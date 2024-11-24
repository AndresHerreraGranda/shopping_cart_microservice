package shoppingCart.router_function.cart;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CartRouter {
    @Bean
    public RouterFunction<ServerResponse> cartRoutes(CartHandler handler) {
        return route()
                .GET("/carts", handler::findAll)
                .GET("/carts/{id}", handler::findById)
                .GET("/carts/customer/{customerId}", handler::findByCustomerId)
                .POST("/carts", handler::save)
                .PUT("/carts/{id}", handler::update)
                .DELETE("/carts/{id}", handler::deleteById)
                .build();
    }
}
