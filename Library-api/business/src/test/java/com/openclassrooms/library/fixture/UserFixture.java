package com.openclassrooms.library.fixture;

import com.openclassrooms.library.entity.User;

public class UserFixture {

    public static User buildUser() {
        User user = new User();
        user.setEmail("user@email.com");
        user.setId(1L);
        return user;
    }
}
