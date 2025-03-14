package io.github.nehigit.online_store.db.sql;

import io.github.nehigit.online_store.db.Constants;
import org.springframework.stereotype.Component;
import io.github.nehigit.online_store.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepository {

    private final String SQL_GET_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    private final String SQL_GET_ALL_USERS = "SELECT * FROM user";

    public Optional<User> getUser(String login) {
        try {
            PreparedStatement preparedStatement = Constants.CONNECTION.prepareStatement(SQL_GET_USER_BY_LOGIN);
            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Getting user by login failed: " + e.getMessage());
        }

        return Optional.empty();
    }

    public List<User> getUsers() {
        List<User> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    Constants.CONNECTION.prepareStatement(SQL_GET_ALL_USERS);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                result.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Getting users failed: " + e.getMessage());
        }

        return result;
    }
}
