package Recipe;

public abstract class Recipe {
    protected String title;
    protected String description;
    protected String type;  

    public Recipe(String title, String description, String type) {
        this.title = title;
        this.description = description;
        this.type = type;
    }

    public String toPropertiesFormat() {
        return "Title=" + title + ", Description=" + description + ", Type=" + type;
    }

    // Getters and setters omitted for brevity
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
