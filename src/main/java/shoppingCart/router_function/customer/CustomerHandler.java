package shoppingCart.router_function.customer;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import shoppingCart.model.Customer;
import shoppingCart.service.CustomerService;

@Component
@AllArgsConstructor
public class CustomerHandler {
    private final CustomerService customerService;
    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(customerService.findAll(), Customer.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        return customerService.findById(id)
                .flatMap(customer -> ServerResponse.ok().body(Mono.just(customer), Customer.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> save(ServerRequest request){
        return request.bodyToMono(Customer.class)
                .flatMap(customerService::save)
                .flatMap(savedCustomer -> ServerResponse.ok().body(Mono.just(savedCustomer), Customer.class));
    }

    public Mono<ServerResponse> update(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        return customerService.findById(id)
                .flatMap(existingCustomer -> request.bodyToMono(Customer.class)
                        .map(customer -> Customer.builder()
                                    .name(customer.name() != null ? customer.name() : existingCustomer.name())
                                    .email(customer.email() != null ? customer.email() : existingCustomer.email())
                                    .password(customer.password() != null ? customer.password() : existingCustomer.password())
                                    .build())
                        .flatMap(customerService::save)
                        .flatMap(updatedCustomer -> ServerResponse.ok().body(Mono.just(updatedCustomer), Customer.class))
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteById(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        return customerService.deleteById(id)
                .then(ServerResponse.ok().build());
    }
}
