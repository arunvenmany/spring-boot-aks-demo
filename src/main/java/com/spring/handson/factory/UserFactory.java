package com.spring.handson.factory;

import com.spring.handson.model.User;

import java.util.ArrayList;
import java.util.List;

import static com.spring.handson.model.User.UserBuilder.anUser;
import static com.spring.handson.util.ServiceConstants.*;

public class UserFactory {
    public static List<User> getUserList() {
        List<User> users = new ArrayList<>(2);
        User user = anUser()
                .withId(MOCK_ID_1)
                .withUsername(MOCK_USER_NAME_1)
                .withPassword(MOCK_PASSWORD_1)
                .withFirstname(MOCK_FIRST_NAME_1)
                .withLastname(MOCK_LAST_NAME_1)
                .withAge(MOCK_AGE_1)
                .withSalary(MOCK_SALARY_1)
                .build();
        users.add(user);
        user = anUser()
                .withId(MOCK_ID_2)
                .withUsername(MOCK_USER_NAME_2)
                .withPassword(MOCK_PASSWORD_2)
                .withFirstname(MOCK_FIRST_NAME_2)
                .withLastname(MOCK_LAST_NAME_2)
                .withAge(MOCK_AGE_2)
                .withSalary(MOCK_SALARY_2)
                .build();
        users.add(user);
        return users;
    }
}
