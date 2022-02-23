package com.example.drink;

import org.junit.Test;

import com.example.drink.drinkmodel.Date;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

public class NewDayUnitTest {

    @Test
    public void testNewDays() {
        LocalDateTime t1 = LocalDateTime.of(2022, 2, 23, 20, 45);
        LocalDateTime t2 = LocalDateTime.of(2022, 2, 23, 21, 45);
        LocalDateTime t3 = LocalDateTime.of(2022, 2, 24, 1, 45);
        LocalDateTime t4 = LocalDateTime.of(2023, 2, 22, 1, 45);
        LocalDateTime t5 = LocalDateTime.of(2023, 2, 23, 0, 1);
        assertFalse(Date.isNewDay(t1, t2));
        assertTrue(Date.isNewDay(t2, t3));
        assertTrue(Date.isNewDay(t3, t4));
        assertTrue(Date.isNewDay(t4, t5));
    }

}
