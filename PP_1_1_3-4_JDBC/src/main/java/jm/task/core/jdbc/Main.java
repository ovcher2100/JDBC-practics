package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args)  {
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
