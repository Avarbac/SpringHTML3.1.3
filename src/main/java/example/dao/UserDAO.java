package example.dao;


import example.models.User;

import java.util.List;

public interface UserDAO {

    User getUserById(int id);

    List<User> getAllUsers();

    void saveUser(User user);

    void deleteUser(User user);

    public void updateUser(User user);

    User getUserByName(String name);
}