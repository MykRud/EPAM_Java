package task_advanced.task_7;

import task_advanced.task_7.DAOs.*;
import task_advanced.task_7.Menus.MainMenu;

import java.sql.*;
import java.text.ParseException;


public class Main {

    public static void main(String[] args) throws SQLException, ParseException, DAOException {
        MainMenu menu = new MainMenu();
        menu.start();
    }
}