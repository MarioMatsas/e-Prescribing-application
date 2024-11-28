import java.util.ArrayList;
import java.util.List;

public class Prescription {
    private List<PrescriptionLine> prescriptionLines;
    private Doctor doctor;
    private Patient patient;
    public final String diagnosis; // TODO change visability in diagram
    private Status status;
    public final Date date;

    public Prescription(String diagnosis, Status status, Date date, Doctor doctor, Patient patient, PrescriptionLine prescriptionLine) {
        this.diagnosis = diagnosis;
        this.status = status;
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
        this.prescriptionLines = new ArrayList<>(prescriptionLine); // needs to have atleast 1 prescription line
    }
    // TODO
    //public String getDiagnosis() {
    //    return this.diagnosis;
    //}

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    //public Date getDate() {
    //    return this.date;
    //}

    public void addLine(PrescriptionLine line) {
        this.prescriptionLines.add(line);
    }

    public String getDoctorInfo(){
        String info = "Doctor Info: Name: "+this.doctor.getFirstName()+" | Surname: "+this.doctor.getLastName()+" | Profession: "+this.doctor.getProfession();
        return info;
    }
}