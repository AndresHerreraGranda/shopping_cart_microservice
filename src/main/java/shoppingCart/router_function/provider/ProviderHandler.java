package shoppingCart.router_function.provider;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import shoppingCart.model.Provider;
import shoppingCart.service.ProviderService;
import shoppingCart.service.ProviderService;

@Component
@AllArgsConstructor
public class ProviderHandler {
    private final ProviderService providerService;
    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(providerService.findAll(), Provider.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        return providerService.findById(id)
                .flatMap(customer -> ServerResponse.ok().body(Mono.just(customer), Provider.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> save(ServerRequest request){
        return request.bodyToMono(Provider.class)
                .flatMap(providerService::save)
                .flatMap(savedProvider -> ServerResponse.ok().body(Mono.just(savedProvider), Provider.class));
    }

    public Mono<ServerResponse> update(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        return providerService.findById(id)
                .flatMap(existingProvider -> request.bodyToMono(Provider.class)
                        .map(customer -> Provider.builder()
                                    .name(customer.name() != null ? customer.name() : existingProvider.name())
                                    .email(customer.email() != null ? customer.email() : existingProvider.email())
                                    .password(customer.password() != null ? customer.password() : existingProvider.password())
                                    .build())
                        .flatMap(providerService::save)
                        .flatMap(updatedProvider -> ServerResponse.ok().body(Mono.just(updatedProvider), Provider.class))
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteById(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        return providerService.deleteById(id)
                .then(ServerResponse.ok().build());
    }
}
