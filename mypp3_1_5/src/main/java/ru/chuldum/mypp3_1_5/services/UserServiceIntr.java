package ru.chuldum.mypp3_1_5.services;

import ru.chuldum.mypp3_1_5.entities.User;

import java.util.List;

public interface UserServiceIntr {

    public List<User> getAllUsers();

    public void save(User user);

    public void deleteViaId(Long id);

    public User findByUsername(String username);

}
