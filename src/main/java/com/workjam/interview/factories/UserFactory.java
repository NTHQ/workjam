package com.workjam.interview.factories;

import com.workjam.interview.objects.user.User;

public class UserFactory {

    private static char letter = 'A';

    /**
     * Implement the getUser factory method.
     *
     * @return the new User created.
     */
    public static User getUser() {

        return null;
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
