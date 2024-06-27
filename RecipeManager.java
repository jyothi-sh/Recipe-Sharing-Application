package Recipe;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Properties;

public class RecipeManager {
    private Properties recipes;
    private String filePath;
    private Scanner scanner;

    public RecipeManager() {
        this.filePath = "/Users/jayma/Desktop/Drugs/CS 151/CS151Project/src/Recipe/RecipeList.properties"; 
        this.recipes = new Properties();
        this.scanner = new Scanner(System.in);
        loadRecipes();
    }

    private void loadRecipes() {
        try (FileInputStream in = new FileInputStream(filePath)) {
            recipes.load(in);
        } catch (IOException e) {
            System.err.println("Failed to load recipes: " + e.getMessage());
        }
    }

    public void createRecipe() {
    	while (true) {
    		System.out.println("Select the type of recipe:");
            System.out.println("1. Savory");
            System.out.println("2. Sweets");
            System.out.print("Enter choice (1 or 2): ");
            int choice = 0;
            try {
            	choice = scanner.nextInt();  // Read the next integer from the scanner

            } catch (InputMismatchException e) {
                scanner.nextLine();  // Consume the invalid input to clear the scanner
            }
            

            switch (choice) {
                case 1:
                    createSavoryRecipe();
                    return;
                case 2:
                    createSweetRecipe();
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                    System.out.println();
            }
    	}
        
    }

    private void createSavoryRecipe() {
    	while (true) {
    		System.out.println("Select the type of savory recipe:");
            System.out.println("1. Vegetarian");
            System.out.println("2. Non-Vegetarian");
            System.out.print("Enter choice (1 or 2): ");
            int choice = 0;
            try {
            	choice = scanner.nextInt();  // Read the next integer from the scanner
                scanner.nextLine(); // Consume the newline

            } catch (InputMismatchException e) {
            	scanner.nextLine();
            	System.out.println("Invalid choice. Please enter 1 or 2.\n");
            	continue;
            }

            System.out.print("Enter title: ");
            String title = scanner.nextLine();
            System.out.print("Enter description: ");
            String description = scanner.nextLine();
            System.out.print("Is it spicy? (true/false): ");
            boolean isSpicy;
            try {
            	isSpicy = scanner.nextBoolean();
                scanner.nextLine(); // Consume the newline

            } catch (InputMismatchException e) {
            	scanner.nextLine();
            	System.out.println("Invalid choice. Please enter true or false.\n");
            	continue;
            }

            Recipe recipe = null;
            
            
            if (choice == 1) {
                recipe = new Vegetarian(title, description, isSpicy);
            } else if (choice == 2) {
                System.out.print("Enter meat type: ");
                scanner.nextLine();  // Consume newline left-over
                String meatType = scanner.nextLine();
                recipe = new NonVegetarian(title, description, isSpicy, meatType);
            }

            if (recipe != null) {
                addRecipe(recipe);
                System.out.println("\nRecipe Added Successfully!");
                System.out.println();
                return;
            }
    	}
        
    }

    private void createSweetRecipe() {
    	while (true) {
    		System.out.println("Select the type of sweet recipe:");
            System.out.println("1. Hot");
            System.out.println("2. Cold");
            System.out.print("Enter choice (1 or 2): ");
            int choice = 0;
            try {
            	choice = scanner.nextInt();  // Read the next integer from the scanner
                scanner.nextLine();  // Consume the invalid input to clear the scanner

            } catch (InputMismatchException e) {
                scanner.nextLine();  // Consume the invalid input to clear the scanner
                System.out.println("Invalid choice. Please enter 1 or 2.\n");
            	continue;
                
            }

            System.out.print("Enter title: ");
            String title = scanner.nextLine();
            System.out.print("Enter description: ");
            String description = scanner.nextLine();
            System.out.print("Enter sweetness level (1-10): ");
            int sweetnessLevel = scanner.nextInt();

            Recipe recipe = null;
            if (choice == 1) {
                recipe = new Hot(title, description, sweetnessLevel);
            } else if (choice == 2) {
                recipe = new Cold(title, description, sweetnessLevel);
            }

            if (recipe != null) {
                addRecipe(recipe);
                System.out.println("\nRecipe Added Successfully!");
                System.out.println();
                break;
            }
    	}
        
    }

    public void addRecipe(Recipe recipe) {
        recipes.setProperty(recipe.getTitle(), recipe.toPropertiesFormat());
        saveRecipes();
    }

    public void saveRecipes() {
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            recipes.store(out, "Recipes Storage");
        } catch (IOException e) {
            System.err.println("Failed to save recipes: " + e.getMessage());
        }
    }

    public void displayAllRecipes() {
        recipes.forEach((key, value) -> System.out.println(key + " => " + value));
    }
    
    public void deleteRecipe() {
        System.out.print("\nEnter the title of the recipe to delete: ");
        String title = scanner.nextLine();

        if (recipes.containsKey(title)) {
            recipes.remove(title);
            saveRecipes();
            System.out.println("Recipe deleted successfully.");
            System.out.println();
        } else {
            System.out.println("Recipe not found.");
            System.out.println();
        }
    }
}

