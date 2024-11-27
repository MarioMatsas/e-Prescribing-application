public class Concentration {
    private Integer quantity;
    private Unit unit;


    public Concentration() {
    }

    public Concentration(Integer quantity, Unit unit) {
        this.quantity = quantity;
        this.unit = unit;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Unit getUnit() {
        return this.unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

}
