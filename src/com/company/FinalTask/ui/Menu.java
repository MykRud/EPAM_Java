package com.company.FinalTask.ui;

import com.company.FinalTask.business.exception.ServiceExceptions;
import com.company.FinalTask.business.services.Errors;
import com.company.FinalTask.business.services.Service;
import jakarta.xml.bind.JAXBException;
import org.xml.sax.SAXException;

import java.util.InputMismatchException;

public class Menu {
    public static void run() throws ServiceExceptions {
        View.runConsole();
        View.print("Натисність будь-яку клавішу щоб розпочати....");
        View.read();
        ask();
        Service service;
        try {
            service = new Service();
            service.run();
        } catch (JAXBException | SAXException e) {
            throw new ServiceExceptions(Errors.DATABASE_ERROR.toString());
        }
        do {
            try {
                String str = View.read();
                if (str.equals("/exit")) {
                    service.save();
                    break;
                } else if(str.equals("/help")){
                    ask();
                    continue;
                }
                String[] result = str.split(" ");
                switch (result[0] + " " + result[1]) {
                    case ("/add country") -> service.addCountry(result[2]);
                    case ("/add city") -> addCityMenu(service, result[2]);
                    case ("/remove country") -> service.removeCountry(result[2]);
                    case ("/remove city") -> service.removeCity(result[2]);
                    case ("/view countries") -> service.getListOfCountries();
                    case ("/view cities") -> service.getListOfCities();
                    case ("/find country") -> service.findCountry(result[2]);
                    case ("/find city") -> service.findCity(result[2]);
                    case ("/find cities") -> service.viewAllCitiesOfCountry(result[2]);
                    case ("/change population") -> changePopulationMenu(service, result[2]);
                    default -> throw new ArrayIndexOutOfBoundsException();
                }
            } catch (ArrayIndexOutOfBoundsException | InputMismatchException | ServiceExceptions e) {
                View.print(Errors.SYNTAX_ERROR.toString());
            }
        } while (true);
    }

    private static void ask() {
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
        View.print("/help - допомога");
        View.print("/exit - вийти з програми");
    }

    private static void changePopulationMenu(Service service, String nameOfCity) throws ServiceExceptions {
        View.print("Введіть нову кількість населення: ");
        int people = Integer.parseInt(View.read());
        service.changePopulationOfCity(nameOfCity, people);
    }

    private static void addCityMenu(Service service, String name) throws ServiceExceptions {
        View.print("Введіть назву країни, до якої відноситься місто: ");
        String country = View.read();
        View.print("Введіть кількість населення міста: ");
        int population = Integer.parseInt(View.read());
        View.print("Це столиця? (так/ні)");
        String isCapital = View.read();
        service.addCity(name, country, population, isCapital);
    }
}