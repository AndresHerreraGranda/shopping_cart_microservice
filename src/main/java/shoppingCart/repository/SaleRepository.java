package shoppingCart.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import shoppingCart.model.Sale;

@Repository
public interface SaleRepository extends ReactiveCrudRepository<Sale, Long> {
}
