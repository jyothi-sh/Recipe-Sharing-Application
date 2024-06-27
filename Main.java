import Account.Admin;
import Account.User;
import Recipe.RecipeManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main3 {
    private static Scanner scanner = new Scanner(System.in);
    private static RecipeManager recipeManager = new RecipeManager();

    public static void main(String[] args) {
    	
        int choice = 0;
        while (choice != 3) {
            System.out.println("\nWelcome to the Recipe Sharing Platform!\n");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Please select an option: ");
            try {
            	choice = scanner.nextInt();  // Read the next integer from the scanner
                scanner.nextLine(); // Consume the newline

            } catch (InputMismatchException e) {
                scanner.nextLine();  // Consume the invalid input to clear the scanner
            }
            

            switch (choice) {
                case 1: // Register
                    handleRegistration();
                    break;
                case 2: // Login
                    handleLogin();
                    break;
                case 3: // Exit
                    System.out.println("Thank you for using our platform. Goodbye!");
                    scanner.close();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    private static void handleRegistration() {
    	while (true) {
    		System.out.println("\nRegister a New Account");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.print("Please select the type of account: ");
            int registrationChoice = 0;
            try {
            	registrationChoice = scanner.nextInt();  // Read the next integer from the scanner
                scanner.nextLine(); // Consume the newline

            } catch (InputMismatchException e) {
                scanner.nextLine();  // Consume the invalid input to clear the scanner
            }

            if (registrationChoice == 1) {
            	System.out.println();
                System.out.println("Registering as Admin");
            	System.out.println();
                Admin admin = new Admin(recipeManager);
                admin.register();
                break;
            } else if (registrationChoice == 2) {
            	System.out.println();
                System.out.println("Registering as User");
            	System.out.println();
                User user = new User(recipeManager);
                user.register();
                break;
            } else {
                System.out.println("Invalid choice. Please select either 1 for Admin or 2 for User.");
            }
    	}
        
    }

    private static void handleLogin() {
    	while (true) {
    		System.out.println("\nLogin to Your Account");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.print("Please select the type of account: ");
            int loginChoice = 0;
            try {
            	loginChoice = scanner.nextInt();  // Read the next integer from the scanner
                scanner.nextLine(); // Consume the newline

            } catch (InputMismatchException e) {
                scanner.nextLine();  // Consume the invalid input to clear the scanner
            }
            
            if (loginChoice == 1) {
                System.out.println("\nLogging in as Admin");
                Admin admin = new Admin(recipeManager);
                admin.login();
                admin.displayDashboard();
            } else if (loginChoice == 2) {
                System.out.println("\nLogging in as User");
                User user = new User(recipeManager);
                user.login();
                user.displayDashboard();
                
            } else {
                System.out.println("Invalid choice. Please select either 1 for Admin or 2 for User.");
            }
    	}
        
    }
}
