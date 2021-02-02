package com.savytskyy.client.menu.menuactions;

public interface MenuAction {
    void doAction();
    String getName();
    boolean closeAfter();
    boolean isVisible();


}
