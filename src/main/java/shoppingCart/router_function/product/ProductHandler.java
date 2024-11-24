package shoppingCart.router_function.product;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import shoppingCart.model.Product;
import shoppingCart.service.ProductService;

@Component
@AllArgsConstructor
public class ProductHandler {
    private final ProductService productService;
    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.findAll(), Product.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        return productService.findById(id)
                .flatMap(product -> ServerResponse.ok().body(Mono.just(product), Product.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> save(ServerRequest request){
        return request.bodyToMono(Product.class)
                .flatMap(productService::save)
                .flatMap(savedProduct -> ServerResponse.ok().body(Mono.just(savedProduct), Product.class))
                .onErrorResume(e -> {
                    if (e.getMessage().equals("Provider not found")) {
                        return ServerResponse.status(404).bodyValue(e.getMessage());
                    }
                    return Mono.error(e);
                });
    }

    public Mono<ServerResponse> update(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        return productService.findById(id)
                .flatMap(existingProduct -> request.bodyToMono(Product.class)
                        .map(product -> Product.builder()
                                    .name(product.name() != null ? product.name() : existingProduct.name())
                                    .price(product.price() != null ? product.price() : existingProduct.price())
                                .description(product.description() != null ? product.description() : existingProduct.description())
                                .imageUrl(product.imageUrl() != null ? product.imageUrl() : existingProduct.imageUrl())
                                .stock(product.stock() != null ? product.stock() : existingProduct.stock())
                                .idProvider(product.idProvider() != null ? product.idProvider() : existingProduct.idProvider())
                                .build())
                        .flatMap(productService::save)
                        .flatMap(updatedProduct -> ServerResponse.ok().body(Mono.just(updatedProduct), Product.class))
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteById(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        return productService.deleteById(id)
                .then(ServerResponse.ok().build());
    }
}
