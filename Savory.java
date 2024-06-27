package Recipe;

public class Savory extends Recipe {
    private boolean isSpicy;

    public Savory(String title, String description, boolean isSpicy) {
        super(title, description, "Savory");
        this.isSpicy = isSpicy;
    }

    @Override
    public String toPropertiesFormat() {
        return super.toPropertiesFormat() + ", IsSpicy=" + isSpicy;
    }
}
