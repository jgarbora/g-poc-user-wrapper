package com.g.g.userwrapper.adapter;


import com.auth0.dto.api.v2.users.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Slf4j
/**
 * Adapter for https://jgarbora.eu.auth0.com/api/v2/
 *
 * https://auth0.com/docs/api/
 *
 */
public class Auth0Adapter {

    @Value("${baseurl:https://jgarbora.eu.auth0.com}")
    private String baseUrl;

    @Value("${auth0.management.api.clientId}")
    private String clientId;

    @Value("${auth0.management.api.clientSecret}")
    private String clientSecret;

    private ResponseEntity<LinkedHashMap> tokenResponse;

    private RestTemplate restTemplate = new RestTemplate();

    public void init() {
        log.debug("clientId: {} , clientSecret: {}", clientId, clientSecret);

        Map<String, String> body = new HashMap<>();
        body.put("client_id",clientId);
        body.put("client_secret",clientSecret);
        body.put("audience","https://jgarbora.eu.auth0.com/api/v2/");
        body.put("grant_type","client_credentials");
        tokenResponse = restTemplate.exchange(baseUrl+"/oauth/token", HttpMethod.POST, new HttpEntity<>(body, buildHeaders()), LinkedHashMap.class);
    }

    // TODO NEED TO REFRESH THE TOKEN

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        return headers;
    }

    private HttpHeaders buildHeadersForAuth0Api() {
        if (tokenResponse == null) {
            init();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", String.format("Bearer %s", tokenResponse.getBody().get("access_token").toString()));
        return headers;
    }

    public ResponseEntity<GetUserResponse> getUser(String userId) {
        String url = String.format("%s/api/v2/users/%s", baseUrl, userId);
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(buildHeadersForAuth0Api()), GetUserResponse.class);
    }

    public ResponseEntity<CreateUserResponse> createUser(CreateUserRequest createUserRequest) {
        String url = String.format("%s/api/v2/users", baseUrl);
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(createUserRequest, buildHeadersForAuth0Api()), CreateUserResponse.class);
    }

    public ResponseEntity<UpdateUserResponse> updateUser(UpdateUserRequest updateUserRequest, String userId) {
        String url = String.format("%s/api/v2/users/%s", baseUrl, userId);
        return restTemplate.exchange(url, HttpMethod.PATCH, new HttpEntity<>(updateUserRequest, buildHeadersForAuth0Api()), UpdateUserResponse.class);
    }

    public ResponseEntity<DeleteUserResponse> deleteUser(String userId) {
        String url = String.format("%s/api/v2/users/%s", baseUrl, userId);

        return restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(buildHeadersForAuth0Api()), DeleteUserResponse.class);
    }

    public ResponseEntity<AssignRolesToAUserResponse> assignRolesToAUser(AssignRolesToAUserRequest assignRolesToAUserRequest, String userId) {
        String url = String.format("%s/api/v2/users/%s/roles", baseUrl, userId);
        return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(assignRolesToAUserRequest, buildHeadersForAuth0Api()), AssignRolesToAUserResponse.class);
    }
}
