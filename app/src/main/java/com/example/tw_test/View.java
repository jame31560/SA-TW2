package com.example.tw_test;

import java.io.Console;

public class View {
    Console console = System.console();

    public void shoWelcome() {
        console.printf("Hello, wellcome to use paymet system.\n");
    }

    public String showMenu() {
        return console.readLine("1) Login\n2) Regist\n3) Quit\n>>> ");
    }

    public void showError(String content) {
        console.printf("Error:\n%s\n", content);
    }

    public String[] showLoginForm() {
        return new String[] {
                console.readLine("Username\n>>> "),
                String.valueOf(console.readPassword("Password\n>>> "))
        };
    }
}
