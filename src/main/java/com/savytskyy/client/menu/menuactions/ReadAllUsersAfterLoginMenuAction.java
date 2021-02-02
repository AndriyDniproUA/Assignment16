package com.savytskyy.client.menu.menuactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.savytskyy.client.users.User;
import com.savytskyy.client.users.UserService;
import lombok.AllArgsConstructor;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class ReadAllUsersAfterLoginMenuAction implements MenuAction {
    private HttpClient httpClient;
    private ObjectMapper objectMapper;
    private UserService userService;

    @Override
    public void doAction() {
        List<User> userList = userService.getUserList(userService.getToken());

        System.out.println("---------------------------------");
        userList.stream()
                .forEach(u -> System.out.printf(
                        "Login: %s, Date of birth: %s\n",
                        u.getLogin(),u.getDateBorn()));
        System.out.println("---------------------------------");

    }

    @Override
    public String getName() {
        return "Read all users after logging in";
    }

    @Override
    public boolean closeAfter() {
        return false;
    }


    @Override
    public boolean isVisible() {
        return (userService.getToken() != null);
    }


}
