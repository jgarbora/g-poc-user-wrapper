package com.g.g.userwrapper.adapter.users.dto.commons;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Identity {

    @JsonProperty("user_id")
    public String userId;
    @JsonProperty("provider")
    public String provider;
    @JsonProperty("connection")
    public String connection;
    @JsonProperty("isSocial")
    public Boolean isSocial;
}
