package models;

public class User {
    private String name;
    private String username;
    private String company;

    public User(final String name, final String username, final String company) {
        this.name = name;
        this.username = username;
        this.company = company;
    }

    public String getUsername() {
        return username;
    }

    public String getCompany() {
        return company;
    }

    public String getName() {
        return name;
    }
}
