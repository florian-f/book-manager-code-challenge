package buecherverwaltung;

import buecherverwaltung.model.Author;
import buecherverwaltung.model.Book;
import buecherverwaltung.model.Magazine;
import buecherverwaltung.model.Publication;

import java.util.*;
import java.util.stream.Collectors;

public class BookManager {

    private List<Author> authors;
    private List<Publication> publications;

    public BookManager(List<Author> authors, List<Book> books, List<Magazine> magazines) {
        this.authors = new ArrayList<>();
        this.authors.addAll(authors);
        this.publications = new ArrayList<>();
        this.publications.addAll(books);
        this.publications.addAll(magazines);
    }

    public List<Publication> findByAuthor(String firstName, String lastName) {
        Optional<Author> authorOptional = authors.stream().filter(author ->
            author.getFirstName().equals(firstName) && author.getLastName().equals(lastName)
        ).findFirst();
        if (authorOptional.isPresent()) {
            return publications.stream().filter(publication -> publication.getAuthors().contains(authorOptional.get().getMail())).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public Publication findByISBN(String isbn) {
        Optional<Publication> pub = publications.stream().filter(publication -> publication.getIsbn().equals(isbn)).findFirst();
        if (pub.isPresent()) {
            return pub.get();
        }
        return null;
    }


    public List<Publication> getPublicationsSorted() {
        List<Publication> publicationList = new ArrayList<>(publications);
        publicationList.sort((publication1, publication2) -> publication1.getTitle().compareTo(publication2.getTitle()));
        return publicationList;
    }

    public List<Publication> getPublications() {
        return Collections.unmodifiableList(publications);
    }
}
