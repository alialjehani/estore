package com.example.CRUDRESTapi.controller;

import lombok.Data;

@Data
class RoleToUserForm{ //this class is acting like an entity, used to retrieve username and roleName  to the addRoleToUser method
    private String username;
    private String roleName;
}
