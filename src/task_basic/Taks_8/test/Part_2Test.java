package task_basic.Taks_8.test;

import task_basic.Taks_8.Part_2;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class Part_2Test {

    Part_2 part = new Part_2();
    String separator = File.separator;
    String path = "src"+separator+"com"+separator+"company"+separator+"Taks_8"+separator+"words1.txt";

    @Test
    void calculate() throws FileNotFoundException {
        char[][] exp = {
                {'a', 's', 'd'},
                {'q', 'w', 'e'},
                {'s', 'z', 'c'}
        };
        Scanner scan = Part_2.getScanner("words1.txt");
        String[] wordsArray = Part_2.getWords(scan);
        char[][] real = Part_2.calculate(wordsArray);
        for(int i = 0; i < 2; i++){
            assertEquals(exp[i][0], real[i][0]);
        }
        assertEquals(19, real[0].length);
        assertEquals(14, real[1].length);
        assertEquals(10, real[2].length);
    }

    @Test
    void getScanner(){
        assertThrows(FileNotFoundException.class, () -> Part_2.getScanner("fail.txt"));
    }

    @Test
    void getWords() throws FileNotFoundException {
        String[] exc = {"qweqweasd", "qwerqw", "sdfsd"};
        String[] real = Part_2.getWords(Part_2.getScanner("words1.txt"));
        for(int i = 0; i < 2; i++){
            assertEquals(exc[i], real[i]);
        }
    }
}