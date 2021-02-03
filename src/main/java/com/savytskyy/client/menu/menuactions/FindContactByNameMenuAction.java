package com.savytskyy.client.menu.menuactions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.savytskyy.client.contacts.Contact;
import com.savytskyy.client.contacts.OnlineContactsService;
import com.savytskyy.client.Menu;
import com.savytskyy.client.users.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class FindContactByNameMenuAction implements MenuAction {

    private HttpClient httpClient;
    private ObjectMapper objectMapper;
    private UserService userService;
    OnlineContactsService onlineContactsService;
    Scanner sc;

    @Override
    public void doAction() {

        System.out.print("Please enter the beginning of the name to find: ");
        String name = sc.nextLine();
        List<Contact> contacts = onlineContactsService.findByName(name, userService.getToken());

        if (contacts != null) {
            System.out.println("---------------------------------");
            contacts.stream()
                    .forEach(s -> System.out.printf(
                            "ID: %s, Name: %s, type: %s, value: %s \n",
                            s.getId(), s.getName(), s.getType(), s.getValue()));
            System.out.println("---------------------------------");
        }
    }

    @Override
    public String getName() {
        return "Find contact by the beginning of the name";
    }

    @Override
    public boolean closeAfter() {
        return false;
    }

    @Override
    public boolean isVisible() { return userService.hasValidToken();
    }
}


