package ru.chuldum.mypp3_1_5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chuldum.mypp3_1_5.entities.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
