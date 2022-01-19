package example.service;



import example.models.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface UserService {

    User getUserById(int id);

    List<User> getAllUsers();

    void saveUser(User user);

    void deleteUser(User user);

    void updateUser(User user);

    User getUserByName(String name);
}
