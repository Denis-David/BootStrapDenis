package net.basil.crudspringboot.util;

import net.basil.crudspringboot.model.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Transformation {

    public String roleSetToString(User user) {
        return   user.getRoleSet()
                .stream()
                .map(r -> r.getName().substring(5))
                .collect(Collectors.joining(" "));
    }

}
