package task_basic.Taks_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part_2{
    public static void main(String[] args) throws FileNotFoundException {
        // Створюємо абстракцію файла
        Scanner scan = getScanner("words1.txt");

        // Створюємо масив з тексту файла
        String[] wordsArray = getWords(scan);

        char[][] lengths = calculate(wordsArray);

        for(int i = 0; i < lengths.length; i++){
            System.out.println(wordsArray[i] + " => " + lengths[i].length);
        }
    }

    public static char[][] calculate(String[] wordsArray) {
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
        return lengths;
    }

    public static String[] getWords(Scanner scan) {
        String fromFile = scan.nextLine();
        String[] wordsArray = fromFile.split(" ");
        return wordsArray;
    }

    public static Scanner getScanner(String fileName) throws FileNotFoundException {
        String separator = File.separator;
        String path = "src"+separator+"com"+separator+"company"+separator+"Taks_8"+separator+fileName;
        File file = new File(path);

        // Передаємо файл в сканер
        Scanner scan = new Scanner(file);
        return scan;
    }

}
