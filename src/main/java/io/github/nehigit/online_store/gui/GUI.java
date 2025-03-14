package io.github.nehigit.online_store.gui;

import io.github.nehigit.online_store.db.ShoppingCart;
import io.github.nehigit.online_store.db.sql.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
@Component
public class GUI {
    private final ProductRepository productRepository;
    private final Scanner scanner;
    private final ShoppingCart shoppingCart;

    public String showMenuAndReadChoice() {
        System.out.println("1. List products");
        System.out.println("2. Show shopping cart");
        System.out.println("3. Add product to cart");
        System.out.println("4. Remove product from cart");
        System.out.println("5. Finalize purchase");
        System.out.println("6. Exit");

        return this.scanner.nextLine();
    }

    public void listProducts() {
        this.productRepository.getProducts().forEach(System.out::println);
    }

    public void listShoppingCart() {
        this.shoppingCart.printItems();
    }

    public void askAndAddProductToCartById() {
        System.out.println("Product id:");
        String id = this.scanner.nextLine();

        if(productRepository.getProduct(id).isPresent()) {
            this.shoppingCart.addProduct(productRepository.getProduct(id).get());
            System.out.println("Product added to the cart");
        }
        else {
            System.out.println("Product not found");
        }
    }

    public void askAndRemoveProductFromCartById() {
        System.out.println("Product id:");
        String id = this.scanner.nextLine();

        if(shoppingCart.getProduct(id).isPresent()) {
            this.shoppingCart.removeProduct(productRepository.getProduct(id).get());
            System.out.println("Product removed from the cart");
        }
        else {
            System.out.println("Product not found");
        }
    }

    public List<String> askAndGetCredentials() {
        System.out.println("Login:");
        String login = this.scanner.nextLine();

        System.out.println("Password:");
        String password = this.scanner.nextLine();

        List<String> credentials = new ArrayList<>(List.of(
            login, password
        ));

        return credentials;
    }

    public void askAndCompleteOrder() {
        System.out.println("Are you sure you want to complete your order and proceed to payment?");
        System.out.println("1. Yes");
        System.out.println("2. No");

        String confirm = this.scanner.nextLine();

        switch(confirm) {
            case "1":
                this.shoppingCart.emptyCartAndUpdateRepository();
                break;
            case "2":
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }
}
