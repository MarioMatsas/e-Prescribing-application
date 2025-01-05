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

    public Prescription getPrescription(int Id){
        return prescriptionDAO.findPrescriptionById(Id);
    }

    public List<ActiveSubstance> getActiveSubs(){
        return activeSubDAO.findAll();
    }

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

    public void addPrescriptionline(ActiveSubstance sub, Form form, String concAmount, String unit, String pdAmount, String days, String Instructions){
        if (errorsFound(form, concAmount, pdAmount, days)){
            return;
        }
        Unit u = Unit.valueOf(unit);
        prescription.addLine(new PrescriptionLine(form, new Concentration(Double.parseDouble(concAmount), u), pdAmount+", "+days+", "+Instructions, sub));
        // The check has already been completed so parsing into double is fine.
        amounts.add(Double.parseDouble(concAmount)*Double.parseDouble(pdAmount)*Double.parseDouble(days));
        view.clearFields();
    }

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

    public boolean error(String stringToCheck, String type){
        if (stringToCheck.isEmpty()) {  // Check if the SSN field is empty
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
