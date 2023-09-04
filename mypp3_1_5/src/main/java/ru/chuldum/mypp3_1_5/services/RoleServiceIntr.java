package ru.chuldum.mypp3_1_5.services;

import ru.chuldum.mypp3_1_5.entities.Role;

import java.util.List;

public interface RoleServiceIntr {
    List<Role> getAllRoles();

    Role getById(Long id);
}
