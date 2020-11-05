package net.basil.crudspringboot.repository;

import net.basil.crudspringboot.model.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Set<Role> getRolesByNameIn(Set<String> roles);

    Role getRoleByName(String defaultRoleName);

}
