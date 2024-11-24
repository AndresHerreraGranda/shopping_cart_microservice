package shoppingCart.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Builder
public record RetriveProduct(
        Long id,
        String name,
        Double price,
        String description,
        String imageUrl,
        Integer stock,
        Provider provider
) {
}
