package com.example.drink.drinkmodel;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Date {

    public static boolean isNewDay(String then, String now) {
        LocalDateTime thenDateTime;
        LocalDateTime nowDateTime;
        try {
            thenDateTime = LocalDateTime.parse(then);
            nowDateTime = LocalDateTime.parse(now);
        } catch (DateTimeParseException e) {
            return true;
        }
        return isNewDay(thenDateTime, nowDateTime);
    }

    public static boolean isNewDay(LocalDateTime then, LocalDateTime now) {
        if (now.getYear() > then.getYear()) {
            return true;
        }
        else return now.getDayOfYear() > then.getDayOfYear() && (then.getYear() == now.getYear());
    }

    private Date() {

    }
}
