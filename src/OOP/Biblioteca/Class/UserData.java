package OOP.Biblioteca.Class;

public class UserData {
    private int userId;
    private String name;
    private String userType;
    private String email;

    public UserData() {
    }

    public UserData(int userId, String name, String userType, String email) {
        this.userId = userId;
        this.name = name;
        this.userType = userType;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return userId + " - " + name + " (" + userType + ")";
    }
}
