package com.company.Taks_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
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

        int firstIndex, secondIndex, thirdIndex;
        int counter = 0;
        int[] indexes = new int[wordsArray.length];
        Arrays.fill(indexes, 0);
        int indexesCounter = 0;
        while(counter != wordsArray.length) {
            for (int i = 1; i < wordsArray.length; i++) {
                if (Objects.equals(wordsArray[counter], wordsArray[i]) && i != counter && wordsArray[i] != null) {
                    indexes[indexesCounter] = i;
                    wordsArray[i] = null;
                }
            }
            counter++;
            indexesCounter++;
        }

        String firstValue = "", secondValue = "", thirdValue = "";
        int firstInd = 0, secondInd = 0, thirdInd = 0;
        int count = 0;
        while(count != indexes.length) {
            for (int i = 0; i < indexes.length; i++) {
                if (indexes[count] > indexes[i] && indexes[i] != 0) {
                    thirdInd = secondInd;
                    secondInd = firstInd;
                    firstInd = indexes[i];
                }
            }
            count++;
        }

        for(int i = 0; i < indexes.length; i++){
            if(indexes[i] == thirdInd)
                firstValue = wordsArray[i];
            else if(indexes[i] == secondInd)
                secondValue = wordsArray[i];
            else if(indexes[i] == firstInd)
                thirdValue = wordsArray[i];
        }

        System.out.println(new StringBuilder(firstValue.toUpperCase()).reverse().toString() + ", "
                + new StringBuilder(secondValue.toUpperCase()).reverse().toString() + ", "
                + new StringBuilder(thirdValue.toUpperCase()).reverse().toString());

    }
}
