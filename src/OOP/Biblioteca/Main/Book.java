package OOP.Biblioteca.Main;

import OOP.Biblioteca.Interfacez.BookActions;
import OOP.Biblioteca.Class.BookData;
import java.util.HashMap;
import java.util.Map;

public class Book extends BookData implements BookActions {
    private final Map<Integer, Book> catalog = new HashMap<>();

    public void addBook(int bookId, String title, String author, String isbn, String status,
            String genre, String publisher, int yearPublished, int pages, int stock, int borrowed) {
        Book newBook = new Book();
        newBook.setBookId(bookId);
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setIsbn(isbn);
        newBook.setStatus(status);
        newBook.setGenre(genre);
        newBook.setPublisher(publisher);
        newBook.setYearPublished(yearPublished);
        newBook.setPages(pages);
        newBook.setStock(stock);
        newBook.setBorrowed(borrowed);
        catalog.put(bookId, newBook);
    }

    public void removeBook(int bookId, int quantity) {
        Book b = catalog.get(bookId);
        if (b != null) {
            b.setStock(Math.max(0, b.getStock() - quantity));
        }
    }

    public void updateBookInfo(int bookId) {
        Book b = catalog.get(bookId);
        if (b != null)
            b.setStock(b.getStock() + 1);
    }

    public void showBooks() {
        catalog.values().forEach(System.out::println);
    }

    public Book findBook(int id) {
        return catalog.get(id);
    }
}
