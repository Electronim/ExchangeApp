package DAO;

import model.User;

import java.util.List;

public interface UserDAO {
    void createUserTable();
    void insert(User user);
    void update(int id, User user);
    void delete(int id);
    User selectUserById(int id);
    void insertAll(List<User> userList);
    List<User> selectAll();
}
