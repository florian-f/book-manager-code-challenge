package buecherverwaltung.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;

@JsonPropertyOrder({"title", "isbn", "authors", "date"})
public class Magazine extends Publication {

    @JsonProperty("date")
    private Date date;

    public Date getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return super.toString() + " Datum: " + this.date;
    }
}
