package shoppingCart.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import shoppingCart.model.Product;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
}
