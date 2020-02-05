package com.workjam.interview.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workjam.interview.objects.UserFilter;
import com.workjam.interview.objects.user.Admin;
import com.workjam.interview.objects.user.Employee;
import com.workjam.interview.objects.user.Manager;
import com.workjam.interview.objects.user.User;
import com.workjam.interview.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    ObjectMapper mapper;

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
    @PostMapping("addUser")
    public void addUser(@RequestBody String userJson) {
        try {
            JSONObject jsonObj = new JSONObject(userJson);
            int companyId = Integer.valueOf(jsonObj.get("companyId").toString());
            String permissionS = (String) jsonObj.get("permission");
            User.Permission permission = User.Permission.valueOf(permissionS);
            User userObj;

            switch (permission) {
                case ADMIN:
                    userObj = mapper.readValue(userJson, Admin.class);
                    break;
                case MANAGER:
                    userObj = mapper.readValue(userJson, Manager.class);
                    break;
                case EMPLOYEE:
                    userObj = mapper.readValue(userJson, Employee.class);
                    break;
                default:
                    userObj = null;
            }

            userService.addUser(companyId, userObj);
        } catch (JSONException e) {

        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
