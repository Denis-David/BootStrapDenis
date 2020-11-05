package net.basil.crudspringboot.repository;

import net.basil.crudspringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByName(String name);

}
