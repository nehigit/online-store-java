package io.github.nehigit.online_store;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import io.github.nehigit.online_store.config.AppConfiguration;
import io.github.nehigit.online_store.core.Core;

public class App {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfiguration.class);
        Core core = context.getBean(Core.class);
        core.run();
    }
}
