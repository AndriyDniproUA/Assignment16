package com.savytskyy.client.menu.menuactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.savytskyy.client.contacts.Contact;
import com.savytskyy.client.contacts.OnlineContactsService;
import com.savytskyy.client.menu.menuactions.MenuAction;
import com.savytskyy.client.users.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class FindContactByValueMenuAction implements MenuAction {

    private HttpClient httpClient;
    private ObjectMapper objectMapper;
    private UserService userService;
    OnlineContactsService onlineContactsService;
    Scanner sc;

    @Override
    public void doAction() {

        System.out.print("Please enter the excerpt of the value to find: ");
        String value = sc.nextLine();
        List<Contact> contacts = onlineContactsService.findByValue(value, userService.getToken());

        System.out.println("---------------------------------");
        contacts.stream()
                .forEach(s -> System.out.printf(
                        "ID: %s, Name: %s, type: %s, value: %s \n",
                        s.getId(),s.getName(),s.getType(),s.getValue()));
        System.out.println("---------------------------------");
    }

    @Override
    public String getName() {
        return "Find contact by part of its value";
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


