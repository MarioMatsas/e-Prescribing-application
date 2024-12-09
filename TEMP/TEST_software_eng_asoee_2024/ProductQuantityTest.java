import org.junit.Assert;
import org.junit.Test;

public class ProductQuantityTest {
    private static ProductQuantity qnt;

    @Before
    public void init() {
        qnt = new ProductQuantity(new PharmacudicalProduct("name", 10, Form.PILL, MedicineType.GENERIC), 10);
    }

    @Test
    public void testInit() {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            this.qnt = new ProductQuantity(null, 10);
        });
    }

    @Test
    public void test() {
        Assert.assertEquals("name", qnt.getProduct().getName());
        Assert.assertEquals(10, qnt.quantity);
    }

}