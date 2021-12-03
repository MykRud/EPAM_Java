package com.company.Taks_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Part_2{
    public static void main(String[] args) throws FileNotFoundException {
        // Створюємо абстракцію файла
        String separator = File.separator;
        String path = "C:" + separator + "Users" + separator + "Keeper" + separator + "Desktop" + separator + "words1.txt";
        File file = new File(path);

        // Передаємо файл в сканер
        Scanner scan = new Scanner(file);

        // Створюємо масив з тексту файла
        String fromFile = scan.nextLine();
        String[] wordsArray = fromFile.split(" ");

        boolean isSorted = false;
        String temp;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < wordsArray.length - 1; i++) {
                if (wordsArray[i].toCharArray().length < wordsArray[i+1].toCharArray().length) {
                    isSorted = false;

                    temp = wordsArray[i];
                    wordsArray[i] = wordsArray[i + 1];
                    wordsArray[i + 1] = temp;
                }
            }
        }

        char[][] lengths = new char[3][];
        for(int i = 0; i < lengths.length; i++){
            lengths[i] = wordsArray[i].toCharArray();
        }

        for(int i = 0; i < lengths.length; i++){
            System.out.println(wordsArray[i] + " => " + lengths[i].length);
        }
    }

}
