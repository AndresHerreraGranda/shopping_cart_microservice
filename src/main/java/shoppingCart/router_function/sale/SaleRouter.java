package shoppingCart.router_function.sale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class SaleRouter {
    @Bean
    public RouterFunction<ServerResponse> saleRoutes(SaleHandler handler) {
        return route()
                .GET("/sales", handler::findAll)
                .GET("/sales/customer/{id}", handler::saveByCustomer)
                .GET("/sales/date", handler::findByDate)
                .build();
    }
}
