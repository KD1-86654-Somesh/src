package main;

import java.util.List;
import java.util.Scanner;

import daos.UserDao;
import daos.UserDaoImpl;
import entities.User;



public class UserFunctions {
    private static Scanner scanner = new Scanner(System.in);

	 static void registerUser() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        try (UserDao user = new UserDaoImpl()) {  
            boolean result = user.register(username, password, email);
            if (result) {
                System.out.println("User registered successfully.");
            } else {
                System.out.println("Registration failed. Email may already exist.");
            }
        } catch (Exception e) {
            System.out.println("Error during registration: " + e.getMessage());
            e.printStackTrace();
        }
    }

     static boolean loginUser() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        try (UserDao user = new UserDaoImpl()) { 
            Main.currentUser = user.login(email, password);
            if (Main.currentUser != null) {
                System.out.println("User logged in successfully.");
                System.out.println("Welcome, " + Main.currentUser.getUsername());
                return true;
            } else {
                System.out.println("Login failed. Please check your email and password.");
            }
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
     

}
