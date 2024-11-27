public class ProductQuantity {
    private PharmacudicalProduct product;
    public final Integer quantity;


    public ProductQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public PharmacudicalProduct getProduct() {
        return this.product;
    }

    public void setProduct(PharmacudicalProduct product) {
        this.product = product;
    }
}
