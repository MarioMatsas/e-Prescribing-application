package com.example.software_eng_asoee_2024.domain;/* DONE  */

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Prescription implements Serializable {
    private static Integer LastId = 0;
    private final Integer Id;
    private List<PrescriptionLine> prescriptionLines;
    private final Doctor doctor;
    private final Patient patient;

    private final String diagnosis;
    private final Date date;
    private Status status;

    /**
     * Ο κατασκευαστής.
     * Πρέπει να δίνονται γιατρός και ασθενής αλλιώς δεν έχουμε συνταγή (πετάγεται exception).
     * Αρχικοποιείται η συνταγή, στο ότι αναμένεται να εκτελεσθεί (pending).
     * @param diagnosis διάγνωση του ασθενούς
     * @param doctor ο γιατρός της συνταγής
     * @param patient ο ασθενής της συνταγής
     */
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
        this.Id = LastId + 1;
        LastId = Id;
    }
    public int getId(){
        return Id;
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
    public Doctor getDoctor() { return doctor; }

    public String getDiagnosis() {
        return diagnosis;
    }

    public Date getDate() {
        return date;
    }

    public List<PrescriptionLine> getPrescriptionLines() {
        return prescriptionLines;
    }

    /**
     * Επιστρέφεται, ως string, τη συνταγή.
     * Συγκεκριμένα, πρέπει να εμφανίζονται στην σειρά όλες οι ουσίες της συνταγής.
     * @return Επιστρέφεται, ως string, τη συνταγή
     */
    @NonNull
    public String toString() {
        String activeSubstances = doctor.getFirstName() + " " + doctor.getLastName() + ": ";
        for (PrescriptionLine line : prescriptionLines){
            activeSubstances += line.getActiveSubstance().getName() +" , ";
        }

        return activeSubstances;
    }
}