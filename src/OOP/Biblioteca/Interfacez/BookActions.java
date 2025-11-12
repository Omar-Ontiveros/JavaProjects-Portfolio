package OOP.Biblioteca.Interfacez;

public interface BookActions {
    void addBook(int bookId, String title, String author, String isbn, String status,
            String genre, String publisher, int yearPublished, int pages, int stock, int borrowed);

    void removeBook(int bookId, int quantity);

    void updateBookInfo(int bookId);

    void showBooks();
}
