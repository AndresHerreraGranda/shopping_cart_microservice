package shoppingCart.router_function.product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProductRouter {
    @Bean
    public RouterFunction<ServerResponse> productRoutes(ProductHandler handler) {
        return route()
                .GET("/products", handler::findAll)
                .GET("/products/{id}", handler::findById)
                .POST("/products", handler::save)
                .PUT("/products/{id}", handler::update)
                .DELETE("/products/{id}", handler::deleteById)
                .build();
    }
}
