package com.savytskyy.client.menu.menuactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.savytskyy.client.contacts.OnlineContactsService;
import com.savytskyy.client.users.UserService;
import lombok.AllArgsConstructor;

import java.net.http.HttpClient;
import java.util.Scanner;

@AllArgsConstructor
public class RegisterUserMenuAction implements MenuAction {
    private HttpClient httpClient;
    private ObjectMapper objectMapper;
    private UserService userService;
    //OnlineContactsService onlineContactsService;
    Scanner sc;

    @Override
    public void doAction() {
        String login, password, dateBorn;

        System.out.print("Please enter your login: ");
        login = sc.nextLine();


        System.out.print("Please enter your password: ");
        password = sc.nextLine();


        System.out.print("Please enter your birthdate [YYYY-MM-dd]: ");
        dateBorn = sc.nextLine();


        userService.register(login, password, dateBorn);
    }

    @Override
    public String getName() {
        return "Register a new user";
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
