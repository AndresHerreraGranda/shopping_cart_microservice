package shoppingCart.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("carts")
@Builder
public record Cart(
        @Id
        Long id,
        @Column("id_customer")
        Long idCustomer,
        @Column("id_product")
        Long idProduct,
        Integer quantity,
        @Column("sub_total")
        Double subTotal
) {
}
