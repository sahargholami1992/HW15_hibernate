package org.example;


import org.example.menu.MainMenu;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


public class Application {

    public static void main(String[] args) {
//         EntityManager entityManager =
//                Persistence.createEntityManagerFactory(
//                        "default"
//                ).createEntityManager();
        MainMenu menu = new MainMenu();
        menu.start();

    }
}
