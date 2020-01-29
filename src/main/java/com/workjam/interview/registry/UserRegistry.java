package com.workjam.interview.registry;

import com.workjam.interview.objects.user.User;

import java.util.List;
import java.util.function.Predicate;

public interface UserRegistry {

    /**
     * Returns an ordered filtered list of users related to the specified company ID.
     *
     * @param companyId the company ID
     * @param filter    the predicate that will be used to filter users. If null is specified, no filter will be used.
     * @return a list of users.
     */
    List<User> getUsers(int companyId, Predicate<User> filter);

    /**
     * Adds a user to the related company ID user list.
     *
     * @param companyId the company ID .
     * @param user      the user to add.
     * @return true if the user was successfully added.
     */
    boolean addUser(int companyId, User user);
}