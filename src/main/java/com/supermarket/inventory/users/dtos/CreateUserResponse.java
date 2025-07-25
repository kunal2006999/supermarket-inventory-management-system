package com.supermarket.inventory.users.dtos;

import lombok.Data;

@Data
public class CreateUserResponse {

    private long id;
    private String name;
    private String username;
    private String email;
    private String role;

}
