package shoppingCart.model;

import lombok.Builder;

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
