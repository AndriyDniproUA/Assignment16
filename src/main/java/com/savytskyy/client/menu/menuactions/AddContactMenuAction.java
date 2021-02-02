package com.savytskyy.client.menu.menuactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.savytskyy.client.contacts.OnlineContactsService;
import com.savytskyy.client.users.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.net.http.HttpClient;
import java.util.Scanner;

@AllArgsConstructor
public class AddContactMenuAction implements MenuAction {
    private HttpClient httpClient;
    private ObjectMapper objectMapper;
    private UserService userService;
    OnlineContactsService onlineContactsService;
    Scanner sc;

    @Override
    public void doAction() {

        String name, type, value;

        System.out.print("Please enter the Name: ");
        name = sc.nextLine();


        System.out.print("Please enter the type [phone/email]: ");
        type = sc.nextLine();


        System.out.print("Please enter the value: ");
        value = sc.nextLine();


        onlineContactsService.add(name, type, value, userService.getToken());
    }

    @Override
    public String getName() {
        return "Add Contact";
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
