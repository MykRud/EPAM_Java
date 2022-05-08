package task_advanced.task_7.Menus;

import java.util.Scanner;

import static java.lang.System.exit;

public class MainMenu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final MovieMenu movieMenu = new MovieMenu();
    private static final ActorMenu actorMenu = new ActorMenu();
    private static final DirectorMenu directorMenu = new DirectorMenu();

    private void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Виберіть потрібне меню: ");
    }

    public void start() {
        String[] options = {"1 - Меню фільмів",
                "2 - Меню акторів",
                "3 - Меню режисерів",
                "4 - Вихід",
        };

        int option = 0;
        while (option != 4){
            printMenu(options);
            try {
                option = Integer.parseInt(scanner.nextLine());
                switch (option){
                    case 1: movieMenu.init(); break;
                    case 2: actorMenu.init(); break;
                    case 3: directorMenu.init(); break;
                    case 4: exit(0);
                }
            }
            catch (Exception ex){
                System.out.println("Будь-ласка, введіть значення в межах від 1 до " + options.length);
                System.out.println("Натисніть Enter щоб продовжити...");
                scanner.nextLine();
            }
        }
    }
}
