import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

public class DateTest {
    public static Date date;

    @Before
    public void init() {
        this.date = new Date();
    }

    @Test
    public void testInit() {
        Assert.assertEquals(LocalDate.now().getYear(), date.getYear());
        Assert.assertEquals(LocalDate.now().getMonth(), date.getMonth());
        Assert.assertEquals(LocalDate.now().getDayOfMonth(), date.getDay());
    }

    @Test
    public void testSet() {
        date.setYear(2004);
        Assert.assertEquals(2004, date.getYear());

        date.setMonth(5);
        Assert.assertEquals(5, date.getMonth());

        date.setDay(27);
        Assert.assertEquals(27, date.getDay());
    }

    @Test
    public void testIllegalArguments() {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            date.setDay(40);
        });
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            date.setMonth(2);
            date.setDay(31);
        });
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            date.setMonth(40);
        });
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            this.date = new Date(2000, 2, 40);
        });
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            this.date = new Date(2000, 40, 1);

        });
    }

}