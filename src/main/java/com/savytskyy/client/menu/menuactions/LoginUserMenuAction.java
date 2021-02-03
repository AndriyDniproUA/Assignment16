package com.savytskyy.client.menu.menuactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.savytskyy.client.users.UserService;
import lombok.AllArgsConstructor;

import java.net.http.HttpClient;
import java.util.Scanner;

@AllArgsConstructor
public class LoginUserMenuAction implements MenuAction {
    private HttpClient httpClient;
    private ObjectMapper objectMapper;
    private UserService userService;
    //OnlineContactsService onlineContactsService;
    Scanner sc;

    @Override
    public void doAction() {

        String login, password;

        System.out.print("Please enter your login: ");
        login = sc.nextLine();

        System.out.print("Please enter your password: ");
        password = sc.nextLine();

        userService.login(login, password);
    }

    @Override
    public String getName() {
        return "Login existing user";
    }

    @Override
    public boolean closeAfter() {
        return false;
    }

    @Override
    public boolean isVisible() {
        return !userService.hasValidToken();
    }


}
