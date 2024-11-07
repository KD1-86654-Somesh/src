package main;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import daos.UserDao;
import daos.UserDaoImpl;
import daos.movieDao;
import daos.movieDaoImpl;
import daos.reviewsDao;
import daos.reviewsDaoImpl;
import entities.User;
import entities.movies;
import entities.reviews;



public class UserFunctions {
    private static Scanner scanner = new Scanner(System.in);

	 static void registerUser() {
		 System.out.print("Enter first name: ");
			String first_name = scanner.next();
			System.out.print("Enter last name: ");
			String last_name = scanner.next();
			System.out.print("Enter email: ");
			String email = scanner.next();
			System.out.print("Enter mobile: ");
			String mobile = scanner.next();
			System.out.print("Enter birth date (yyyy-MM-dd): ");
			String birth = scanner.next();
			Date birthDate = Date.valueOf(birth);
			System.out.print("Enter password: ");
			String password = scanner.next();
        try (UserDao user = new UserDaoImpl()) {  
            boolean result = user.register(first_name, last_name, email, mobile, birthDate, password);
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
                System.out.println("Welcome, " + Main.currentUser.getFirst_name());
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
     
     
     static void editProfile() {
		 System.out.print("Enter first name: ");
			String first_name = scanner.next();
			System.out.print("Enter last name: ");
			String last_name = scanner.next();
			System.out.print("Enter email: ");
			String email = scanner.next();
			System.out.print("Enter mobile: ");
			String mobile = scanner.next();
			System.out.print("Enter birth date (yyyy-MM-dd): ");
			String birth = scanner.next();
			Date birthDate = Date.valueOf(birth);
			
        try (UserDao user = new UserDaoImpl()) {  
        	User u = new User(Main.currentUser.getId(),first_name, last_name, email, mobile, birthDate, Main.currentUser.getPassword());
            boolean result = user.editProfile(u);
            if (result) {
                System.out.println("User Updated successfully.");
            } else {
                System.out.println("Update failed.");
            }
        } catch (Exception e) {
            System.out.println("Error during registration: " + e.getMessage());
            e.printStackTrace();
        }
    }
     
     static void changePassword() {
		 System.out.print("Enter New Password : ");
			String password = scanner.next();
			
			
        try (UserDao user = new UserDaoImpl()) {  
            boolean result = user.changePassword(password,Main.currentUser.getId());
            if (result) {
                System.out.println("User password Changed successfully.");
            } else {
                System.out.println("Change password failed.");
            }
        } catch (Exception e) {
            System.out.println("Error during password change: " + e.getMessage());
            e.printStackTrace();
        }
    }
     
     
     static void viewMovies() {
			try(movieDao MovieDao = new movieDaoImpl()) {
				List<movies> list = MovieDao.findAll();
				list.forEach(System.out::println);
			}
			catch (Exception e) {
				e.printStackTrace();
			}		
		}
     
     
     static void addReview() {
		 System.out.print("Enter MovieId: ");
			int movie_id = scanner.nextInt();
			System.out.print("Enter review  : ");
			String review_text = scanner.next();
			System.out.print("Enter rating: ");
			int rating = scanner.nextInt();
			
        try (reviewsDao review = new reviewsDaoImpl()) {  
        	reviews r = new reviews(0, movie_id, review_text, rating, Main.currentUser.getId());
            boolean result = review.addReview(r);
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
     


     
     static boolean editReview() {
         try(reviewsDao reviewDao = new reviewsDaoImpl()) {
        	 System.out.print("Enter review Id to edit: ");
             int id = scanner.nextInt();
 			reviews r = reviewDao.findReview(id);
 			if(r != null) {
 				System.out.println("Found " + r); 	
 			    System.out.println("Enter new review text");
 			    String review_text = scanner.next();
 			    System.out.println("Enter new review rating");
			    int rating = scanner.nextInt();
			    reviews r2 = new reviews(r.getId(),r.getMovieId(),review_text,rating,r.getUserId());
	            boolean result = reviewDao.editReview(r2);
	            if (result) {
	                System.out.println("Review Updated successfully.");
	            } else {
	                System.out.println("Review Update failed.");
	            }
 			}
 			else
 				System.out.println("Review Not Found.");
 		} // candDao.close();
 		catch (Exception e) {
 			e.printStackTrace();
 		}
         return false;
     }
     
     static void deleteReview() {
    	 try(reviewsDao reviewDao = new reviewsDaoImpl()) {
 			System.out.print("Enter review Id (to delete): ");
 			int id = scanner.nextInt();
 			boolean result = reviewDao.deleteReview(id);
 			if (result) {
                System.out.println("Deleted Successfully.");
            } else {
                System.out.println("Deletion failed.");
            }
 		} // candDao.close();
 		catch (Exception e) {
 			e.printStackTrace();
 		}
     }
     
     static void displayAllReviews() {
			try(reviewsDao reviewDao = new reviewsDaoImpl()) {
				List<reviews> list = reviewDao.displayAll();
				list.forEach(System.out::println);
			}
			catch (Exception e) {
				e.printStackTrace();
			}		
		}
     
     static void displayMyReviews() {
		    if (Main.currentUser  == null) {
		        System.out.println("You need to log in to view your reviews.");
		        return;
		    }

		    try (reviewsDao reviewDao = new reviewsDaoImpl()) {
		        List<reviews> reviewList = reviewDao.displayMyReviews(Main.currentUser.getId()); // Assuming getId() returns user_id
				reviewList.forEach(System.out::println);
		    } catch (Exception e) {
		        System.out.println("Error while retrieving orders: " + e.getMessage());
		        e.printStackTrace();
		    }
		}
}
