package task_advanced.task_7.Menus;

import task_advanced.task_7.DAOs.DAOException;
import task_advanced.task_7.Entities.Actor;
import task_advanced.task_7.Entities.Entity;
import task_advanced.task_7.Entities.Movie;
import task_advanced.task_7.Services.ActorService;
import task_advanced.task_7.Services.MovieService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class ActorMenu implements Menu{
    private static final Scanner scanner = new Scanner(System.in);
    private static final ActorService actorService = new ActorService();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Виберіть потрібне меню: ");
    }

    private static void printList(List<? extends Entity> entityList){
        for(Entity entity : entityList)
            System.out.println(entity);
    }

    @Override
    public void init() {
        System.out.println("Ви у меню Актори. Виберіть дію: ");
        String[] options = {"1 - Вибрати усіх акторів",
                "2 - Вибрати актора за id",
                "3 - Вибрати актора за прізвищем",
                "4 - Вибрати акторів за датою народження",
                "5 - Вибрати акторів, які знімалися у декому фільмі",
                "6 - Додати актора у базу даних",
                "7 - Змінити актора",
                "8 - Видалити актора",
                "9 - Вивести усю інформацію про актерів, за знімалися як мінімум у N фільмах",
                "10 - Вивести інформацію про акторів, які бути режисерами хоча би одного із фільмів",
                "11 - Назад",
                "12 - Вихід",
        };
        int option = 0;
        while (option != 11){
            printMenu(options);
            try {
                option = Integer.parseInt(scanner.nextLine());
                switch (option){
                    case 1:
                        printList(actorService.findAll());
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 2:
                        System.out.println("Введіть потрібне id: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println(actorService.findById(id));
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 3:
                        System.out.println("Введіть прізвище: ");
                        String name = scanner.nextLine();
                        printList(actorService.findActorByLastName(name));
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 4:
                        System.out.println("Введіть дату народження актора у форматі 'yyyy-MM-dd': ");
                        String date = scanner.nextLine();
                        printList(actorService.findActorsByBirthDate(date));
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 5:
                        System.out.println("Введіть назву фільму: ");
                        String movieName = scanner.nextLine();
                        printList(actorService.findActorsByMovie(movieName));
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 6:
                        addActor();
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 7:
                        alterActor();
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 8:
                        System.out.println("Введіть прізвище актора, якого слід видалити з бази даних: ");
                        String actorName = scanner.nextLine();
                        Actor actor = actorService.findActorByLastName(actorName).get(0);
                        System.out.println(actorService.delete(actor));
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 9:
                        System.out.println("Введіть кількість фільмів: ");
                        int numberOfMovies = Integer.parseInt(scanner.nextLine());
                        printList(actorService.findActorsThatActAtLeastInNMovies(numberOfMovies));
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 10:
                        printList(actorService.findActorsThatAreDirectorsInAtLeastOneMovie());
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 12: exit(0);
                }
            }
            catch (Exception ex){
                System.out.println("Будь-ласка, введіть значення в межах від 1 до " + options.length);
                System.out.println("Натисніть Enter щоб продовжити...");
                scanner.nextLine();
            }
        }
    }
    private static void addActor(){
        Actor actor = createActorEntity();
        try {
            boolean isAdded = actorService.add(actor);
            System.out.println(isAdded);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    private static void alterActor(){
        System.out.println("Введіть прізвище актора, дані якого потребують змін: ");
        String name = scanner.nextLine();
        try {
            Actor actor = actorService.findActorByLastName(name).get(0);
            alterActorEntity(actor);
            System.out.println(actorService.alter(actor));
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    private static Actor createActorEntity() {
        System.out.println("Введіть ім'я (firstname) актора: ");
        String firstName = scanner.nextLine();
        System.out.println("Введіть прізвище (lastname) актора: ");
        String secondName = scanner.nextLine();
        System.out.println("Введіть дату народження в форматі 'yyy-MM-dd': ");
        String stringDate = scanner.nextLine();
        Date date = null;
        try {
            date = new Date(sdf.parse(stringDate).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Actor actor = new Actor(firstName, secondName, date);
        return actor;
    }

    private static void alterActorEntity(Actor actor) {
        System.out.println("Змінюйте дані за необхідністю або вводьте попередні дані...");
        System.out.println("Введіть ім'я (firstname) актора: ");
        actor.setFirstName(scanner.nextLine());
        System.out.println("Введіть прізвище (lastname) актора: ");
        actor.setLastName(scanner.nextLine());
        System.out.println("Введіть дату народження актора в форматі 'yyy-MM-dd': ");
        String stringDate = scanner.nextLine();
        Date date = null;
        try {
            actor.setBirthDate(new Date(sdf.parse(stringDate).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
