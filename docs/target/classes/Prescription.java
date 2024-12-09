import java.util.ArrayList;
import java.util.List;

public class Prescription {
    private List<PrescriptionLine> prescriptionLines = new ArrayList<PrescriptionLine>();
    private Doctor doctor;
    private Patient patient;
    public final String diagnosis;
    private Status status;
    public final Date date;

    public Prescription(String diagnosis, Status status, Doctor doctor, Patient patient) {
        this.diagnosis = diagnosis;
        this.status = status;
        this.date = new Date();
        this.doctor = doctor;
        this.patient = patient;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return this.date;
    }

    public void addLine(PrescriptionLine line) {
        this.prescriptionLines.add(line);
    }

    public String getDoctorInfo(){
        String info = "Doctor Info: Name: "+this.doctor.getFirstName()+" | Surname: "+this.doctor.getLastName()+" | Specialty: "+this.doctor.getSpecialty();
        return info;
    }
}