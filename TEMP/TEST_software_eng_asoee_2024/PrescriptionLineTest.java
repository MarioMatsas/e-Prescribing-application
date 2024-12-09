public class PrescriptionLineTest {
    static PrescriptionLine line;

    @Before
    public void init() {
        line = new PrescriptionLine(Form.CREAM, new Concentration(10, Μeasurement.mg_per_g), "For 10 days", new ActiveSubstance("Paracetamol", 10d));
    }

    @Test
    public void testInit() {
        Assert.assertEquals(line.form, Form.CREAM);
        Assert.assertEquals(line.concentration.getQuantity(), 10);
        Assert.assertEquals(line.instructions, "For 10 days");
        Assert.assertEquals(line.getActiveSubstance().getName(), "Paracetamol");
    }

    @Test
    public void testSet() {
        line.setActiveSubstance(new ActiveSubstance("Ibuprofen", 5d));
        Assert.assertEquals(line.getActiveSubstance().getName(), "Ibuprofen");
    }
}