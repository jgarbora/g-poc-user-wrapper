package com.g.g.userwrapper.controller;

import com.auth0.dto.api.v2.users.*;
import com.g.g.userwrapper.adapter.Auth0Adapter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

import static com.g.g.userwrapper.util.Constants.ROLE_MAP;

@RestController
@Slf4j
@RequestMapping(path = "/api/v0/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private Auth0Adapter auth0Adapter;

    @GetMapping(value = "/{userId}")
    public ResponseEntity getUser(@PathVariable String userId) {
        if (StringUtils.isEmpty(userId)) {
            return ResponseEntity.notFound().build();
        }
        return auth0Adapter.getUser(userId);
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserCustomRequest createUserRequest) {
        ResponseEntity<CreateUserResponse> createUserResponse = auth0Adapter.createUser(createUserRequest);

        if (ROLE_MAP.containsKey(createUserRequest.role)) {
            AssignRolesToAUserRequest request = new AssignRolesToAUserRequest();
            request.roles = Collections.singletonList(ROLE_MAP.get(createUserRequest.role));
            auth0Adapter.assignRolesToAUser(request, createUserResponse.getBody().getUserId());
        }

        return createUserResponse;
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UpdateUserResponse> updateUser(@RequestBody UpdateUserRequest updateUserRequest, @PathVariable String userId) {
        return auth0Adapter.updateUser(updateUserRequest, userId);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<DeleteUserResponse> deleteUser(@PathVariable String userId) {
        return auth0Adapter.deleteUser(userId);
    }

    @PostMapping("/{userId}/roles")
    public ResponseEntity<AssignRolesToAUserResponse> assignRolesToAUser(@RequestBody AssignRolesToAUserRequest assignRolesToAUserRequest, @PathVariable String userId) {
        return auth0Adapter.assignRolesToAUser(assignRolesToAUserRequest, userId);
    }
}
