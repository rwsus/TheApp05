package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void saveUser(String name, String lastName, int age);
    void updateUser(long id, User updatedUser);
    User findUserById(long id);

    void removeUserById(long id);
}
