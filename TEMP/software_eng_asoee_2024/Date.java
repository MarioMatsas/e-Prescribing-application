import java.time.LocalDate;

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
        return month % 2 == 1 ? 31 : 30;
    }

    public Date() {
        LocalDate now = LocalDate.now();
        this.year = now.getYear();
        this.month = now.getMonthValue();
        this.day = now.getDayOfMonth();
    }

    public Date(Integer year, Integer month, Integer day) {
        if (month > 12 || month < 1)
            throw new IllegalArgumentException("Month has to be 1-12");
        if (day > getDaysInMonth(month, year) || day < 1)
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
    }

    public Integer getMonth() {
        return this.month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return this.day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

}
