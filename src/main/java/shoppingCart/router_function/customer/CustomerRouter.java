package shoppingCart.router_function.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CustomerRouter {
    @Bean
    public RouterFunction<ServerResponse> customerRoutes(CustomerHandler handler) {
        return route()
                .GET("/customers", handler::findAll)
                .GET("/customers/{id}", handler::findById)
                .POST("/customers", handler::save)
                .PUT("/customers/{id}", handler::update)
                .DELETE("/customers/{id}", handler::deleteById)
                .build();
    }
}
