package shoppingCart.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("providers")
@Builder
public record Provider(
        @Id
        Long id,
        String name,
        String email,
        String password
) {
}
