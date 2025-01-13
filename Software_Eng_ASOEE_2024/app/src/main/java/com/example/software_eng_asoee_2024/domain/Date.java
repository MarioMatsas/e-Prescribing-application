package com.example.software_eng_asoee_2024.domain;/* DONE  *//* DONE */
// import java.time.LocalDate; TODO we can use this but we need different jdk version ( >= 26, curr is 24 )
import java.io.Serializable;
import java.util.Calendar;
public class Date implements Serializable {
    private Integer year;
    private Integer month;
    private Integer day;

    /**
     * Ελέγχει αν το δοθέν έτος είναι δίσεκτο.
     * @param year το έτος που ελέγχουμε αν είναι δίσεκτο
     * @return επιστρέφει true αν είναι δίσεκτο, αλλιώς false
     */
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
     * Ελέγχει αν ο μήνας έχει τόσες μέρες.
     * Κάνει ένα πρόσθετο έλεγχο για τον φλεβάρη, που σε δίσεκτο έτος έχει 29.
     * @param year το έτος που εξετάζουμε
     * @param month ο μήνας που εξετάζουμε
     * @return επιστρέφει true αν είναι δίσεκτο, αλλιώς false
     */
    private static int getDaysInMonth(int year, int month) {
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        }
        return month % 2 == 1 ? 31 : 30;
    }

    /**
     * Ελέγχει αν είναι σωστή η μέρα, ανάλογα τον μήνα και το έτος.
     * @param year το έτος που ελέγχουμε
     * @param month ο μήνας που ελέγχουμε
     * @param day η μέρα που ελέγχουμε
     * @return true αν είναι λάθος η μέρα, αλλιώς false
     */
    private static boolean isInvalidDay(int year, int month, int day) {
        return day < 1 || day > getDaysInMonth(year, month);
    }

    public Date() {
        Calendar now = Calendar.getInstance();
        this.year = now.get(Calendar.YEAR);
        this.month = now.get(Calendar.MONTH) + 1; // Calendar.MONTH is zero-based
        this.day = now.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Ο κατσκευαστής.
     * Αν δεί ότι είναι λάθος ο μήνας (όχι 1-12) ή η μέρα, πετά exception.
     * @param year το έτος που ελέγχουμε
     * @param month ο μήνας που ελέγχουμε
     * @param day η μέρα που ελέγχουμε
     */
    public Date(Integer year, Integer month, Integer day) {
        if (month > 12 || month < 1)
            throw new IllegalArgumentException("Month has to be 1-12");
        if (isInvalidDay(year, month, day))
            throw new IllegalArgumentException("Day has to be valid within the month");
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Integer getYear() {
        return this.year;
    }

    /**
     * Ελέχει, πρίν θέσει το έτος, άν έχει δοθεί κάτι λάθος στην μέρα.
     * @param year το έτος που ελέγχουμε
     */
    public void setYear(Integer year) {
        // Revalidate the day in case the year affects leap year calculations
        if (isInvalidDay(year, this.month, this.day)) {
            throw new IllegalArgumentException("Day has to be valid within the month for the given year.");
        }
        this.year = year;
    }

    public Integer getMonth() {
        return this.month;
    }

    /**
     * Ελέχει, πρίν θέσει τον μήνα, άν έχει δοθεί κάτι λάθος στον μήνα ή στην μέρα.
     * @param month ο μήνας που ελέγχουμε
     */
    public void setMonth(Integer month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month has to be 1-12");
        }
        // Revalidate the day in case the year affects leap year calculations
        if (isInvalidDay(this.year, month, this.day)) {
            throw new IllegalArgumentException("Day has to be valid within the new month for the given year.");
        }
        this.month = month;
    }

    public Integer getDay() {
        return this.day;
    }

    /**
     * Ελέχει, πρίν θέσει τον χρόνο, αν έχει δοθεί κάτι λάθος στην μέρα.
     * @param day η μέρα που ελέγχουμε
     */
    public void setDay(Integer day) {
        // Revalidate the day in case the year affects leap year calculations
        if (isInvalidDay(this.year, this.month, day)) {
            throw new IllegalArgumentException("Day has to be valid within the month for the given year.");
        }
        this.day = day;
    }
}
