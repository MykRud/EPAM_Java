package com.company.Taks_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Part_1 {
    public static void main(String[] args) throws FileNotFoundException {
        // Створюємо абстракцію файла
        String separator = File.separator;
        String path = "C:" + separator + "Users" + separator + "Keeper" + separator + "Desktop" + separator + "words.txt";
        File file = new File(path);

        // Передаємо файл в сканер
        Scanner scan = new Scanner(file);

        // Створюємо масив з тексту файла
        String fromFile = scan.nextLine();
        String[] wordsArray = fromFile.split(" ");

        // Сортуємо масив слів
        Arrays.sort(wordsArray);

        // Визначаємо кількість слів у файлі
        int numberOfWords = 0;
        for (int i = 0; i < wordsArray.length - 1; i++) {
            if (!Objects.equals(wordsArray[i], wordsArray[i + 1]))
                numberOfWords++;
        }
        numberOfWords++;

        String[][] array = new String[numberOfWords][];
        //System.out.println(numberOfWords);

        // Визначаємо кількість окремо взятого слова. Спочатку створюємо пустий масив (заповнений нулями)
        int[] firstWord = new int[wordsArray.length];
        Arrays.fill(firstWord, 0);

        // Заповнюємо елементи пустого масива числа, що відображають кількість кожного окремо взятого слова
        int counter = 0;
        for (int i = 0; i < wordsArray.length - 1; i++) {
            firstWord[counter]++;
            if (!Objects.equals(wordsArray[i], wordsArray[i + 1]))
                counter++;
        }
        firstWord[counter]++;

        // Видаляємо зайві нулі
        firstWord = Arrays.copyOf(firstWord, counter + 1);

        // Цикл, який створить двовимірний масив, кожен з масив буде містити однакові слова
        int i = 0;
        int j = 0;
        for (int m = 0; m < firstWord[i]; m++) {
            array[i] = new String[firstWord[i]];
            m = 0;
            while (j < wordsArray.length - 1) {
                array[i][m++] = wordsArray[j];
                if (!Objects.equals(wordsArray[j], wordsArray[j + 1])) {
                    m = i;
                    i++;
                    j++;
                    break;
                }
                j++;
            }
        }

        // Sorting
        boolean isSorted = false;
        String[] temp;
        while (!isSorted) {
            isSorted = true;
            for (int q = 0; q < array.length - 1; q++) {
                if (array[q].length < array[q + 1].length) {
                    isSorted = false;

                    temp = array[q];
                    array[q] = array[q + 1];
                    array[q + 1] = temp;
                }
            }
        }
        for (int q = 0; q < 3; q++) {
            System.out.println(array[q][0] + " => " + array[q].length);
        }
    }
}
