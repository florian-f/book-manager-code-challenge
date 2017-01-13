package buecherverwaltung;

import buecherverwaltung.model.Author;
import buecherverwaltung.model.Book;
import buecherverwaltung.model.Publication;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class BookManagerTest {

    @Test
    public void findByAuthor() throws Exception {
        Author author1 = Mockito.mock(Author.class);
        Author author2 = Mockito.mock(Author.class);
        Book publication1 = Mockito.mock(Book.class);
        Book publication2 = Mockito.mock(Book.class);

        when(author1.getFirstName()).thenReturn("Walter");
        when(author1.getLastName()).thenReturn("Optivo");
        when(author1.getMail()).thenReturn("pr-walter@optivo.de");
        when(author2.getFirstName()).thenReturn("Ferdinand");
        when(author2.getLastName()).thenReturn("Optivo");
        when(author2.getMail()).thenReturn("pr-ferdinand@optivo.de");

        List<String> authors1 = new ArrayList<>();
        authors1.add("pr-walter@optivo.de");

        List<String> authors2 = new ArrayList<>();
        authors2.add("pr-walter@optivo.de");
        authors2.add("pr-ferdinand@optivo.de");

        when(publication1.getAuthors()).thenReturn(authors1);
        when(publication2.getAuthors()).thenReturn(authors2);

        List<Book> publications = new ArrayList<>();
        publications.add(publication1);
        publications.add(publication2);

        List<Author> authorList = new ArrayList<>();
        authorList.add(author1);
        authorList.add(author2);

        BookManager bookManager = new BookManager(authorList, publications, new ArrayList<>());

        List<Publication> findWalter = bookManager.findByAuthor("Walter", "Optivo");
        Assert.assertTrue(findWalter.size() == 2);
        for (Publication publication : findWalter) {
            Assert.assertTrue(publication.getAuthors().contains("pr-walter@optivo.de"));
        }

        List<Publication> findFerdinand = bookManager.findByAuthor("Ferdinand", "Optivo");
        Assert.assertTrue(findFerdinand.size() == 1);
        for (Publication publication : findFerdinand) {
            Assert.assertTrue(publication.getAuthors().contains("pr-ferdinand@optivo.de"));
        }
    }

    @Test
    public void findByISBN() throws Exception {
        Book publication1 = Mockito.mock(Book.class);
        Book publication2 = Mockito.mock(Book.class);

        when(publication1.getIsbn()).thenReturn("1215-4545-5895");
        when(publication2.getIsbn()).thenReturn("4545-8541-2012");

        List<Book> publications = new ArrayList<>();
        publications.add(publication1);
        publications.add(publication2);

        BookManager bookManager = new BookManager(new ArrayList<>(), publications, new ArrayList<>());

        Assert.assertEquals(bookManager.findByISBN("4545-8541-2012").getIsbn(), "4545-8541-2012");

    }

    @Test
    public void getPublicationsSorted() throws Exception {
        Book publication1 = Mockito.mock(Book.class);
        Book publication2 = Mockito.mock(Book.class);

        when(publication1.getTitle()).thenReturn("asdf");
        when(publication2.getTitle()).thenReturn("fdsa");

        List<Book> publications = new ArrayList<>();
        publications.add(publication1);
        publications.add(publication2);

        BookManager bookManager = new BookManager(new ArrayList<>(), publications, new ArrayList<>());

        List<Publication> sorted = bookManager.getPublicationsSorted();
        Assert.assertTrue(sorted.size() == 2);
        Assert.assertTrue(sorted.get(0).getTitle().equals("asdf"));
    }
}