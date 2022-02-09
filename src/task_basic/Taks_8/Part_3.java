package task_basic.Taks_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Part_3 {
    public static void main(String[] args) throws FileNotFoundException {
        // Створюємо абстракцію файла
        Scanner scan = getScanner("words2.txt");

        String[] wordsArray = getWords(scan);

        int[] indexes = getIndexes(wordsArray);

        String[] str = calculation(wordsArray, indexes);


        System.out.println(str[0] + ", " + str[1] + ", " + str[2]);


    }

    public static String[] calculation(String[] wordsArray, int[] indexes) {
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


        String[] str = new String[3];
        str[0] = new StringBuilder(firstValue.toUpperCase()).reverse().toString();
        str[1] = new StringBuilder(secondValue.toUpperCase()).reverse().toString();
        str[2] = new StringBuilder(thirdValue.toUpperCase()).reverse().toString();

        return str;

    }

    public static int[] getIndexes(String[] wordsArray) {
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
        return indexes;
    }

    public static String[] getWords(Scanner scan) {
        // Створюємо масив з тексту файла
        String fromFile = scan.nextLine();
        String[] wordsArray = fromFile.split(" ");
        return wordsArray;
    }

    public static Scanner getScanner(String filePath) throws FileNotFoundException {
        String separator = File.separator;
        String path = "src"+separator+"com"+separator+"company"+separator+"Taks_8"+separator+filePath;
        File file = new File(path);

        // Передаємо файл в сканер
        Scanner scan = new Scanner(file);
        return scan;
    }
}
