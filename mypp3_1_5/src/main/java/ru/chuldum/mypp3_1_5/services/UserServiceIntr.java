package ru.chuldum.mypp3_1_5.services;

import ru.chuldum.mypp3_1_5.entities.User;

import java.util.List;

public interface UserServiceIntr {

    List<User> getAllUsers();

    void save(User user);

    void deleteViaId(Long id);

    User findByUsername(String username);

}
