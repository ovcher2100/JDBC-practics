package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String createUsersTable = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            session.createNativeQuery(createUsersTable).executeUpdate();
            try {
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            try {
                transaction.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        String dropUsersTable = "DROP TABLE IF EXISTS users";
        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            session.createNativeQuery(dropUsersTable).executeUpdate();
            try {
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            try {
                transaction.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastname, age) VALUES (?,?,?)";
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            session.createNativeQuery(sql)
                    .setParameter(1, name)
                    .setParameter(2, lastName)
                    .setParameter(3, age)
                    .executeUpdate();
            try {
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.printf("User с именем — %s добавлен в базу данных\n", name);
        } catch (Exception e) {
            try {
                transaction.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String removeUserById = "DELETE FROM users WHERE id = :id";
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            int result = session.createNativeQuery(removeUserById)
                    .setParameter("id", id)
                    .executeUpdate();
            if (result > 0) {
                System.out.printf("Пользователь с ID:  %d  успешно удален.\n", id);
            } else {
                System.out.printf("Пользователь с ID:  %d   не найден.\n", id);
            }
            try {
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            try {
                transaction.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }


    @Override
    public List<User> getAllUsers() {
        String getAllUsers = "SELECT  name, lastName, age FROM users";
        Transaction transaction = null;
        List<User> users = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            NativeQuery<User> query = session.createNativeQuery(getAllUsers, User.class);
            users = query.getResultList();
            try {
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            try {
                transaction.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String cleanUsersTable = "DELETE FROM users";
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createNativeQuery(cleanUsersTable).executeUpdate();
            try {
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            try {
                transaction.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
