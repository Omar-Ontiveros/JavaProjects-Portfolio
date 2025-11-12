package OOP.Biblioteca.Main;

import java.util.Scanner;

public class MainLibrary {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        int option;

        do {
            System.out.println("\n=== LIBRARY SYSTEM ===");
            System.out.println("1. Manage Books");
            System.out.println("2. Manage Users");
            System.out.println("3. Manage Borrows");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            option = sc.nextInt();

            switch (option) {
                case 1 -> manageBooks(library, sc);
                case 2 -> manageUsers(library, sc);
                case 3 -> manageBorrows(library, sc);
            }
        } while (option != 0);

        sc.close();
    }

    private static void manageBooks(Library library, Scanner sc) {
        int op;
        do {
            System.out.println("\n-- BOOK MENU --");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Update Stock");
            System.out.println("4. Show Books");
            System.out.println("0. Back");
            System.out.print("Option: ");
            op = sc.nextInt();

            switch (op) {
                case 1 -> {
                    library.bookManager.addBook(101, "Java Programming", "John Doe", "1234567890", "Available", "Programming", "TechBooks", 2020, 500, 10, 0);
                    library.bookManager.addBook(102, "Python Essentials", "Jane Smith", "0987654321", "Available", "Programming", "CodeBooks", 2021, 350, 5, 0);
                    System.out.println("Books added!");
                }
                case 2 -> {
                    System.out.print("Enter book ID: ");
                    int id = sc.nextInt();
                    library.bookManager.removeBook(id, 1);
                    System.out.println("Book stock updated!");
                }
                case 3 -> {
                    System.out.print("Enter book ID: ");
                    int id = sc.nextInt();
                    library.bookManager.updateBookInfo(id);
                    System.out.println("Stock increased by 1!");
                }
                case 4 -> library.bookManager.showBooks();
            }
        } while (op != 0);
    }

    private static void manageUsers(Library library, Scanner sc) {
        int op;
        do {
            System.out.println("\n-- USER MENU --");
            System.out.println("1. Add User");
            System.out.println("2. Remove User");
            System.out.println("3. Show Users");
            System.out.println("0. Back");
            System.out.print("Option: ");
            op = sc.nextInt();

            switch (op) {
                case 1 -> {
                    library.userManager.addUser(1, "Alice", "Student", "alice@mail.com");
                    library.userManager.addUser(2, "Bob", "Teacher", "bob@mail.com");
                    System.out.println("Users added!");
                }
                case 2 -> {
                    System.out.print("Enter user ID: ");
                    int id = sc.nextInt();
                    library.userManager.removeUser(id);
                    System.out.println("User removed!");
                }
                case 3 -> library.userManager.showUsers();
            }
        } while (op != 0);
    }

    private static void manageBorrows(Library library, Scanner sc) {
        int op;
        do {
            System.out.println("\n-- BORROW MENU --");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. Show Borrows");
            System.out.println("0. Back");
            System.out.print("Option: ");
            op = sc.nextInt();

            switch (op) {
                case 1 -> {
                    System.out.print("User ID: ");
                    int userId = sc.nextInt();
                    System.out.print("Book ID: ");
                    int bookId = sc.nextInt();
                    library.borrowManager.borrowBook(userId, bookId);
                    System.out.println("Book borrowed!");
                }
                case 2 -> {
                    System.out.print("User ID: ");
                    int userId = sc.nextInt();
                    System.out.print("Book ID: ");
                    int bookId = sc.nextInt();
                    library.borrowManager.returnBook(userId, bookId);
                    System.out.println("Book returned!");
                }
                case 3 -> library.borrowManager.showBorrows();
            }
        } while (op != 0);
    }
}
