package task_advanced.task_7.Menus;

import task_advanced.task_7.DAOs.DAOException;
import task_advanced.task_7.Entities.Director;
import task_advanced.task_7.Entities.Entity;
import task_advanced.task_7.Services.DirectorService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class DirectorMenu implements Menu{

    private static final Scanner scanner = new Scanner(System.in);
    private static final DirectorService directorService = new DirectorService();
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
        System.out.println("Ви у меню Режисери. Виберіть дію: ");
        String[] options = {"1 - Вибрати усіх режисерів",
                "2 - Вибрати режисера за id",
                "3 - Вибрати режисера за прізвищем",
                "4 - Вибрати режисерів за датою народження",
                "5 - Вибрати режисера заданого фільму",
                "6 - Додати режисера у базу даних",
                "7 - Змінити режисера",
                "8 - Видалити режисера",
                "9 - Вихід",
        };
        int option = 0;
        while (option != 9){
            printMenu(options);
            try {
                option = Integer.parseInt(scanner.nextLine());
                switch (option){
                    case 1:
                        printList(directorService.findAll());
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 2:
                        System.out.println("Введіть потрібне id: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println(directorService.findById(id));
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 3:
                        System.out.println("Введіть прізвище: ");
                        String name = scanner.nextLine();
                        printList(directorService.findDirectorsByLastName(name));
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 4:
                        System.out.println("Введіть дату народження режисера у форматі 'yyyy-MM-dd': ");
                        String date = scanner.nextLine();
                        printList(directorService.findDirectorsByBirthDate(date));
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 5:
                        System.out.println("Введіть назву фільму: ");
                        String movieName = scanner.nextLine();
                        System.out.println(directorService.findDirectorOfMovie(movieName));
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 6:
                        addDirector();
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 8:
                        alterDirector();
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 9:
                        System.out.println("Введіть прізвище режисера, якого слід видалити з бази даних: ");
                        String directorName = scanner.nextLine();
                        Director director = directorService.findDirectorsByLastName(directorName).get(0);
                        System.out.println(directorService.delete(director));
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 10: exit(0);
                }
            }
            catch (Exception ex){
                System.out.println("Будь-ласка, введіть значення в межах від 1 до " + options.length);
                System.out.println("Натисніть Enter щоб продовжити...");
                scanner.nextLine();
            }
        }
    }
    private static void addDirector(){
        Director director = createDirectorEntity();
        try {
            boolean isAdded = directorService.add(director);
            System.out.println(isAdded);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    private static void alterDirector(){
        System.out.println("Введіть прізвище режисера, дані якого потребують змін: ");
        String name = scanner.nextLine();
        try {
            Director director = directorService.findDirectorsByLastName(name).get(0);
            alterDirectorEntity(director);
            System.out.println(directorService.alter(director));
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    private static Director createDirectorEntity() {
        System.out.println("Введіть ім'я (firstname) режисера: ");
        String firstName = scanner.nextLine();
        System.out.println("Введіть прізвище (lastname) режисера: ");
        String secondName = scanner.nextLine();
        System.out.println("Введіть дату народження в форматі 'yyy-MM-dd': ");
        String stringDate = scanner.nextLine();
        Date date = null;
        try {
            date = new Date(sdf.parse(stringDate).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Director director = new Director(firstName, secondName, date);
        return director;
    }

    private static void alterDirectorEntity(Director director) {
        System.out.println("Змінюйте дані за необхідністю або вводьте попередні дані...");
        System.out.println("Введіть ім'я (firstname) режисера: ");
        director.setFirstName(scanner.nextLine());
        System.out.println("Введіть прізвище (lastname) режисера: ");
        director.setLastName(scanner.nextLine());
        System.out.println("Введіть дату народження режисера в форматі 'yyy-MM-dd': ");
        String stringDate = scanner.nextLine();
        Date date = null;
        try {
            director.setBirthDate(new Date(sdf.parse(stringDate).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
