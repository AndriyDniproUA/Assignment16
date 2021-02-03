package com.savytskyy.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.savytskyy.client.contacts.OnlineContactsService;
import com.savytskyy.client.menu.menuactions.*;
import com.savytskyy.client.users.UserService;

import java.net.http.HttpClient;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HttpClient httpClient = HttpClient.newBuilder().build();
        ObjectMapper objectMapper = new ObjectMapper();
        UserService userService = new UserService(httpClient, objectMapper);
        OnlineContactsService onlineContactsService = new OnlineContactsService(httpClient, objectMapper);
        Scanner sc = new Scanner(System.in);

        Menu menu = new Menu(httpClient, objectMapper, userService, onlineContactsService, sc);
        menu.addAction(new ReadAllContactsMenuAction(httpClient, objectMapper, userService, onlineContactsService, sc));
        menu.addAction(new FindContactByNameMenuAction(httpClient, objectMapper, userService, onlineContactsService, sc));
        menu.addAction(new FindContactByValueMenuAction(httpClient, objectMapper, userService, onlineContactsService, sc));
        menu.addAction(new AddContactMenuAction(httpClient, objectMapper, userService, onlineContactsService, sc));
        menu.addAction(new ReadAllUsersAfterLoginMenuAction(httpClient, objectMapper, userService));
        menu.addAction(new QuitUserMenuAction(userService));

        menu.addAction(new LoginUserMenuAction(httpClient, objectMapper, userService, sc));
        menu.addAction(new ReadAllUsersBeforeLoginMenuAction(httpClient, objectMapper, userService));
        menu.addAction(new RegisterUserMenuAction(httpClient, objectMapper, userService, sc));
        menu.addAction(new QuitMenuAction(userService));
        menu.run();
    }
}
