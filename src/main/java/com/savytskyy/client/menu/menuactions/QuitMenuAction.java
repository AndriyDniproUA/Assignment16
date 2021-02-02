package com.savytskyy.client.menu.menuactions;

import com.savytskyy.client.users.UserService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QuitMenuAction implements MenuAction {
    UserService userService;

    @Override
    public void doAction() {
        System.out.println("Good Bye!");
        System.out.println("Thank you for using our service!");
    }

    @Override
    public String getName() {
        return "Quit menu";
    }

    @Override
    public boolean closeAfter() {
        return true;
    }

    @Override
    public boolean isVisible() {
        return true;
    }


}
