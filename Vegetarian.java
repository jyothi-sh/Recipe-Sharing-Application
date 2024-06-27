package Recipe;

public class Vegetarian extends Savory {
    public Vegetarian(String title, String description, boolean isSpicy) {
        super(title, description, isSpicy);
        this.type = "Vegetarian Savory";
    }
}

