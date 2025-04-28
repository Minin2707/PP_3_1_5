package com.example.PP_315.services;


import com.example.PP_315.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {

    private final RestTemplate restTemplate;
    public String sessionId;
    public final String url = "http://94.198.50.185:7081/api/users";

    @Autowired
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void getAllUsers() {
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        List<String> cookies = response.getHeaders().get("Set-Cookie");
        if(cookies != null) {
            sessionId = cookies.get(0).split(";")[0];
        }
        System.out.println(response.getBody());
    }

    public void saveUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", sessionId);

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println(response.getBody());
    }

    public void updateUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", sessionId);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
        System.out.println(response.getBody());
    }

    public void deleteUser(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", sessionId);
        HttpEntity<User> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, entity, String.class);
        System.out.println(response.getBody());
    }


}
