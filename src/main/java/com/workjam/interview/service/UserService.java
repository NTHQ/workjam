package com.workjam.interview.service;

import com.workjam.interview.objects.UserFilter;
import com.workjam.interview.objects.user.User;
import com.workjam.interview.registry.UserRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Predicate;

@Configuration
public class UserService {
    /**
     * The user registry instance that contains users.
     */
    @Autowired
    private UserRegistry userRegistry;

    /*
     * Add method(s) to return users from the UserRegistry. This class shall support filtering based in specified user permissions and the active state.
     */
    public List<User> getUsers(int companyId, UserFilter filter) {
        Predicate<User> predicate = null;
        if (filter != null) {
            if (filter.getPermission() != null && filter.isActive() != null) {
                predicate = u -> u.isActive() == filter.isActive() && u.getPermission() == filter.getPermission();
            } else if (filter.isActive() != null) {
                predicate = u -> u.isActive() == filter.isActive();
            } else {
                predicate = u -> u.getPermission() == filter.getPermission();
            }
        }

        return userRegistry.getUsers(companyId, predicate);

    }
    /*
     * Add method to add a user into the UserRegistry.
     */


}
