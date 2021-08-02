package com.g.g.userwrapper.adapter.users.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.g.g.userwrapper.adapter.users.dto.commons.AppMetadata;
import com.g.g.userwrapper.adapter.users.dto.commons.Identity;
import com.g.g.userwrapper.adapter.users.dto.commons.UserMetadata;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetUserResponse {

    @JsonProperty("created_at")
    public String createdAt;

    @JsonProperty("email")
    public String email;

    @JsonProperty("email_verified")
    public Boolean emailVerified;

    @JsonProperty("identities")
    public List<Identity> identities = null;

    @JsonProperty("name")
    public String name;

    @JsonProperty("nickname")
    public String nickname;

    @JsonProperty("picture")
    public String picture;

    @JsonProperty("updated_at")
    public String updatedAt;

    @JsonProperty("user_id")
    public String userId;

    @JsonProperty("multifactor")
    public List<String> multifactor = null;

    @JsonProperty("multifactor_last_modified")
    public String multifactorLastModified;

    @JsonProperty("user_metadata")
    public UserMetadata userMetadata;

    @JsonProperty("app_metadata")
    public AppMetadata appMetadata;

    @JsonProperty("last_ip")
    public String lastIp;

    @JsonProperty("last_login")
    public String lastLogin;

    @JsonProperty("logins_count")
    public Integer loginsCount;

}
