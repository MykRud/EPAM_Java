package com.company.Taks_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Part_1 {

    public static void main(String[] args) throws FileNotFoundException {

        File file = createFile("words.txt");

        String[] wordsArray = getArrayFromFile(file);


        // Сортуємо масив слів
        Arrays.sort(wordsArray);

        // Визначаємо кількість слів у файлі
        int numberOfWords = getNumberOfWords(wordsArray);

        String[][] array = calculate(wordsArray, numberOfWords);

        for (int q = 0; q < 3; q++) {
            System.out.println(array[q][0] + " => " + array[q].length);
        }
    }

    public static File createFile(String nameOfFile){
        // Створюємо абстракцію файла
        String separator = File.separator;

        String path = "src"+separator+"com"+separator+"company"+separator+"Taks_8"+separator+nameOfFile;
        File file = new File(path);

        return file;
    }

    public static String[] getArrayFromFile(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        String fromFile = scan.nextLine();
        return fromFile.split(" ");
    }

    public static String[][] calculate(String[] wordsArray, int numberOfWords) {
        String[][] array = new String[numberOfWords][];

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

        // Сорутвання
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
        return array;

    }

    public static int getNumberOfWords(String[] arrayOfWords) {
        int numberOfWords = 0;
        for (int i = 0; i < arrayOfWords.length - 1; i++) {
            if (!Objects.equals(arrayOfWords[i], arrayOfWords[i + 1]))
                numberOfWords++;
        }
        numberOfWords++;
        return numberOfWords;
    }
}
