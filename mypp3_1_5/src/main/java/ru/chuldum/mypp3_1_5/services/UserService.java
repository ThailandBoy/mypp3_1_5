package ru.chuldum.mypp3_1_5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chuldum.mypp3_1_5.entities.Role;
import ru.chuldum.mypp3_1_5.entities.User;
import ru.chuldum.mypp3_1_5.entities.UserRoleWrap;
import ru.chuldum.mypp3_1_5.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService, UserServiceIntr {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleServiceIntr roleServiceIntr;

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public void saveWrapper(UserRoleWrap userRoleWrap) {
        User user = userRoleWrap.getUser();
        user.setPassword(passwordEncoder.encode(userRoleWrap.getUser().getPassword()));
        List<Role> roleList = new ArrayList<>();
        for (Long roleId : userRoleWrap.getRoleList2()) {
            roleList.add(roleServiceIntr.getById(roleId));
        }
        user.setRoles(roleList);
        save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteViaId(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return user;
    }
}
