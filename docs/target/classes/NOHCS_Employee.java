public class NOHCS_Employee extends Person{
    
    private static int LastEmpId;
    protected int empId;

    public NOHCS_Employee(String fn, String ln){
        super(fn, ln);
        this.empId = LastEmpId + 1;
        LastEmpId = empId;
    }

    //protected calculateSalary(Month month){}

    public void managePharmaceuticalProduct(PharmaceuticalProduct product){}
    
    public void manageActiveSubstance(Substance substance){}
}

