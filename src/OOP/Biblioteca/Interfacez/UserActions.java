package OOP.Biblioteca.Interfacez;

public interface UserActions {
    void addUser(int userId, String name, String userType, String email);
    void removeUser(int userId);
    void showUsers();
}
