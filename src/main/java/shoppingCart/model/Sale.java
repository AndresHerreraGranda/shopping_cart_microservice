package shoppingCart.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("sales")
@Builder
public record Sale(
        @Id
        Long id,
        @Column("id_cart")
        Long idCart,
        @Column("created_at")
        LocalDate createdAt
) {
}
