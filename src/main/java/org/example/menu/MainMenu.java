package org.example.menu;

import lombok.NoArgsConstructor;

import java.util.InputMismatchException;
import java.util.Scanner;

@NoArgsConstructor
public class MainMenu {

    Scanner scanner = new Scanner(System.in);
    String userName;
    String password;

    public void start() {
        while (true) {
            String text = """
                     select your role :
                    1- student
                    2- teacher
                    3- employee
                    4- exit
                    """;
            System.out.println(text);
            System.out.println("enter your select :");
            int input = input();
            switch (input) {
                case 1 -> {
                    login();
                    new StudentMenu(userName, password);
                }
                case 2 -> {
                    login();
                    new TeacherMenu(userName, password);
                }
                case 3 -> {
                    login();
                    new EmployeeMenu(userName, password);
                }
                case 4 -> System.exit(-1);
                default -> {
                    System.out.println("invalid input");
                    start();
                }
            }
        }
    }


    void login() {

        System.out.println("enter your username : ");
        this.userName = scanner.next();
        System.out.println(" enter your password : ");
        this.password = scanner.next();

    }

    public Integer input() {
        int i;
        while (true) {
            try {
                i = scanner.nextInt();
                scanner.nextLine();
                return i;
            } catch (InputMismatchException in) {
                scanner.nextLine();
                System.out.println("enter valid number");
            }
        }
    }
}