package com.workjam.interview.registry;

import com.workjam.interview.factories.UserFactory;
import com.workjam.interview.objects.user.Admin;
import com.workjam.interview.objects.user.Employee;
import com.workjam.interview.objects.user.Manager;
import com.workjam.interview.objects.user.User;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

/**
 * The UserRegistryImpl class is used to manage the users persistence in the application.
 */
@Configuration
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

        Random random = new Random();

        long currentDate = System.currentTimeMillis();
        // Generate 13 employees
        List<Employee> employees111 = new ArrayList<>();
        List<Employee> employees333 = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            long randomDay = ThreadLocalRandom.current().nextLong(currentDate);
            boolean randomActive = Math.random() % 2 == 1;
            Employee employee = (Employee) UserFactory.getUser(User.Permission.EMPLOYEE, Instant.ofEpochMilli(randomDay).atZone(ZoneId.systemDefault()).toLocalDate(), randomActive);

            if (i < 7) {
                employees111.add(employee);
            } else {
                employees333.add(employee);
            }
        }

        // Generate 5 managers
        List<User> managers111 = new ArrayList<>();
        List<User> managers333 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            long randomDay = ThreadLocalRandom.current().nextLong(currentDate);
            boolean randomActive = Math.random() % 2 == 1;
            Manager manager = (Manager) UserFactory.getUser(User.Permission.MANAGER, Instant.ofEpochMilli(randomDay).atZone(ZoneId.systemDefault()).toLocalDate(), randomActive);

            if (i < 3) {
                managers111.add(manager);
            } else {
                managers333.add(manager);
            }
        }

        // Assign employees to managers
        Manager manager0 = (Manager) managers111.get(0);
        manager0.addEmployees(employees111.get(0), employees111.get(1), employees111.get(2));

        Manager manager1 = (Manager) managers111.get(1);
        manager1.addEmployees(employees111.get(3), employees111.get(4));

        Manager manager2 = (Manager) managers111.get(2);
        manager2.addEmployees(employees111.get(5), employees111.get(6));

        Manager manager3 = (Manager) managers333.get(0);
        manager3.addEmployees(employees333.get(0), employees333.get(1), employees333.get(2));

        Manager manager4 = (Manager) managers333.get(1);
        manager4.addEmployees(employees333.get(3), employees333.get(4), employees333.get(5));

        // Generate 2 admins
        List<User> admins111 = new ArrayList<>();
        List<User> admins333 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            long randomDay = ThreadLocalRandom.current().nextLong(currentDate);
            boolean randomActive = random.nextBoolean();
            Admin admin = (Admin) UserFactory.getUser(User.Permission.ADMIN, Instant.ofEpochMilli(randomDay).atZone(ZoneId.systemDefault()).toLocalDate(), randomActive);

            if (i == 0) {
                admin.addManagers(manager0, manager1, manager2);
                admins111.add(admin);
            } else {
                admin.addManagers(manager3, manager4);
                admins333.add(admin);
            }
        }

        List<User> users111 = new ArrayList<>();
        users111.addAll(employees111);
        users111.addAll(managers111);
        users111.addAll(admins111);

        List<User> users333 = new ArrayList<>();
        users333.addAll(employees333);
        users333.addAll(managers333);
        users333.addAll(admins333);

        REGISTRY.put(111, users111);
        REGISTRY.put(333, users333);
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
