package OOP.Biblioteca.Class;

public class BookData {
    private int bookId;
    private String title;
    private String author;
    private String isbn;
    private String status;
    private String genre;
    private String publisher;
    private int yearPublished;
    private int pages;
    private int stock;
    private int borrowed;

    public BookData() {
    }

    public BookData(int bookId, String title, String author, String isbn, String status,
            String genre, String publisher, int yearPublished, int pages, int stock, int borrowed) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.status = status;
        this.genre = genre;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.pages = pages;
        this.stock = stock;
        this.borrowed = borrowed;
    }

    // Getters y Setters
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(int borrowed) {
        this.borrowed = borrowed;
    }

    @Override
    public String toString() {
        return bookId + " - " + title + " (" + author + ") | Stock: " + stock;
    }
}
