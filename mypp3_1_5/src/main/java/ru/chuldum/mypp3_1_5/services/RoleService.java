package ru.chuldum.mypp3_1_5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chuldum.mypp3_1_5.entities.Role;
import ru.chuldum.mypp3_1_5.repositories.RoleRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RoleService implements RoleServiceIntr {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Role with id=" + id + " not found."));
    }
}
