package com.supermarket.inventory.users;

import com.supermarket.inventory.users.dtos.CreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsersServiceTests {

    @Autowired
    UserService userService;

    @Test
    void can_create_users() {
        var user = userService.createUser(new CreateUserRequest(
                "kunal",
                "kunal123",
                "123456",
                "kunal@sm.com",
                "admin"
        ));

        Assertions.assertNotNull(user);
        Assertions.assertEquals("kunal123", user.getUsername());
    }
}
