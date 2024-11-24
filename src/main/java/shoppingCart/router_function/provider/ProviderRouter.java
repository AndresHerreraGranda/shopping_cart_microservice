package shoppingCart.router_function.provider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProviderRouter {
    @Bean
    public RouterFunction<ServerResponse> providerRoutes(ProviderHandler handler) {
        return route()
                .GET("/providers", handler::findAll)
                .GET("/providers/{id}", handler::findById)
                .POST("/providers", handler::save)
                .PUT("/providers/{id}", handler::update)
                .DELETE("/providers/{id}", handler::deleteById)
                .build();
    }
}
