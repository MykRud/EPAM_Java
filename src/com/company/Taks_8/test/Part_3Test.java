package com.company.Taks_8.test;

import com.company.Taks_8.Part_3;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class Part_3Test {

    @Test
    void calculation() throws FileNotFoundException {
        Scanner scan = Part_3.getScanner("words2.txt");
        String[] str = Part_3.getWords(scan);
        int[] indexes = Part_3.getIndexes(str);
        String[] exc = {"YTREWQ", "OLLEH", "YEKNOM"};
        String[] real = Part_3.calculation(str, indexes);
        for(int i = 0; i < 2; i++){
            assertEquals(exc[i], real[i]);
        }
    }

    @Test
    void getIndexes() throws FileNotFoundException {
        Scanner scan = Part_3.getScanner("words2.txt");
        String[] str = Part_3.getWords(scan);
        int[] exc = {4, 8, 12};
        int[] real = Part_3.getIndexes(str);
        for(int i = 0; i < 2; i++){
            assertEquals(exc[i], real[i]);
        }
    }

    @Test
    void getWords() throws FileNotFoundException {
        Scanner scan = Part_3.getScanner("words2.txt");
        String[] exc = {"qwerty", "hello", "world"};
        String[] real = Part_3.getWords(scan);
        for(int i = 0; i < 2; i++){
            assertEquals(exc[i], real[i]);
        }
    }

    @Test
    void getScanner() {
        assertThrows(FileNotFoundException.class, () -> Part_3.getScanner("fail.txt"));
    }
}