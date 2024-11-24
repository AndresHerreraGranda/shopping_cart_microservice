package shoppingCart.router_function.cart;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import shoppingCart.model.Cart;
import shoppingCart.service.CartService;

@Component
@AllArgsConstructor
public class CartHandler {
    private final CartService cartService;
    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(cartService.findAll(), Cart.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        return cartService.findById(id)
                .flatMap(customer -> ServerResponse.ok().body(Mono.just(customer), Cart.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findByCustomerId(ServerRequest request){
        Long customerId = Long.valueOf(request.pathVariable("customerId"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(cartService.findByCustomerId(customerId), Cart.class);
    }
    public Mono<ServerResponse> save(ServerRequest request){
        return request.bodyToMono(Cart.class)
                .flatMap(cartService::save)
                .flatMap(savedCart -> ServerResponse.ok().body(Mono.just(savedCart), Cart.class));
    }

    public Mono<ServerResponse> update(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        return cartService.findById(id)
                .flatMap(existingCart -> request.bodyToMono(Cart.class)
                        .map(customer -> Cart.builder()
                                    .idCustomer(customer.idCustomer() != null ? customer.idCustomer() : existingCart.idCustomer())
                                    .idProduct(customer.idProduct() != null ? customer.idProduct() : existingCart.idProduct())
                                    .build())
                        .flatMap(cartService::save)
                        .flatMap(updatedCart -> ServerResponse.ok().body(Mono.just(updatedCart), Cart.class))
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteById(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        return cartService.deleteById(id)
                .then(ServerResponse.ok().build());
    }
}
