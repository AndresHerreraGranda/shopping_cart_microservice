package shoppingCart.router_function.sale;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import shoppingCart.model.Sale;
import shoppingCart.service.SaleService;

@Component
@AllArgsConstructor
public class SaleHandler {
    private final SaleService saleService;
    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(saleService.findAll(), Sale.class);
    }

    public Mono<ServerResponse> saveByCustomer(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(saleService.saveCartByCustomer(id), Sale.class);
    }

    public Mono<ServerResponse> findByDate(ServerRequest request){
        String startDateStr = request.queryParam("startDate").orElse("");
        String endDateStr = request.queryParam("endDate").orElse("");
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(saleService.findByDate(startDateStr, endDateStr), Sale.class);
    }
}

