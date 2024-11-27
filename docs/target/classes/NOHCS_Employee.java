public class NOHCS_Employee extends Person{
    
    private int LastEmpId;
    protected int empId;

    public NOHCS_Employee(String fn, String ln, int lEmpId, int emId){
        super(fn, ln);
        LastEmpId = lEmpId;
        empId = emId;
    }

    protected calculateSalary(Month month){}

    public void managePharmaceuticalProduct(PharmaceuticalProduct product){}
    
    public void manageActiveSubstance(Substance substance){}
}
