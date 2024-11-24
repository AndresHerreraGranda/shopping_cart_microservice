package shoppingCart.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RetriveSale {
    Customer customer;
    Product product;
    Provider provider;
}
