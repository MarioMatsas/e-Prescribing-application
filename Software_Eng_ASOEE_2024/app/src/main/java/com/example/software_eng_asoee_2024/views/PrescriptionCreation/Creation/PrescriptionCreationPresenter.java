package com.example.software_eng_asoee_2024.views.PrescriptionCreation.Creation;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.PrescriptionLine;
import com.example.software_eng_asoee_2024.domain.Status;
import com.example.software_eng_asoee_2024.domain.Unit;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.DoctorDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PatientDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.ReportObjectDAOMemory;
import com.example.software_eng_asoee_2024.views.PrescriptionCreation.Creation.PrescriptionCreationView;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionCreationPresenter {
    private PrescriptionCreationView view;
    private PrescriptionDAOMemory prescriptionDAO;
    private ActiveSubstanceDAOMemory activeSubstanceDAO;
    private Doctor doctor;
    private Patient patient;
    private Prescription prescription;
    private PatientDAOMemory patientDAO;
    private DoctorDAOMemory doctorDAO;
    private ActiveSubstanceDAOMemory activeSubDAO;
    private ReportObjectDAOMemory reportDAO;
    private ArrayList<Double> amounts = new ArrayList<Double>();

    public PrescriptionCreationView getView() {
        return view;
    }

    public void setView(PrescriptionCreationView view) {
        this.view = view;
    }

    /**
     * Αρχικοποιεί τον presenter με τις χρήσιμες, για την συνταγή, πληροφορίες.
     * Οι πληροφορίες θεωρείται ΚΑΙ είναι σωστές. Αλλιώς δεν θα είμασταν
     * στο στάδιο της δημιουργίας συνταγής.
     * @param name όνομα γιατρού
     * @param surname επώνυμο γιατρού
     * @param ssn ΑΜΚΑ ασθενή
     * @param diagnosis διάγνωση συνταγής
     */
    public void init(String name, String surname, int ssn, String diagnosis){
        doctor = doctorDAO.find(name, surname);
        System.out.println(doctor);
        patient = patientDAO.find(ssn);
        System.out.println(patient);
        prescription = new Prescription(diagnosis, doctor, patient);
        prescription.setStatus(Status.PENDING);
    }

    public void setPrescriptionDAO(PrescriptionDAOMemory prescriptionDAO) {
        this.prescriptionDAO = prescriptionDAO;
    }
    public void setPatientDAO(PatientDAOMemory patientDAO) {
        this.patientDAO = patientDAO;
    }
    public void setDoctorDAO(DoctorDAOMemory doctorDAO) {
        this.doctorDAO = doctorDAO;
    }
    public void setSubstanceDAO(ActiveSubstanceDAOMemory activeSubDAO) {
        this.activeSubDAO = activeSubDAO;
    }

    public void setReportDAO(ReportObjectDAOMemory reportDAO){
        this.reportDAO = reportDAO;
    }

    /**
     * Επιστρέφει την συγκεκριμένει συνταγή, που υπάρχει στην μνήμη.
     * @param Id το id της συγκεκριμένης συνταγής που θέλουμε να βρούνε στην μνήμη
     * @return Επιστρέφει την συγκεκριμένει συνταγή, που υπάρχει στην μνήμη.
     */
    public Prescription getPrescription(int Id){
        return prescriptionDAO.findPrescriptionById(Id);
    }

    /**
     * Επιστρέφει όλες τις δραστικές ουσίες που υπάρχουν στην μνήμη.
     * @return επιστρέφει όλες τις δραστικές ουσίες που υπάρχουν στην μνήμη
     */
    public List<ActiveSubstance> getActiveSubs(){
        return activeSubDAO.findAll();
    }

    /**
     * Δημιουργεί την συνταγή και τη αποθηκεύει στην μνήμη.
     * Αν δεν υπάρχουν ακόμη γραμμές στην συνταγή, δεν δημιουργείται τίποτα.
     * @return επιστρέφει αν ήταν επιτυχής(true) ή αποτυχής(false) η δημιουργία.
     */
    public boolean createPrescription(){
        if (prescription.getPrescriptionLines().isEmpty()){
            view.showError("Prescription is empty");
            return false;
        }
        prescriptionDAO.save(prescription);

        for (int i = 0; i < amounts.size(); i++){
            PrescriptionLine line = prescription.getPrescriptionLines().get(i);
            reportDAO.update(prescription.getDoctor(), prescription.getPatient(), line.getActiveSubstance(), prescription.getDate(), amounts.get(i));
        }
        return true;
    }

    /**
     * Δημιουργεί και προσθέτει μια γραμμή στην συνταγή.
     * Αν υπάρχει κάποιο λάθος στην είσοδο, δεν γίνεται τίποτα.
     * @param concAmount περιεκτικότητα της ουσίας που χορηγείται
     * @param unit μονάδα μέτρησης του form
     * @param pdAmount ποσότητα ανα μέρα από την συγκεκριμένη ουσία
     * @param sub δραστική ουσία
     * @param Instructions οδηγίες για την συγκεκριμένη γραμμή
     * @param form μορφή φαρμάκου
     * @param days μέρες εκτέλεσης της γραμμής από τον ασθενή
     * @return επιστρέφει αν ήταν επιτυχής(true) ή αποτυχής(false) η προσθήκη.
     */
    public Boolean addPrescriptionline(ActiveSubstance sub, Form form, String concAmount, String unit, String pdAmount, String days, String Instructions){
        //boolean so we can run tests
        if (errorsFound(form, concAmount, pdAmount, days)){
            return false;
        }
        Unit u = Unit.valueOf(unit);
        prescription.addLine(new PrescriptionLine(form, new Concentration(Double.parseDouble(concAmount), u), pdAmount+", "+days+", "+Instructions, sub));
        // The check has already been completed so parsing into double is fine.
        amounts.add(Double.parseDouble(concAmount)*Double.parseDouble(pdAmount)*Double.parseDouble(days));
        view.clearFields();
        return true;
    }

    /**
     * Ελέγχει να δεί αν υπάρχει κάποιο λάθος στην είσοδο.
     * Συγκεκριμένα, κοιτάει αν μπορούν να γίνουν σωστά parse τα string δεδομένα βάσει περιορισμών.
     * @param concAmount περιεκτικότητα της ουσίας που χορηγείται
     * @param pdAmount ποσότητα ανα μέρα
     * @param form μορφή φαρμάκου
     * @param days μέρες που πρέπει ο ασθενής να ακολουθήσει αυτή την γραμμή συνταγής
     * @return επιστρέφει αν βρέθηκε σφάλμα(true) ή οχι(false)
     */
    public boolean errorsFound(Form form, String concAmount, String pdAmount, String days){
        if (error(concAmount, "double")) return true;

        if (form.equals(Form.PILL) || form.equals(Form.SPRAY)){
            if (error(pdAmount, "int")) return true;
        }
        else{
            if (error(pdAmount, "double")) return true;
        }

        if (error(days, "int")) return true;
        return false;
    }

    /**
     * Ελέγχει να δεί αν υπάρχει κάποιο λάθος στην είσοδο.
     * Συγκεκριμένα, κοιτάει αν μπορούν να γίνει σωστά parse το string δεδομένο,
     * ανάλογα τον τύπο του.
     * @param stringToCheck το string του οποίου περιεχόμενο ελέγχουμε.
     * @param type επιθυμητός τύπος δεδομένων του περιεχομένου του stringToCheck
     * @return επιστρέφει αν βρέθηκε σφάλμα(true) ή οχι(false)
     */
    public boolean error(String stringToCheck, String type){
        if (stringToCheck.isEmpty()) {  // Check if the stringToCheck field is empty
            view.showError("Make sure all fields are filled");
            return true;
        }
        try {
            if (type.equals("int")){
                int num = Integer.parseInt(stringToCheck);
            }
            else{
                double num = Double.parseDouble(stringToCheck);
            }
            return false;
        }
        catch (NumberFormatException e) {
            view.showError("Make sure to enter numbers");
            return true;
        }
    }
}
