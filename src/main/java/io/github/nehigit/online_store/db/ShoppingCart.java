package io.github.nehigit.online_store.db;

import io.github.nehigit.online_store.db.sql.ProductRepository;
import io.github.nehigit.online_store.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ShoppingCart {
    private final ProductRepository productRepository;
    private LinkedList<Product> products = new LinkedList<>();

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public Optional<Product> getProduct(String id) {
        int productId;
        try {
            productId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }

        return this.products.stream()
                .filter(product -> product.id() == productId)
                .findFirst();
    }


    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    public void emptyCart() {
        this.products = new LinkedList<>();
    }

    public void printItems() {
        this.products.forEach(System.out::println);
    }

    public void emptyCartAndUpdateRepository() {
        emptyCart();
        this.productRepository.updateProducts(this.products);
    }
}
