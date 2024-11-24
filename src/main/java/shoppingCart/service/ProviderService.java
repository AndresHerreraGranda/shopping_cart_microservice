package shoppingCart.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import shoppingCart.model.Provider;
import shoppingCart.repository.ProviderRepository;

@Service
@AllArgsConstructor
public class ProviderService {
    private final ProviderRepository providerRepository;

    public Flux<Provider> findAll(){
        return providerRepository.findAll();
    }

    public Mono<Provider> findById(Long id){
        return providerRepository.findById(id);
    }

    public Mono<Provider> save(Provider provider){
        return providerRepository.save(provider);
    }

    public Mono<Void> deleteById(Long id){
        return providerRepository.deleteById(id);
    }
}
