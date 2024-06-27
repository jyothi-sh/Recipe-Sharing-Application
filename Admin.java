package Account;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

import Recipe.RecipeManager;


public class Admin extends User {
    private static final String ADMIN_PROPERTIES_PATH = "/Users/jayma/Desktop/Drugs/CS 151/CS151Project/src/Account/AdminList.properties";
    private Scanner scanner = new Scanner(System.in);
    private Properties admin = new Properties();


    public Admin(String adminUsername, String password, RecipeManager recipeManager) {
        super(adminUsername, password);
        this.recipeManager = recipeManager;
        loadAdmin();
    }

    public Admin(RecipeManager manager) {
    	super(manager);
        loadAdmin();
    }

    private void loadAdmin() {
        try {
            admin.load(new FileReader(ADMIN_PROPERTIES_PATH));
        } catch (IOException e) {
            System.err.println("Failed to load admin data: " + e.getMessage());
        }
    }

    private boolean containsAdminUsername(String adminUsername) {
        return admin.containsKey(adminUsername);
    }

    private void storeAdmin(String username, String pass) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADMIN_PROPERTIES_PATH, true))) {
            writer.write(username + " = " + pass);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Failed to write admin data: " + e.getMessage());
        }
    }

    @Override
    public void register() {
        while (true) {
            System.out.println("\nAdmin Registration\n------------------");
            System.out.print("Enter a unique admin username: ");
            String adminUsername = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (containsAdminUsername(adminUsername)) {
                System.out.println("\nRegistration failed! The username entered already exists.\nPlease try another username!\n");
            } else {
                storeAdmin(adminUsername, password);
                System.out.println("Admin registered successfully! Please login!\n");
                break;
            }
        }
    }

    @Override
    public void login() {
        while (true) {
            System.out.print("Enter your admin username: ");
            String adminUsername = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            if (containsAdminUsername(adminUsername)) {
                if (admin.get(adminUsername).equals(password)) {
                    setUsername(adminUsername);
                    setPassword(password);
                    System.out.println("Login successful!\n");
                    break; // Exit loop on successful login
                } else {
                    System.out.println("Incorrect password! Please try again or enter 'exit' to quit.\n");
                }
            } else {
                System.out.println("Login failed! Admin username does not exist.");
                System.out.println("Enter 'retry' to try again or 'exit' to quit.");
            }

            // Prompt for retry or exit
            System.out.print("Do you want to retry or exit? (retry/exit): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("exit")) {
                System.out.println("Exited.\n");
                System.exit(0); // Exit the program if user chooses to exit
            }
            // Loop will continue if user types "retry" or any other input apart from "exit"
        }
    }

    @Override
    public void logout() {
        setUsername(null);
        setPassword(null);
        System.out.println("Logged out Admin user successfully!");
        System.exit(0);
    }
    
    @Override
    public void displayDashboard() {
    	int choice = 0;
    	while (true) {
            System.out.println("\nAdmin Dashboard\n---------------");
            System.out.println("1. Manage Recipes");
            System.out.println("2. Manage Users");
            System.out.println("3. Logout");

            System.out.print("\nPlease select an option by entering a number: ");
            try {
            	choice = scanner.nextInt();  // Read the next integer from the scanner
                scanner.nextLine(); // Consume the newline

            } catch (InputMismatchException e) {
                scanner.nextLine();  // Consume the invalid input to clear the scanner
            }

            switch (choice) {
                case 1:
                    manageRecipes();
                    continue;
                case 2:
                    manageUsers();
                    continue;
                case 3:
                    logout();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                    System.out.println();
            }
        }
    }

    public void manageUsers() {
    	int choice = 0;
    	while(true) {
    		System.out.println("\nManage Users\n---------------");
            System.out.println("1. Create User");
            System.out.println("2. Delete User");
            System.out.println("3. View All Users");
            System.out.println("4. Return");
            
            System.out.print("\nPlease select an option by entering a number: ");
            try {
            	choice = scanner.nextInt();  // Read the next integer from the scanner
                scanner.nextLine(); // Consume the newline

            } catch (InputMismatchException e) {
                scanner.nextLine();  // Consume the invalid input to clear the scanner
            }
            
            switch (choice) {
                case 1:
                    createUser();
                    continue;
                case 2:
                    deleteUser();
                    continue;
                case 3:
                    viewAllUsers();
                    continue;
                case 4:
                	return;
                default:
                    System.out.println("Invalid Choice! Please enter 1, 2, 3 or 4.");
                    System.out.println();
            }
    	}
    }
    
    public void manageRecipes() {
        
        
        int choice = 0;
        
        while (true) {
        	System.out.println("\nManage Recipes\n---------------");
            System.out.println("1. Add Recipe");
            System.out.println("2. Delete Recipe");
            System.out.println("3. View All Recipes");
            System.out.println("4. Return");
            
            System.out.print("\nPlease select an option by entering a number: ");
            try {
            	choice = scanner.nextInt();  // Read the next integer from the scanner
                scanner.nextLine(); // Consume the newline

            } catch (InputMismatchException e) {
                scanner.nextLine();  // Consume the invalid input to clear the scanner
            }
        	

            switch (choice) {
                case 1:
                    recipeManager.createRecipe(); 
                    continue;
                case 2:
                    recipeManager.deleteRecipe();
                    continue;
                case 3:
                    recipeManager.displayAllRecipes();
                    continue;
                case 4:
                    return; 
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3 or 4.");
                    System.out.println();
            }
        }

        
    }

    // Further implementations remain similar to the original provided

    public void createUser() {
    	super.register();
    }
    
    public void deleteUser() {
    	String username;
    	while (true) {
    		System.out.print("Enter a user's username to delete: ");
        	username = scanner.nextLine();
            super.loadUser();
            if (super.containsUsername(username)) {
            	user.remove(username);
            	System.out.println("User removed successfully!");
            	try (FileOutputStream out = new FileOutputStream("/Users/jayma/Desktop/Drugs/CS 151/CS151Project/src/Account/UserList.properties")) {
                    user.store(out, "Updated properties after removing a key");
                } catch (IOException e) {
                    System.err.println("Failed to save properties: " + e.getMessage());
                }
            	return;
            }
            else {
            	System.out.println("User not found.");
            	System.out.println();
            	return;
            }
    	}
    	
    }
    
    public void viewAllUsers() {
        // Additional logic to view all users
    	super.loadUser();
        System.out.println(user.keySet());
    }
}