package com.g.g.userwrapper.adapter.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.g.g.userwrapper.adapter.users.dto.commons.AppMetadata;
import com.g.g.userwrapper.adapter.users.dto.commons.UserMetadata;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("user_metadata")
    private UserMetadata userMetadata;

    @JsonProperty("blocked")
    private Boolean blocked;

    @JsonProperty("email_verified")
    private Boolean emailVerified;

    @JsonProperty("phone_verified")
    private Boolean phoneVerified;

    @JsonProperty("app_metadata")
    private AppMetadata appMetadata;

    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty("family_name")
    private String familyName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("picture")
    private String picture;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("connection")
    private String connection;

    @JsonProperty("password")
    private String password;

    @JsonProperty("verify_email")
    private Boolean verifyEmail;

    @JsonProperty("username")
    private String username;
}
