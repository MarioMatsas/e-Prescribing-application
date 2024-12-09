import java.util.ArrayList;
import java.util.List;

public class PrescriptionExecution {
    private final Pharmacist pharmacist;

    private final Prescription prescription;
    private List<ProductQuantity> productQuantities;
    public final Date completionDate;

    public PrescriptionExecution(Pharmacist pharmacist, Prescription prescription) {
        this.completionDate = new Date();
        this.pharmacist = pharmacist;
        this.prescription = prescription;
        productQuantities = new ArrayList<ProductQuantity>();
    }

    public PrescriptionExecution(Pharmacist pharmacist, Prescription prescription, ArrayList<ProductQuantity> list) {
        this.completionDate = new Date();
        this.pharmacist = pharmacist;
        this.prescription = prescription;
        productQuantities = list;
    }

    public void addProductQuantity(ProductQuantity productQuantity) {
        this.productQuantities.add(productQuantity);
    }

    public Integer calculateTotalCost() {
        int totalCost = 0;
        for (ProductQuantity productQuantity : productQuantities) {
            totalCost += productQuantity.quantity * productQuantity.getProduct().getFinalPrice();
        }
        return totalCost;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public Pharmacist getPharmacist() {
        return pharmacist;
    }

}
