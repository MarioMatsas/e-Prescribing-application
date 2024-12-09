/* DONE */

public class ProductQuantity {
    private PharmacudicalProduct product;
    public final Integer quantity;

    public ProductQuantity(PharmacudicalProduct product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public PharmacudicalProduct getProduct() {
        return this.product;
    }
}
