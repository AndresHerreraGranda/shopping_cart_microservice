package shoppingCart.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import shoppingCart.model.Customer;
import shoppingCart.repository.CustomerRepository;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Flux<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Mono<Customer> findById(Long id){
        return customerRepository.findById(id);
    }

    public Mono<Customer> save(Customer customer){
        return customerRepository.save(customer);
    }

    public Mono<Void> deleteById(Long id){
        return customerRepository.deleteById(id);
    }
}
