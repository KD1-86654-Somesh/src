package daos;

import java.sql.Date;
import java.sql.SQLException;

import entities.User;

public interface UserDao extends AutoCloseable{
      boolean register(String first_name, String last_name, String email, String mobile , Date birth , String password ) throws SQLException;
      User login(String email, String password) throws Exception;
      boolean editProfile(User u) throws Exception;
      boolean changePassword(String password,int id) throws Exception;

}
