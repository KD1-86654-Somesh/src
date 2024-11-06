package daos;

import java.sql.SQLException;

import entities.User;

public interface UserDao extends AutoCloseable{
      boolean register(String username, String password, String email) throws SQLException;
      User login(String password, String email) throws Exception;

}
