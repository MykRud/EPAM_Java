package com.company.Taks_8;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

class Part_1Test {

    Part_1 part = new Part_1();
    String path = "src"+File.separator+"com"+File.separator+"company"+File.separator+"Taks_8"+File.separator+"words.txt";

    @Test
    void getArrayFromFile() throws FileNotFoundException {
        String[] str = {"ezhik", "apple", "apple", "panda"};
        String[] real = Part_1.getArrayFromFile(new File(path));
        for(int i = 0; i < 3; i++) {
            assertEquals(str[i], real[i]);
        }
    }

    @Test
    void testOfFileNotFoundException(){
        assertThrows(FileNotFoundException.class, () -> Part_1.getArrayFromFile(new File("fail.txt")));
    }

    @Test
    void calculate() throws FileNotFoundException {
        String[] str = Part_1.getArrayFromFile(new File(path));
        Arrays.sort(str);
        String[][] exc = new String[][]{
                {"ezhik"},
                {"apple"},
                {"panda"}
        };
        String[][] real = Part_1.calculate(str, Part_1.getNumberOfWords(str));
        assertEquals(exc[0][0], real[0][0]);
        assertEquals(exc[1][0], real[1][0]);
        assertEquals(exc[2][0], real[2][0]);
        assertEquals(20, real[0].length);
        assertEquals(19, real[1].length);
        assertEquals(15, real[2].length);
    }

    @Test
    void getNumberOfWords() throws FileNotFoundException {
        //3
        String[] str = Part_1.getArrayFromFile(new File(path));
        Arrays.sort(str);
        assertEquals(3, Part_1.getNumberOfWords(str));
    }
}