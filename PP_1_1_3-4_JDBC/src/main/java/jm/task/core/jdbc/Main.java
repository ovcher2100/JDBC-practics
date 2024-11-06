package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Marat","Reznov",(byte)37);
        userService.saveUser("Rinat","Reznov",(byte)20);
        userService.saveUser("Agat","Rinatov",(byte)35);
        userService.saveUser("Durak","Agatov",(byte)24);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}