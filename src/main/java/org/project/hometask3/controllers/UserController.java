package org.project.hometask3.controllers;

import org.project.hometask3.domain.User;
import org.project.hometask3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getUserRepository().getAllUsers();
    }

    @GetMapping("/sorted")
    public List<User> getSortedUserListByAge(){
        return userService.getUserRepository().getAllUsers().stream()
                .sorted(Comparator.comparing(User::getAge))
                .toList();
    }

    @PostMapping("/add")
    public String addUserToList(@RequestBody User user){
        userService.getUserRepository().getAllUsers().add(user);
        return "User added to repository";
    }

    @DeleteMapping("/delete/{name}")
    public String deleteUserFromList(@PathVariable String name){
        userService.getUserRepository().getAllUsers().removeIf(user -> user.getAge()== user.getAge());
        return "User deleted";
    }

    @GetMapping("/average")
    public double getAverageAge(){
        return userService.getUserRepository().getAllUsers().stream()
                .mapToInt(User::getAge).average()
                .getAsDouble();
    }

    @GetMapping("/filter/{age}")
    public List<User> GetUsersFilteredByAge(@PathVariable int age){
        return userService.getUserRepository().getAllUsers().stream()
                .filter(user -> user.getAge()>=age)
                .toList();
    }


}
