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
            System.out.println("Welcome to the  Application!");
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
            System.out.println("1. Edit Profile");
            System.out.println("2. Change Password");
            System.out.println("3. Display All Movies");
            System.out.println("4. Add Review");
            System.out.println("5. Edit Review");
            System.out.println("6. Delete Review");
            System.out.println("7. Display All Reviews");
            System.out.println("8. Display My Reviews");
            System.out.println("9. Display reviews shared with me");
            System.out.println("10. Share review");

            System.out.println("0. Exit");

            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1: 
                	users.editProfile();
                    break;
                case 2: 
                    users.changePassword();
                	break;
                case 3: 
                    users.viewMovies();
                    break;
                case 4: 
                    users.addReview();
                    break;
                case 5: 
                    users.editReview();
                    break;
                case 6: 
                    users.deleteReview();
                    break;
                case 7: 
                    users.displayAllReviews();
                    break;
                case 8: 
                    users.displayMyReviews();
                    break;
                case 9: 
                    users.sharedWithMe();
                    break;
                case 10: 
                    users.shareReview();
                    break;
                case 0:
                    System.out.println("Logged Out...");
                    currentUser=null;
                    System.exit(0);  
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    
    
}
