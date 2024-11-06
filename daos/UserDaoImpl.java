package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.User;

public class UserDaoImpl extends Dao implements UserDao {
    private PreparedStatement stmtRegister;
    private PreparedStatement stmtCheckEmail;
    private PreparedStatement stmtLogin;

    public UserDaoImpl() throws Exception {
        String sqlRegister = "INSERT INTO Users (username, password, email) VALUES (?, ?, ?)";
        stmtRegister = con.prepareStatement(sqlRegister);

        String sqlCheckEmail = "SELECT COUNT(*) FROM Users WHERE email = ?";
        stmtCheckEmail = con.prepareStatement(sqlCheckEmail);

        String sqlLogin = "SELECT * FROM Users WHERE email = ?";
        stmtLogin = con.prepareStatement(sqlLogin);
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

    public boolean register(String username, String password, String email) throws SQLException {
        if (checkEmailExists(email)) {
            System.out.println("Email already exists.");
            return false;
        }

        stmtRegister.setString(1, username);
        stmtRegister.setString(2, password);
        stmtRegister.setString(3, email);

        return stmtRegister.executeUpdate() == 1;
    }

    public User login(String email, String password) throws SQLException {
        stmtLogin.setString(1, email);
        try (ResultSet rs = stmtLogin.executeQuery()) {
            if (rs.next()) {
            	 if (rs.getString("password").equals(password)) {
                     return new User(rs.getInt("id"),rs.getString("username"), rs.getString("email"), rs.getString("password"));
                 }
            }
            return null;  
        }
    }
}
