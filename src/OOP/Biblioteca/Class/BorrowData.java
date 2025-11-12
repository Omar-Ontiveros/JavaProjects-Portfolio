package OOP.Biblioteca.Class;

public class BorrowData {
    private int userId;
    private int bookId;

    public BorrowData() {
    }

    public BorrowData(int userId, int bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "UserID: " + userId + " → BookID: " + bookId;
    }
}
