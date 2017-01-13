package buecherverwaltung.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Publication {

    @JsonProperty("title")
    private String title;
    @JsonProperty("isbn")
    private String isbn;
    @JsonProperty("authors")
    private List<String> authors;

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public List<String> getAuthors() {
        return authors;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Titel: " + this.getTitle());
        stringBuilder.append(" ISBN: " + this.getIsbn());
        stringBuilder.append(" Author(en):");
        if (authors != null) {
            for (String s: authors) {
                stringBuilder.append(" " + s);
            }
        }
        return stringBuilder.toString();
    }
}
