package service;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static List <User> listOfUsers = new ArrayList <>();

    private static UserService ourInstance = new UserService();

    public static UserService getInstance() {
        return ourInstance;
    }

    private UserService() {
        listOfUsers.add(new User("Sandu", "Petrasco", "godzilla", "asdi123ns", "Romania", "Splaiul Independentei 204"));
        listOfUsers.add(new User("Ion", "Popescu", "ipopescu", "jfj221ksa9", "Romania", "Iuliu Maniu 207"));
    }

    public static List<User> getListOfUsers() {
        return listOfUsers;
    }

    public User getUserByUsername(String username) {
        for (User user: listOfUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
    }
}
