import java.util.ArrayList;
import java.util.List;

public class Prescription {
    private List<PrescriptionLine> prescriptionLines;
    private Doctor doctor;
    private Patient patient;

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public final String diagnosis;
    private Status status;
    public final Date date;

    public Prescription(String diagnosis, Status status, Doctor doctor, Patient patient) {
        if (doctor == null)
            throw new IllegalArgumentException("Doctor cant be null");
        if (patient == null)
            throw new IllegalArgumentException("Patient cant be null");
        this.diagnosis = diagnosis;
        this.status = status;
        this.date = new Date();
        this.doctor = doctor;
        this.patient = patient;
        prescriptionLines = new ArrayList<PrescriptionLine>();
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void addLine(PrescriptionLine line) {
        this.prescriptionLines.add(line);
    }

    public String getDoctorInfo() {
        String info = "Doctor Info: Name: " + this.doctor.getFirstName() + " | Surname: " + this.doctor.getLastName() + " | Specialty: " + this.doctor.getSpecialty();
        return info;
    }

    public Patient getPatient() {
        return patient;
    }
}