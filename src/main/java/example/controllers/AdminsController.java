package example.controllers;


import example.models.User;
import example.service.RoleService;
import example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminsController {

    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminsController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "")
    public String printAllUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.allRoles());
        return "index";
    }

    @GetMapping("/{id}")
    public String printUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @GetMapping("/add_user")
    public String newUser(Model model) {
        model.addAttribute("roles", roleService.allRoles());
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("")
    public String addUser(@ModelAttribute("addUser") User user,
                          @RequestParam(value = "nameRoles") String[] nameRoles) {
        user.setRoles(roleService.getSetOfRoles(nameRoles));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String getUserById(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.allRoles());
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @PathVariable("id") int id,
                             @RequestParam(value = "nameRoles") String[] nameRoles) {
        user.setRoles(roleService.getSetOfRoles(nameRoles));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        userService.deleteUser(user);
        return "redirect:/admin";
    }

}