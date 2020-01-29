package com.workjam.interview.registry;

import com.workjam.interview.objects.user.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * The UserRegistryImpl class is used to manage the users persistence in the application.
 */
public class UserRegistryImpl implements UserRegistry {

    /**
     * This is the data structure that contains all users by company ID.
     * Important: for each company, the user list must always remain sorted by the users' name.
     */
    public static final Map<Integer, List<User>> REGISTRY = Collections.synchronizedMap(new HashMap<>());

    static {

        /*
         * Important: fill the map registry statically for 2 companies (ID 111 and 333).
         *
         * The user list must use the UserFactory. The factory should be able to generate a user regardless of the defined parameters.
         * Use this factory to generate a list of at least 20 users, 5 of them managers and 2 admins.
         * Random names should generated for users. It is important to have both active and inactive users of each type.
         */
    }

    /**
     * Returns an ordered and filtered list of users related to the specified company ID.
     *
     * @param companyId the company ID.
     * @param filter    the predicate that will be used to filter users. If null is specified, no filter will be used.
     * @return a list of users.
     */
    @Override
    public List<User> getUsers(int companyId, Predicate<User> filter) {

        if (filter == null) {

            final List<User> userList = REGISTRY.get(Integer.valueOf(companyId));

            if (userList != null)
                return Collections.unmodifiableList(userList);
        }

        /*
         * Complete the method to return a filtered list based on the specified filter predicate
         */

        return Collections.emptyList();
    }

    /**
     * Adds a user to the related company ID user list.
     *
     * @param companyId the company ID .
     * @param user      the user to add.
     * @return true if the user was successfully added.
     */
    @Override
    public boolean addUser(int companyId, User user) {

        if (user == null)
            throw new IllegalArgumentException("Invalid user (null).");

        /*
         * Complete the method to add the specified user into the registry
         */

        return false;
    }
}
