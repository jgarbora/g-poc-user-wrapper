package com.g.g.userwrapper.controller;

import com.g.g.userwrapper.adapter.Auth0Adapter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private Auth0Adapter auth0Adapter;

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity getUser(@PathVariable String userId) {

        if (StringUtils.isEmpty(userId)) {
            return ResponseEntity.notFound().build();
        }

        ResponseEntity responseEntity = auth0Adapter.getUser(userId);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok(responseEntity.getBody());
        }

        // 404	User not found.
        if (responseEntity.getStatusCodeValue() == 404) { //
            return ResponseEntity.notFound().build();
        }

        log.warn("getUser: auth0 response http code: {}, for user id {}",responseEntity.getStatusCodeValue(),userId);
        return ResponseEntity.internalServerError().build();
    }
}
