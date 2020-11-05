package net.basil.crudspringboot.controller;

import net.basil.crudspringboot.model.User;
import net.basil.crudspringboot.util.Transformation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/user/**")
public class UserViewController {

    private Transformation transformation;

    public UserViewController(Transformation transformation) {
        this.transformation = transformation;
    }

    @GetMapping("userinfo")
    public ModelAndView showUserForm(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView("/user/userinfo");
        String stringRoles = transformation.roleSetToString(user);
        modelAndView.addObject("userData", user);
        modelAndView.addObject("userRoles", stringRoles);
        return modelAndView;
    }

}
