package com.savytskyy.client.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.savytskyy.client.users.dto.*;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class UserService {
    public UserService(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private String token = null;
    private Date loginTime = new Date();
    private final long MAX_LOGIN_DURATION = 30;

    public List<User> getUserList() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://mag-contacts-api.herokuapp.com/users"))
                .GET()
                .header("Accept", "application/json")
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            UserResponse userResponse = objectMapper.readValue(
                    response.body(),
                    UserResponse.class);

            return userResponse.getUsers();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getUserList(String token) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://mag-contacts-api.herokuapp.com/users2"))
                .GET()
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json")
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            UserResponse userResponse = objectMapper.readValue(
                    response.body(),
                    UserResponse.class);

            return userResponse.getUsers();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void  register(String login, String password, String dateBorn) {

        RegistrationRequest regRequest = new RegistrationRequest(login, password, dateBorn);

        try {
            String req = objectMapper.writeValueAsString(regRequest);
            String error = null;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://mag-contacts-api.herokuapp.com/register"))
                    .POST(HttpRequest.BodyPublishers.ofString(req))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            RegistrationResponse regResponse = objectMapper.readValue(
                    response.body(),
                    RegistrationResponse.class);

            error = regResponse.getError();
            System.out.println(error!=null?error:regResponse.getStatus());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void login (String login, String password)  {

        LoginRequest logRequest = new LoginRequest(login, password);

        try {
            String req = objectMapper.writeValueAsString(logRequest);

            HttpRequest request3 = HttpRequest.newBuilder()
                    .uri(URI.create("https://mag-contacts-api.herokuapp.com/login"))
                    .POST(HttpRequest.BodyPublishers.ofString(req))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = httpClient.send(request3, HttpResponse.BodyHandlers.ofString());

            LoginResponse logResponse = objectMapper.readValue(
                    response.body(),
                    LoginResponse.class);

            System.out.println(logResponse.getStatus());
            setToken(logResponse.getToken());
            if (logResponse.getStatus().equals("Ok")){loginTime = new Date();}



        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getToken () {
        return token;
    }

    public void setToken (String token) {
        this.token = token;
    }

    //TODO добавить проверку срока годности!
    public boolean hasValidToken() {
        return (token!=null&& tokenIsFresh());
    }

    public boolean tokenIsFresh () {
        long periodAfterLogin = new Date().getTime()-loginTime.getTime();
        periodAfterLogin/=60000;

        return periodAfterLogin<MAX_LOGIN_DURATION;
    }

}
