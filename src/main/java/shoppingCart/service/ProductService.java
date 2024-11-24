package shoppingCart.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import shoppingCart.model.Product;
import shoppingCart.model.RetriveProduct;
import shoppingCart.repository.ProductRepository;
import shoppingCart.repository.ProviderRepository;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProviderRepository providerRepository;

    public Flux<Product> findAll(){
        return productRepository.findAll();
    }

    public Mono<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public Mono<RetriveProduct> save(Product product){
        return providerRepository.findById(product.idProvider())
                .flatMap(provider -> productRepository.save(product)
                       .flatMap(productSaved -> Mono.just(RetriveProduct.builder()
                                 .id(productSaved.id())
                               .name(productSaved.name())
                               .description(productSaved.description())
                               .stock(productSaved.stock())
                               .price(productSaved.price())
                               .imageUrl(productSaved.imageUrl())
                               .provider(provider)
                               .build())))
                .switchIfEmpty(Mono.error(new RuntimeException("Provider not found")));

    }


    public Mono<Void> deleteById(Long id){
        return productRepository.deleteById(id);
    }
}
