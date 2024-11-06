package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();
    Statement statement;
    PreparedStatement preparedStatement;


    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS Users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(50)," +
                "lastName VARCHAR(50)," +
                "age INT)";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(createTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() throws SQLException {
        String dropUsersTable = "DROP TABLE IF EXISTS Users";
        try {
            statement = connection.createStatement();
            statement.execute(dropUsersTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(saveUser);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.printf("User с именем — %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String removeUserById = "DELETE FROM Users WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(removeUserById);
            preparedStatement.setLong(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.printf("Пользователь с ID:  %d  успешно удален.\n", id);
            } else {
                System.out.printf("Пользователь с ID:  %d   не найден.\n", id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() throws SQLException {
        String getAllUsers = "SELECT name, lastName, age FROM Users;";
        List<User> users = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAllUsers);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        String cleanUsersTable = "TRUNCATE TABLE users";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(cleanUsersTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
