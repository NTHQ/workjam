package com.workjam.interview.controller;

import com.workjam.interview.objects.UserFilter;
import com.workjam.interview.objects.user.User;
import com.workjam.interview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /*
     * Write a method that handles a RESTful GET request and returns users from UserService in the JSON format.
     * Important: when returning users, the birthday date must not be included in the transferred data.
     *
     * Add a path parameter for the company ID.
     * Add an optional parameter to filter users based on user permissions (admin, manager or employee).
     * Add an optional parameter to filter users based the active state.
     *
     * @see UserService
     * @see User.Permission
     */
    @GetMapping("/getUser")
    public List<User> getUser(
            @RequestParam(value = "companyId") int companyId,
            @RequestParam(value = "permission", required = false) User.Permission permission,
            @RequestParam(value = "active", required = false) Boolean active) {

        UserFilter filter = null;
        if (permission != null || active != null) {
            filter = new UserFilter(active, permission);
        }

        return userService.getUsers(companyId, filter);
    }

    /*
     * Write a method that handles a RESTful POST request that adds a user using the UserService.
     *
     * @see UserService
     */
}
