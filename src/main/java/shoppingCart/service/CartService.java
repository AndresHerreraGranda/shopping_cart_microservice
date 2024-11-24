package shoppingCart.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import shoppingCart.model.Cart;
import shoppingCart.model.RetriveCart;
import shoppingCart.repository.CartRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CustomerService customerService;

    public Flux<Cart> findAll(){
        return cartRepository.findAll();
    }

    public Mono<Cart> findById(Long id){
        return cartRepository.findById(id);
    }


    public Mono<RetriveCart> findByCustomerId(Long customerId) {
        RetriveCart retriveCart = new RetriveCart();
        return cartRepository.findByIdCustomer(customerId)
                .collectList()
                .flatMap(carts -> {
                    double total = carts.stream()
                            .mapToDouble(Cart::subTotal)
                            .sum();
                    retriveCart.setTotal(total);
                    return Flux.fromIterable(carts)
                            .flatMap(cart -> productService.findById(cart.idProduct()))
                            .collectList()
                            .flatMap(products -> {
                                retriveCart.setProducts(products);
                                return customerService.findById(customerId).map(customer -> {
                                    retriveCart.setCustomer(customer);
                                    return retriveCart;
                                });
                            });
                });
    }

    public Mono<Cart> save(Cart cart){
        return productService.findById(cart.idProduct())
                .flatMap(product -> cartRepository.save(Cart.builder()
                        .idCustomer(cart.idCustomer())
                        .idProduct(cart.idProduct())
                        .quantity(cart.quantity())
                        .subTotal(product.price() * cart.quantity())
                        .build()));
    }

    public Mono<Void> deleteById(Long id){
        return cartRepository.deleteById(id);
    }

}
