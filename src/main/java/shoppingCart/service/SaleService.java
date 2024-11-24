package shoppingCart.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import shoppingCart.model.RetriveSale;
import shoppingCart.model.Sale;
import shoppingCart.repository.CartRepository;
import shoppingCart.repository.CustomerRepository;
import shoppingCart.repository.ProductRepository;
import shoppingCart.repository.ProviderRepository;
import shoppingCart.repository.SaleRepository;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final CartService cartService;
    private final ProductRepository productRepository;
    private final ProviderRepository providerRepository;

    public Flux<Sale> findAll(){
        return saleRepository.findAll();
    }

    public Mono<Sale> findById(Long id){
        return saleRepository.findById(id);
    }

    public Mono<Sale> save(Sale sale){
        return saleRepository.save(sale);
    }

    public Mono<Void> deleteById(Long id){
        return saleRepository.deleteById(id);
    }

    public Flux<Sale> saveCartByCustomer(Long id){
        return cartRepository.findByIdCustomer(id)
                .flatMap(cart -> {
                    return Flux.just(Sale.builder()
                            .idCart(cart.id())
                            .createdAt(LocalDate.now())
                            .build());
                })
                .flatMap(saleRepository::save);
    }

    public Flux<RetriveSale> findByDate(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        return saleRepository.findAll()
                .filter(sale -> !sale.createdAt().isBefore(start) && !sale.createdAt().isAfter(end))
                .flatMap(sale -> cartRepository.findById(sale.idCart())
                        .flatMap(cart -> customerRepository.findById(cart.idCustomer())
                                .flatMap(customer -> productRepository.findById(cart.idProduct())
                                        .flatMap(product -> providerRepository.findById(product.idProvider())
                                                .map(provider -> {
                                                    RetriveSale retriveSale = new RetriveSale();
                                                    retriveSale.setCustomer(customer);
                                                    retriveSale.setProduct(product);
                                                    retriveSale.setProvider(provider);
                                                    return retriveSale;
                                                })))));
    }
}
