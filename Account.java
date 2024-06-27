package Account;

public abstract class Account {
    private String username;
    private String password;
    
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public Account() {
    	
    }

    public abstract void register();
    public abstract void login();
    public abstract void logout();
    public abstract void displayDashboard();

    // Getters and setters for encapsulation
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}