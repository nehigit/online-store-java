package io.github.nehigit.online_store.model;

public record Product(
        int id,
        String brand,
        String model,
        double screenWidth,
        String CPU,
        int RAMCapacityGB,
        double price,
        int quantity) {

    public Product {
        if(quantity < 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
    }

    @Override
    public String toString() {
        final String SEPARATOR = ",    ";
        return new StringBuilder()
                .append("id: ").append(id)
                .append(SEPARATOR)
                .append("Brand: ").append(brand)
                .append(SEPARATOR)
                .append("Model: ").append(model)
                .append(SEPARATOR)
                .append("Screen width: ").append(screenWidth)
                .append(SEPARATOR)
                .append("CPU: ").append(CPU)
                .append(SEPARATOR)
                .append("RAM: ").append(RAMCapacityGB).append("GB")
                .append(SEPARATOR)
                .append("Price: ").append(price).append("PLN")
                .append(SEPARATOR)
                .append("In stock: ").append(quantity)
                .toString();
    }
}
