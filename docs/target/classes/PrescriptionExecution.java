public class PrescriptionExecution {
    private Pharmacist pharmacist;
    private Prescription prescription;
    private List<ProductQuantity> productQuantities = new ArrayList<ProductQuantity>();
    private Date completionDate;

    public PrescriptionExecution(Pharmacist pharmacist, Prescription prescription) {
        this.pharmacist = pharmacist;
        this.prescription = prescription;
        this.completionDate = new Date();
    }

    public Integer calculateTotalCost() {
        int totalCost = 0;
        for (ProductQuantity productQuantity : productQuantities) {
            totalCost += productQuantity.quantity * productQuantity.getProduct().getFinalPrice();
        }
    }
}
