package shoppingCart.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("products")
@Builder
public record Product(
        @Id
        Long id,
        String name,
        Double price,
        String description,
        @Column("image_url")
        String imageUrl,
        Integer stock,
        @Column("id_provider")
        Long idProvider
) {
}
