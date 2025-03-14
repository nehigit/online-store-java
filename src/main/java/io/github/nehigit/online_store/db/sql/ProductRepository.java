package io.github.nehigit.online_store.db.sql;

import io.github.nehigit.online_store.db.Constants;
import io.github.nehigit.online_store.model.Product;
import io.github.nehigit.online_store.model.User;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class ProductRepository {

    private final String SQL_GET_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?";
    private final String SQL_GET_ALL_PRODUCTS = "SELECT * FROM product";
    private final String SQL_UPDATE_PRODUCT_QUANTITY = "UPDATE product SET quantity = ? WHERE id = ?";


    public Optional<Product> getProduct(String id) {
        try {
            PreparedStatement preparedStatement = Constants.CONNECTION.prepareStatement(SQL_GET_PRODUCT_BY_ID);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getDouble("screenWidth"),
                        resultSet.getString("CPU"),
                        resultSet.getInt("RAMCapacityGB"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Getting product by id failed: " + e.getMessage());
        }

        return Optional.empty();
    }

    public List<Product> getProducts() {
        List<Product> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = Constants.CONNECTION.prepareStatement(SQL_GET_ALL_PRODUCTS);
            ResultSet resultSet= preparedStatement.executeQuery();
            while(resultSet.next()) {
                result.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getDouble("screenWidth"),
                        resultSet.getString("CPU"),
                        resultSet.getInt("RAMCapacityGB"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Getting all products failed: " + e.getMessage());
        }

        return result;
    }

    public void updateProducts(LinkedList<Product> products) {
        try {
            PreparedStatement preparedStatement = Constants.CONNECTION.prepareStatement(SQL_UPDATE_PRODUCT_QUANTITY);

            for (Product product : products) {
                preparedStatement.setInt(1, product.quantity() - 1);
                preparedStatement.setInt(2, product.id());

                int rowsUpdated = preparedStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Updated product with id: " + product.id());
                } else {
                    System.out.println("No product found with id: " + product.id());
                }
            }

            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("Updating products failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
