package OOP.Biblioteca.Main;

import OOP.Biblioteca.Interfacez.BorrowActions;
import OOP.Biblioteca.Class.BorrowData;
import java.util.ArrayList;
import java.util.List;

public class Borrow extends BorrowData implements BorrowActions {
    private final List<Borrow> borrows = new ArrayList<>();

    public void borrowBook(int userId, int bookId) {
        borrows.add(new Borrow(userId, bookId));
    }

    public void returnBook(int userId, int bookId) {
        borrows.removeIf(b -> b.getUserId() == userId && b.getBookId() == bookId);
    }

    public void showBorrows() {
        borrows.forEach(System.out::println);
    }

    public Borrow(int userId, int bookId) {
        super(userId, bookId);
    }

    public Borrow() {}
}
