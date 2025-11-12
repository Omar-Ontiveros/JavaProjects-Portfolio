package OOP.Biblioteca.Interfacez;

public interface BorrowActions {
    void borrowBook(int userId, int bookId);
    void returnBook(int userId, int bookId);
    void showBorrows();
}
