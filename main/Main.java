package main;

import java.util.Scanner;
import daos.UserDao;
import daos.UserDaoImpl;
import entities.User;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    static User currentUser = null;
    static UserFunctions users = new UserFunctions();
    public static void main(String[] args) {
        while(true) {
            System.out.println("Welcome to the E-commerce Application!");
            System.out.println("Select an option:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1: 
                    users.registerUser();
                    break;
                case 2: 
                    if(users.loginUser()) {
                    	userMenu();
                    };
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);  
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void userMenu() {
    	while(true) {
            System.out.println("Select an option:");
            System.out.println("1. Add Products");
            System.out.println("2. View Products");
            System.out.println("3. Place Order");
            System.out.println("4. View  Orders");
            System.out.println("0. Exit");

            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1: 
                	//
                    break;
                case 2: 
                    //
                	break;
                case 3: 
                    //
                    break;
                case 4: 
                    //
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);  
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    
    
}
