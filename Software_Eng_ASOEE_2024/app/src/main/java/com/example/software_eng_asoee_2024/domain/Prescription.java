package com.example.software_eng_asoee_2024.domain;/* DONE  */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Prescription implements Serializable {
    private List<PrescriptionLine> prescriptionLines;
    private final Doctor doctor;
    private final Patient patient;

    private final String diagnosis;
    private final Date date;
    private Status status;

    public Prescription(String diagnosis, Doctor doctor, Patient patient) {
        if (doctor == null)
            throw new IllegalArgumentException("Doctor cant be null");
        if (patient == null)
            throw new IllegalArgumentException("Patient cant be null");
        this.diagnosis = diagnosis;
        this.status = Status.PENDING;
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
        return "Doctor Info: Name: " + this.doctor.getFirstName() + " | Surname: " + this.doctor.getLastName() + " | Specialty: " + this.doctor.getSpecialty();
    }

    public Patient getPatient() {
        return patient;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public Date getDate() {
        return date;
    }

    public List<PrescriptionLine> getPrescriptionLines() {
        return prescriptionLines;
    }

    public String toString() {
        String activeSubstances = "";
        for (PrescriptionLine line : prescriptionLines){
            activeSubstances += line.getActiveSubstance().getName() +" | ";
        }
        return activeSubstances;
    }
}