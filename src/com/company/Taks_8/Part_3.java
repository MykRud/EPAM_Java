package com.company.Taks_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part_3 {
    public static void main(String[] args) throws FileNotFoundException {
        // Створюємо абстракцію файла
        String separator = File.separator;
        String path = "C:" + separator + "Users" + separator + "Keeper" + separator + "Desktop" + separator + "words2.txt";
        File file = new File(path);

        // Передаємо файл в сканер
        Scanner scan = new Scanner(file);

        // Створюємо масив з тексту файла
        String fromFile = scan.nextLine();
        String[] wordsArray = fromFile.split(" ");



    }
}
