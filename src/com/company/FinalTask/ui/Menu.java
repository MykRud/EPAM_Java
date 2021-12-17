package com.company.FinalTask.ui;

import com.company.FinalTask.business.exception.ServiceExceptions;
import com.company.FinalTask.business.services.Errors;
import com.company.FinalTask.business.services.Service;
import jakarta.xml.bind.JAXBException;
import org.xml.sax.SAXException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void run() throws ServiceExceptions {
        try {
            Scanner scanner = new Scanner(System.in);
            View.print("Натисність будь-яку клавішу щоб розпочати....");
            scanner.nextLine();
            Service service = new Service();
            service.run();
            View.print("Виберіть дію: ");
            View.print("/add country (назва) - додати країну");
            View.print("/add city (назва) - додати місто");
            View.print("/remove country (назва) - видалити країну");
            View.print("/remove city (назва) - видалити місто");
            View.print("/view countries - переглянути список країн");
            View.print("/view cities - переглянути список міст");
            View.print("/find country (назва/код) - знайти країну по назві чи індексу");
            View.print("/find city (назва/код) - знайти місто по назві чи індексу");
            View.print("/find cities (назва країни/код) - знайти всі міста країни");
            View.print("/change population (назва) - змінити кількість населення міста");
            View.print("/exit - вийти з програми");

            do {
                String str = scanner.nextLine();
                if (str.equals("/exit")) {
                    service.save();
                    break;
                }
                try {
                    String[] result = str.split(" ");
                    switch (result[0] + " " + result[1]) {
                        case ("/add country") -> service.addCountry(result[2]);
                        case ("/add city") -> {
                            View.print("Введіть назву країни, до якої відноситься місто: ");
                            String country = scanner.nextLine();
                            View.print("Введіть кількість населення міста: ");
                            int population = scanner.nextInt();
                            scanner.nextLine();
                            View.print("Це столиця? (так/ні)");
                            String isCapital = scanner.nextLine();
                            service.addCity(result[2], country, population, isCapital);
                        }
                        case ("/remove country") -> service.removeCountry(result[2]);
                        case ("/remove city") -> service.removeCity(result[2]);
                        case ("/view countries") -> service.getListOfCountries();
                        case ("/view cities") -> service.getListOfCities();
                        case ("/find country") -> service.findCountry(result[2]);
                        case ("/find city") -> service.findCity(result[2]);
                        case ("/find cities") -> service.viewAllCitiesOfCountry(result[2]);
                        case ("/change population") -> {
                            View.print("Введіть нову кількість населення: ");
                            int people = scanner.nextInt();
                            scanner.nextLine();
                            service.changePopulationOfCity(result[2], people);
                        }
                        default -> throw new ArrayIndexOutOfBoundsException();
                    }
                } catch (ArrayIndexOutOfBoundsException | InputMismatchException | ServiceExceptions e) {
                    View.print(Errors.SYNTAX_ERROR.toString());
                }
            } while (true);

        } catch(JAXBException | SAXException e){
            throw new ServiceExceptions(Errors.DATABASE_ERROR.toString());
        }

    }
}