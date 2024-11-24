package shoppingCart.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import shoppingCart.model.Cart;
import shoppingCart.model.Customer;

@Repository
public interface CartRepository extends ReactiveCrudRepository<Cart, Long> {
    Flux<Cart> findByIdCustomer(Long id);
}
