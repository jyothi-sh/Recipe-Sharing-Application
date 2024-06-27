package Recipe;

public class NonVegetarian extends Savory {
    private String meatType;

    public NonVegetarian(String title, String description, boolean isSpicy, String meatType) {
        super(title, description, isSpicy);
        this.type = "NonVegetarian Savory";
        this.meatType = meatType;
    }

    @Override
    public String toPropertiesFormat() {
        return super.toPropertiesFormat() + ", MeatType=" + meatType;
    }
}
