package daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.User;

public class UserDaoImpl extends Dao implements UserDao {
    private PreparedStatement stmtRegister;
    private PreparedStatement stmtCheckEmail;
    private PreparedStatement stmtLogin;
    private PreparedStatement stmtEditProfile;
    private PreparedStatement stmtChangePassword;

    

    public UserDaoImpl() throws Exception {
        String sqlRegister = "INSERT INTO users (first_name,last_name, email, mobile , birth , password) VALUES (?, ?, ?, ?,?,?)";
        stmtRegister = con.prepareStatement(sqlRegister);

        String sqlCheckEmail = "SELECT COUNT(*) FROM Users WHERE email = ?";
        stmtCheckEmail = con.prepareStatement(sqlCheckEmail);

        String sqlLogin = "SELECT * FROM Users WHERE email = ?";
        stmtLogin = con.prepareStatement(sqlLogin);
        
        String sqlEditProfile = "update users set first_name=?, last_name=?, email=? ,mobile=? , birth=?  where id=?";
        stmtEditProfile = con.prepareStatement(sqlEditProfile);
        
        String sqlChangePassword = "update users set password=?  where id=?";
        stmtChangePassword = con.prepareStatement(sqlChangePassword);
    }

    public void close() throws Exception {
        try {
            if (stmtRegister != null) stmtRegister.close();
            if (stmtCheckEmail != null) stmtCheckEmail.close();
            if (stmtLogin != null) stmtLogin.close();
        } finally {
            super.close();
        }
    }

    private boolean checkEmailExists(String email) throws SQLException {
        stmtCheckEmail.setString(1, email);
        try (ResultSet rs = stmtCheckEmail.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        }
    }

    public boolean register(String first_name, String last_name, String email, String mobile , Date birth , String password) throws SQLException {
//        if (checkEmailExists(email)) {
//            System.out.println("Email already exists.");
//            return false;
//        }

        stmtRegister.setString(1, first_name);
        stmtRegister.setString(2, last_name);
        stmtRegister.setString(3, email);
        stmtRegister.setString(4, mobile);
        stmtRegister.setDate(5, birth);
        stmtRegister.setString(6, password);



        return stmtRegister.executeUpdate() == 1;
    }

    public User login(String email, String password) throws SQLException {
        stmtLogin.setString(1, email);
        try (ResultSet rs = stmtLogin.executeQuery()) {
            if (rs.next()) {
            	 if (rs.getString("password").equals(password)) {
                     return new User(rs.getInt("id"),rs.getString("first_name"),rs.getString("last_name"), rs.getString("email"),rs.getString("mobile"),rs.getDate("birth"), rs.getString("password"));
                 }
            }
            return null;  
        }
    }
    
    public boolean editProfile(User u) throws Exception{
   	 stmtEditProfile.setString(1,u.getFirst_name());
 		stmtEditProfile.setString(2, u.getLast_name());
 		stmtEditProfile.setString(3, u.getEmail());
 		stmtEditProfile.setString(4, u.getMobile());
 		stmtEditProfile.setDate(5, u.getBirth());
 		stmtEditProfile.setInt(6, u.getId());

 		return stmtEditProfile.executeUpdate() == 1;
    }
    
    public boolean changePassword(String password,int id) throws Exception{
      	 stmtChangePassword.setString(1,password);
    		stmtChangePassword.setInt(2, id);
    		

    		return stmtChangePassword.executeUpdate() == 1;
       }
}
