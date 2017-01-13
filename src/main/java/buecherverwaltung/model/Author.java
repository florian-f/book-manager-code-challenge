package buecherverwaltung.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"mail", "firstName", "lastName"})
public class Author {

    private String mail;
    private String firstName;
    private String lastName;

    public String getMail() {
        return mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Emailadresse: " + mail + " Vorname: " + firstName + " Nachname: " + lastName;
    }
}
