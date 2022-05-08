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

public class MovieMenu implements Menu{
    private static final Scanner scanner = new Scanner(System.in);
    private static final MovieService movieService = new MovieService();
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
        System.out.println("Ви у меню Фільми. Виберіть дію: ");
        String[] options = {"1 - Вибрати усі фільми",
                "2 - Вибрати фільм за id",
                "3 - Вибрати фільм за назвою",
                "4 - Вибрати фільми за датою виходу на екран",
                "5 - Вибрати фільми за актором, який знімався у них",
                "6 - Вибрати фільми за країною, у якій вони знімалися",
                "7 - Додати фільм у базу даних",
                "8 - Змінити фільм",
                "9 - Видалити фільм",
                "10 - Знайти всі фільми, що вийшли на екран у теперішньому та попередньому роках",
                "11 - Видалити усі фільми, дата виходу яких є більшою за певну кількісті років",
                "12 - Додати актора у список акторів деякого фільму",
                "13 - Назад",
                "14 - Вихід",
        };
        int option = 0;
        while (option != 13){
            printMenu(options);
            try {
                option = Integer.parseInt(scanner.nextLine());
                switch (option){
                    case 1:
                        printList(movieService.findAll());
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 2:
                        System.out.println("Введіть потрібне id: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println(movieService.findById(id));
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 3:
                        System.out.println("Введіть потрібну назву: ");
                        String name = scanner.nextLine();
                        printList(movieService.findMoviesByName(name));
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 4:
                        System.out.println("Введіть дату виходу фільма у форматі 'yyyy-MM-dd': ");
                        String date = scanner.nextLine();
                        printList(movieService.findMoviesByDateRelease(date));
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 5:
                        System.out.println("Введіть прізвище актора: ");
                        String actorLastName = scanner.nextLine();
                        printList(movieService.findMoviesByActor(actorLastName));
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 6:
                        System.out.println("Введіть назву країни: ");
                        String country = scanner.nextLine();
                        printList(movieService.findMoviesByCountry(country));
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 7:
                        addMovie();
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 8:
                        alterMovie();
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 9:
                        System.out.println("Введіть назву фільму, який слід видалити з бази даних: ");
                        String movieName = scanner.nextLine();
                        Movie movie = movieService.findMoviesByName(movieName).get(0);
                        System.out.println(movieService.delete(movie));
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 10:
                        printList(movieService.findThisYearAndPreviousYearMovies());
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 11:
                        System.out.println("Введіть кількість років: ");
                        int numberOfYears = Integer.parseInt(scanner.nextLine());
                        movieService.deleteMoviesThatHaveReleaseDateGreaterThanN(numberOfYears);
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 12:
                        System.out.println("Введіть назву фільму: ");
                        String movName = scanner.nextLine();
                        List<Movie> movieList = movieService.findMoviesByName(movName);
                        if(movieList.isEmpty())
                            throw new DAOException("Фільм не був знайдений");
                        System.out.println("Введіть прізвище актора (він повинен бути присутній у базі даних): ");
                        String actorName = scanner.nextLine();
                        ActorService actorService = new ActorService();
                        List<Actor> actorList = actorService.findActorByLastName(actorName);
                        if(actorList.isEmpty())
                            throw new DAOException("Актор не знайдений");
                        System.out.println(movieService.addActorToMovie(movieList.get(0), actorList.get(0)));
                        System.out.println("Натисніть Enter щоб продовжити...");
                        scanner.nextLine();
                        break;
                    case 14: exit(0);
                }
            }
            catch (Exception ex){
                ex.printStackTrace();
                System.out.println("Будь-ласка, введіть значення в межах від 1 до " + options.length);
                System.out.println("Натисніть Enter щоб продовжити...");
                scanner.nextLine();
            }
        }
    }
    private static void addMovie(){
        Movie movie = createMovieEntity();
        try {
            boolean isAdded = movieService.add(movie);
            System.out.println(isAdded);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    private static void alterMovie(){
        System.out.println("Введіть назву фільму, який потрібно змінити: ");
        String name = scanner.nextLine();
        try {
            Movie movie = movieService.findMoviesByName(name).get(0);
            alterMovieEntity(movie);
            System.out.println(movieService.alter(movie));
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    private static Movie createMovieEntity() {
        System.out.println("Введіть назву фільму: ");
        String name = scanner.nextLine();
        System.out.println("Введіть дату виходу фільму в форматі 'yyy-MM-dd': ");
        String stringDate = scanner.nextLine();
        Date date = null;
        try {
            date = new Date(sdf.parse(stringDate).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Введіть країну, що випустила фільм: ");
        String country = scanner.nextLine();
        System.out.println("Введіть прізвище режисера фільму: ");
        String directorName = scanner.nextLine();
        Movie movie = new Movie(name, date, country, directorName, new ArrayList<>());
        return movie;
    }

    private static void alterMovieEntity(Movie sourceMovie) {
        System.out.println("Змінюйте дані за необхідністю або вводьте попередні дані...");
        System.out.println("Введіть назву фільму: ");
        sourceMovie.setName(scanner.nextLine());
        System.out.println("Введіть дату виходу фільму в форматі 'yyy-MM-dd': ");
        String stringDate = scanner.nextLine();
        Date date = null;
        try {
            sourceMovie.setReleaseDate(new Date(sdf.parse(stringDate).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Введіть країну, що випустила фільм: ");
        sourceMovie.setCountry(scanner.nextLine());
        System.out.println("Введіть прізвище режисера фільму: ");
        sourceMovie.setDirectorName(scanner.nextLine());
    }
}
