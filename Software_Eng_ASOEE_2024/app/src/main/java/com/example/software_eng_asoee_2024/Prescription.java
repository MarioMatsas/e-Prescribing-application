package com.example.software_eng_asoee_2024;/* DONE  */
import java.util.ArrayList;
import java.util.List;


public class Prescription {
    private List<PrescriptionLine> prescriptionLines;
    private Doctor doctor;
    private Patient patient;
    private final String diagnosis; // TODO change visability in diagram
    private Status status;
    private final Date date;

    public Prescription(String diagnosis, Status status, Date date, Doctor doctor, Patient patient, PrescriptionLine prescriptionLine) {
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor null error");
        }
        if (patient == null) {
            throw new IllegalArgumentException("Patient null error");
        }
        if (prescriptionLine == null) {
            throw new IllegalArgumentException("PrescriptionLine null error");
        }
        this.diagnosis = diagnosis;
        this.status = status;
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
        this.prescriptionLines = new ArrayList<>(); // needs to have atleast 1 prescription line
        addLine(prescriptionLine);
    }
    // TODO
    public String getDiagnosis() {
        return this.diagnosis;
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

    // TODO check this
    //public Patient getPatient(){
    //    return this.patient;
    //}

    public void addLine(PrescriptionLine line) {
        this.prescriptionLines.add(line);
    }

    public String getDoctorInfo(){
        String info = "Doctor Info: Name: "+this.doctor.getFirstName()+" | Surname: "+this.doctor.getLastName()+" | Specialty: "+this.doctor.getSpecialty();
        return info;
    }
}