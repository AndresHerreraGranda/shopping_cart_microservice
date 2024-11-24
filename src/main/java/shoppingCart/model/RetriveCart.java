package shoppingCart.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class RetriveCart {
    private Customer customer;
    private List<Product> products;
    private Double total;
}
