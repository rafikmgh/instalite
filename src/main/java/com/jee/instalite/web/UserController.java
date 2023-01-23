package com.jee.instalite.web;

import com.jee.instalite.model.User;
import com.jee.instalite.service.UserService;
import com.jee.instalite.web.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin()
@RestController

public class UserController {

    private UserService userService ;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET, produces="text/html")
    public String showRegistrationForm() {
        return "<h1>registration</h1>";
    }

    @RequestMapping(value = "/register" , method =RequestMethod.POST , produces="text/html" )
    public String registerUserAccount(@ModelAttribute("user") UserDto registrationDto) {
         userService.save(registrationDto);
         return "success";
    }

    @RequestMapping(value = "/users/{id}" , method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if(userService.getUserById(id) == null){
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/users/{id}" , method = RequestMethod.PUT , produces="text/html")
    public String updateUser(@PathVariable Long id , @ModelAttribute("user") UserDto userDto) {
        if(userService.getUserById(id) == null){
            return "user not found";
        }
        userService.updateUser(id, userDto);
        return "";
    }

    @RequestMapping(value = "/users" , method = RequestMethod.GET , produces = "text/html")
    public String showAllUsers() {

        List<User> users = userService.getAllUsers();

        return "all users page" ;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

/*    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session) {
        User user = userService.authenticateUser(email, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }*/



}
