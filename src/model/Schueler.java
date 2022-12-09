package model;

public record Schueler(String firstName, String lastName, String sid) {

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSid() {
        return sid;
    }
}
