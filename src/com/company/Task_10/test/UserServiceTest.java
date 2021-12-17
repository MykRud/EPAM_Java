package com.company.Task_10.test;

import com.company.Task_10.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    UserService users = new UserService();

    @BeforeAll
    void setup() throws IOException {
        users.fromURL("https://data.gov.ua/dataset/770cc750-4333-424f-b6e9-6e6c5c76aec9/resource/59cb6066-1fac-41ed-a571-811db551c75b/download/zp-lupen-2019.csv");
    }

    @Test
    void getMaxIncome() {
        assertEquals(BigDecimal.valueOf(127849.99), users.getMaxIncome().getSummaryIncome());
    }

    @Test
    void getMinIncome() {
        assertEquals(BigDecimal.valueOf(23719.81), users.getMinIncome().getSummaryIncome());

    }

    @Test
    void getMiddleIncome() {
        assertEquals(BigDecimal.valueOf(75784.90), users.getMiddleIncome());
    }
}