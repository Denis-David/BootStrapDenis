package net.basil.crudspringboot.controller;

import net.basil.crudspringboot.model.User;
import net.basil.crudspringboot.service.RoleService;
import net.basil.crudspringboot.service.UserService;
import net.basil.crudspringboot.util.Transformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/**")
public class AdminViewController {

    private UserService userService;
    private RoleService roleService;
    private Transformation transformation;

    @Autowired
    public AdminViewController(UserService userService, RoleService roleService, Transformation transformation) {
        this.userService = userService;
        this.roleService = roleService;
        this.transformation = transformation;
    }

    @GetMapping("usertable")
    public ModelAndView printUserTable(@AuthenticationPrincipal User admin) {
        return getModelAndView(admin);
    }

    @PostMapping("delete")
    public ModelAndView deleteUser(@RequestParam("id") Long id, @AuthenticationPrincipal User admin) {
        userService.deleteUser(id);
        return getModelAndView(admin);
    }

    @PostMapping("updateUser")
    public ModelAndView updateUser(@RequestParam("roles") Set<String> roles, @AuthenticationPrincipal User admin, User user) {
        user.setRoleSet(roleService.getRoleSet(roles));
        userService.updateUser(user);
        return getModelAndView(admin);
    }

    @PostMapping("adduser")
    public ModelAndView createUser(@RequestParam("roles") Set<String> roles, @AuthenticationPrincipal User admin, User user) {
        user.setRoleSet(roleService.getRoleSet(roles));
        userService.addUser(user);
        return getModelAndView(admin);
    }

    private ModelAndView getModelAndView(User admin) {
        User user = new User();
        ModelAndView modelAndView = new ModelAndView("admin/usertable");
        String stringRoles = transformation.roleSetToString(admin);
        modelAndView.addObject("adminInfo", admin)
                .addObject("user", user)
                .addObject("userRoles", stringRoles)
                .addObject("userData", userService.getAllUsers());
        return modelAndView;
    }

}