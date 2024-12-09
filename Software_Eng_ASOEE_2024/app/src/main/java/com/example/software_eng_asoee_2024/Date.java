package com.example.software_eng_asoee_2024;/* DONE  *//* DONE */
// import java.time.LocalDate; TODO we can use this but we need different jdk version ( >= 26, curr is 24 )
import java.util.Calendar;
public class Date {
    private Integer year;
    private Integer month;
    private Integer day;

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    private static int getDaysInMonth(int year, int month) {
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        }
        return (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) ? 31 : 30;
    }

    public Date() {
        Calendar now = Calendar.getInstance();
        this.year = now.get(Calendar.YEAR);
        this.month = now.get(Calendar.MONTH) + 1; // Calendar.MONTH is zero-based
        this.day = now.get(Calendar.DAY_OF_MONTH);
    }

    public Date(Integer year, Integer month, Integer day) {
        if (month > 12 || month < 1)
            throw new IllegalArgumentException("Month has to be 1-12");
        if (day > getDaysInMonth(year, month) || day < 1)
            throw new IllegalArgumentException("Day has to be valid within the month");
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
        // Revalidate the day in case the year affects leap year calculations
        if (this.day > getDaysInMonth(this.year, this.month)) {
            throw new IllegalArgumentException("Day has to be valid within the month for the given year.");
        }
    }

    public Integer getMonth() {
        return this.month;
    }

    public void setMonth(Integer month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month has to be 1-12");
        }
        this.month = month;
        // Revalidate the day in case the month affects the maximum day
        if (this.day > getDaysInMonth(this.year, this.month)) {
            throw new IllegalArgumentException("Day has to be valid within the new month for the given year.");
        }
    }

    public Integer getDay() {
        return this.day;
    }

    public void setDay(Integer day) {
        if (day < 1 || day > getDaysInMonth(this.year, this.month)) {
            throw new IllegalArgumentException("Day has to be valid within the month for the given year.");
        }
        this.day = day;
    }
}
