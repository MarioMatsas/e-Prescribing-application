public class Concentration {
    private Integer quantity;
    private Μeasurement measurement;

    public Concentration() {
    }

    public Concentration(Integer quantity, Μeasurement measurement) {
        this.quantity = quantity;
        this.measurement = measurement;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Μeasurement getΜeasurement() {
        return this.measurement;
    }

    public void setMeasurement(Μeasurement measurement) {
        this.measurement = measurement;
    }

}
