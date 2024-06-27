package Recipe;

public class Sweets extends Recipe {
    private int sweetnessLevel;

    public Sweets(String title, String description, int sweetnessLevel) {
        super(title, description, "Sweets");
        this.sweetnessLevel = sweetnessLevel;
    }

    @Override
    public String toPropertiesFormat() {
        return super.toPropertiesFormat() + ", SweetnessLevel=" + sweetnessLevel;
    }
}
