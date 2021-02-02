package com.savytskyy.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.savytskyy.client.contacts.OnlineContactsService;
import com.savytskyy.client.menu.menuactions.MenuAction;
import com.savytskyy.client.users.UserService;
import lombok.AllArgsConstructor;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Menu {
    private HttpClient httpClient;
    private ObjectMapper objectMapper;
    private UserService userService;
    private OnlineContactsService onlineContactsService;
    private Scanner sc;

    public Menu(HttpClient httpClient, ObjectMapper objectMapper, UserService userService, OnlineContactsService onlineContactsService,Scanner sc) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.userService = userService;
        this.onlineContactsService = onlineContactsService;
        this.sc = sc;
    }
    public List<MenuAction> actions = new ArrayList<>();

    public void run() {
        System.out.println("This is your new Online contact List manager:");

        while (true) {
            System.out.println("---------------------------------");
            System.out.println("You've got the following options:");
            showMenu();
            int choice = askUserChoice();
            int index = choice - 1;
            if (indexIsValid(index)) {
                actions.get(index).doAction();
            } else {
                System.out.printf("Please enter the number within 1-%d range\n", actions.size());
                continue;
            }
            if (actions.get(index).closeAfter()) break;
        }
    }


    public void addAction(MenuAction action) {
        actions.add(action);
    }

    //@TODO Хочу снова спросить, как можно работать с индексами листа при использовании стрима (нужны для выбора пунктов меню)!
    public void showMenu() {
        System.out.println("--------------MENU ----------------");
        for (int i = 0; i < actions.size(); i++) {
            if (actions.get(i).isVisible()) {
                System.out.printf("[%d] - %s \n", i + 1, actions.get(i).getName());
            }
        }
        System.out.println("---------------------------------");
    }

    private int askUserChoice() {
        System.out.println("Please enter the number of your desired option: ");
        int choice = sc.nextInt();
        sc.nextLine();
        return choice;
    }

    private boolean indexIsValid(int index) {
        return index >= 0 && index < actions.size();
    }





}
