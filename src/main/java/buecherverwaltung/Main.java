package buecherverwaltung;

import buecherverwaltung.model.Author;
import buecherverwaltung.model.Book;
import buecherverwaltung.model.Magazine;
import buecherverwaltung.model.Publication;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        CSVUtil csvUtil = new CSVUtil();
        List<Author> authorList = csvUtil.readAuthors();
        List<Book> bookList = csvUtil.readBooks();
        List<Magazine> magazineList = csvUtil.readMagazines();

        BookManager bookManager = new BookManager(authorList, bookList, magazineList);
        System.out.println("Alle Bücher/ Zeitschriften mit allen Details ausgeben:");
        for(Publication p: bookManager.getPublications()) {
            System.out.println(p);
        }

        System.out.println();
        System.out.println("Anhand einer ISBN-Nummer ein Buch / eine Zeitschrift finden und ausgeben:");
        System.out.println("ISBN: 2547-8548-2541");
        System.out.println(bookManager.findByISBN("2547-8548-2541"));

        System.out.println();
        System.out.println("Alle Bücher / Zeitschriften eines Autors finden und ausgeben:");
        System.out.println("Author: Paul Walter");
        System.out.println(bookManager.findByAuthor("Paul", "Walter"));

        System.out.println();
        System.out.println("Alle Bücher / Zeitschriften nach Titel sortieren und ausgeben:");
        for(Publication p: bookManager.getPublicationsSorted()) {
            System.out.println(p);
        }
    }

}
