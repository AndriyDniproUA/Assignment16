package com.savytskyy.client.menu.menuactions;

import com.savytskyy.client.users.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QuitUserMenuAction implements MenuAction {
    UserService userService;

    @Override
    public void doAction() {
        userService.setToken(null);
    }

    @Override
    public String getName() {
        return "Quit current user";
    }

    @Override
    public boolean closeAfter() {
        return false;
    }

    @Override
    public boolean isVisible() {
        return userService.hasValidToken();
    }


}
