package Account;

import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Properties;

import Recipe.RecipeManager;

public class User extends Account {
    private static final String USER_PROPERTIES_PATH = "/Users/jayma/Desktop/Drugs/CS 151/CS151Project/src/Account/UserList.properties";
    private Scanner scanner = new Scanner(System.in);
    protected Properties user = new Properties();
    protected RecipeManager recipeManager;


    public User(String username, String password) {
        super(username, password);
        loadUser();
    }

    public User(RecipeManager manager) {
    	recipeManager = manager;
        loadUser();
    }

    protected void loadUser() {
        try {
            user.load(new FileReader(USER_PROPERTIES_PATH));
        } catch (IOException e) {
            System.err.println("Failed to load user data: " + e.getMessage());
        }
    }

    @Override
    public void register() {
        while (true) {
            System.out.println("\nUser Registration\n------------------");
            System.out.print("Enter a unique username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            
            if (containsUsername(username)) {
                System.out.println("Registration failed! The username entered already exists.\nPlease try another username!\n");
            } else {
                storeUser(username, password);
                System.out.println("User registered successfully!\n");
                break;
            }
        }
    }

    protected boolean containsUsername(String searchString) {
        return user.containsKey(searchString);
    }

    private void storeUser(String username, String pass) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_PROPERTIES_PATH, true))) {
            writer.write(username + " = " + pass);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    @Override
    public void login() {
        while (true) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (containsUsername(username)) {
                if ((user.get(username)).equals(password)) {
                    setUsername(username);
                    setPassword(password);
                    System.out.println("Login successful!\n");
                    System.out.println();
                    break; // Exit loop on successful login
                } else {
                    System.out.println("Incorrect password! Try again or enter 'exit' to quit.");
                }
            } else {
                System.out.println("Login failed! Username does not exist.");
                System.out.println("Enter 'retry' to try again or 'exit' to quit.");
                System.out.println();
            }

            // Prompt for retry or exit
            System.out.print("Do you want to retry or exit? (retry/exit): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("exit")) {
                System.out.println("Exiting login process.\n");
                System.exit(0); // Exit the program if user chooses to exit
            }
            // Loop will continue if user types "retry" or any other input apart from "exit"
        }
        
    }
    
    @Override
    public void displayDashboard() {
        while (true) {
        	System.out.println("\nUser Dashboard\n--------------");
            System.out.println("1. Add a New Recipe");
            System.out.println("2. View All Recipes");
            System.out.println("3. Logout");
            
            System.out.print("\nPlease select an option by entering a number: ");
            int choice = 0;
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
                    recipeManager.displayAllRecipes();
                    continue;
                case 3:
                    logout();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2 or 3.");
                    System.out.println();
            }
        }
    }

    @Override
    public void logout() {
        setUsername(null);
        setPassword(null);
        System.out.println("Logged out user successfully!");
        System.out.println();
        System.exit(0);
    }
}
