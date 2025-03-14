package io.github.nehigit.online_store.core;

import io.github.nehigit.online_store.auth.Authenticator;
import io.github.nehigit.online_store.gui.GUI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Core {
    private final GUI gui;
    private final Authenticator authenticator;

    public void run() {
        boolean running = false;
        int tries = 0;

        while(!running && tries < 3) {
            running = this.authenticator.authenticate(gui.askAndGetCredentials());
            tries++;
        }

        while(running) {
            switch(this.gui.showMenuAndReadChoice()) {
                case "1":
                    this.gui.listProducts();
                    break;
                case "2":
                    this.gui.listShoppingCart();
                    break;
                case "3":
                    this.gui.askAndAddProductToCartById();
                    break;
                case "4":
                    this.gui.askAndRemoveProductFromCartById();
                    break;
                case "5":
                    this.gui.askAndCompleteOrder();
                    break;
                case "6":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }

    }
}
