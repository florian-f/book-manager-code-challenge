package buecherverwaltung.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book extends Publication {

    @JsonProperty("description")
    private String description;

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return super.toString() + " Kurzbeschreibung: " + this.description;
    }
}
