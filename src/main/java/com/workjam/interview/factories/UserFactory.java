package com.workjam.interview.factories;

import com.workjam.interview.objects.user.Admin;
import com.workjam.interview.objects.user.Employee;
import com.workjam.interview.objects.user.Manager;
import com.workjam.interview.objects.user.User;

import java.time.LocalDate;

public class UserFactory {

    private static char letter = 'A';

    /**
     * Implement the getUser factory method.
     *
     * @return the new User created.
     */
    public static User getUser(User.Permission permission, LocalDate birthday, boolean active) {
        switch (permission) {
            case ADMIN:
                Admin admin = new Admin();
                admin.setName(getRandomName());
                admin.setBirthDay(birthday);
                admin.setActive(active);
                return admin;
            case MANAGER:
                Manager manager = new Manager();
                manager.setName(getRandomName());
                manager.setBirthDay(birthday);
                manager.setActive(active);
                return manager;
            case EMPLOYEE:
                Employee employee = new Employee();
                employee.setName(getRandomName());
                employee.setBirthDay(birthday);
                employee.setActive(active);
                return employee;
            default:
                return null;
        }
    }

    private static String getRandomName() {

        return new StringBuilder()
                .append(nextLetter())
                .append(nextLetter())
                .append(nextLetter())
                .append(nextLetter())
                .toString();
    }

    private static char nextLetter() {

        if (UserFactory.letter >= 'Z') {
            UserFactory.letter = 'A';
        }

        return UserFactory.letter++;
    }
}
