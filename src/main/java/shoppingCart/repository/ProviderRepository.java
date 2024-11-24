package shoppingCart.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import shoppingCart.model.Provider;

@Repository
public interface ProviderRepository extends ReactiveCrudRepository<Provider, Long> {
}
