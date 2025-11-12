package OOP.Biblioteca.Main;

import OOP.Biblioteca.Interfacez.UserActions;
import OOP.Biblioteca.Class.UserData;
import java.util.HashMap;
import java.util.Map;

public class User extends UserData implements UserActions {
    private final Map<Integer, User> users = new HashMap<>();

    public void addUser(int userId, String name, String userType, String email) {
        User u = new User();
        u.setUserId(userId);
        u.setName(name);
        u.setUserType(userType);
        u.setEmail(email);
        users.put(userId, u);
    }

    public void removeUser(int userId) {
        users.remove(userId);
    }

    public void showUsers() {
        users.values().forEach(System.out::println);
    }

    public User findUser(int id) {
        return users.get(id);
    }
}
