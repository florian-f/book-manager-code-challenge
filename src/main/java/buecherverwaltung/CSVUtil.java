package buecherverwaltung;

import buecherverwaltung.model.Author;
import buecherverwaltung.model.Book;
import buecherverwaltung.model.Magazine;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class CSVUtil {

    private CsvMapper csvMapper;

    private static final String bookPath = "src/main/resources/buecher.csv";
    private static final String magazinePath = "src/main/resources/zeitschriften.csv";
    private static final String authorPath = "src/main/resources/autoren.csv";

    public CSVUtil() {
        csvMapper = new CsvMapper();
        csvMapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);

    }

    public List<Book> readBooks() throws IOException {

        CsvSchema.Builder schemaBuilder = new CsvSchema.Builder();
        schemaBuilder.addColumn("title", CsvSchema.ColumnType.STRING);
        schemaBuilder.addColumn("isbn", CsvSchema.ColumnType.STRING);
        schemaBuilder.addColumn("authors", CsvSchema.ColumnType.ARRAY);
        schemaBuilder.addColumn("description", CsvSchema.ColumnType.STRING);

        CsvSchema csvSchema = schemaBuilder.build().withColumnSeparator(';').withLineSeparator("\r\n").withHeader();

        return Arrays.asList((Book[]) csvMapper.readerFor(Book[].class).with(csvSchema).readValue(new InputStreamReader(new FileInputStream(new File(bookPath)), "MacCyrillic")));
    }

    public List<Magazine> readMagazines() throws IOException {
        File csvFile = new File(magazinePath);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        csvMapper.setDateFormat(dateFormat);

        // configure delimiter and line separator
        CsvSchema csvSchema = csvMapper.schemaFor(Magazine.class).withColumnSeparator(';').withLineSeparator("\r\n").withHeader();

        return csvMapper.readerFor(new TypeReference<List<Magazine>>() {
        }).with(csvSchema).readValue(new InputStreamReader(new FileInputStream(csvFile), "ISO-8859-1"));
    }

    public List<Author> readAuthors() throws IOException {
        File csvFile = new File(authorPath);

        // configure delimiter and line separator
        CsvSchema csvSchema = csvMapper.schemaFor(Author.class)
                .withColumnSeparator(';')
                .withLineSeparator("\r\n")
                .withHeader();

        return csvMapper.readerFor(new TypeReference<List<Author>>() {})
                .with(csvSchema)
                .readValue(new InputStreamReader(new FileInputStream(csvFile), "ISO-8859-1"));
    }
}
