package com.example.software_eng_asoee_2024.memorydao;

import com.example.software_eng_asoee_2024.dao.PrescriptionDAO;
import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.PrescriptionLine;
import com.example.software_eng_asoee_2024.domain.Unit;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAOMemory implements PrescriptionDAO {
    static ArrayList<Prescription> entities = new ArrayList<Prescription>();

    public PrescriptionDAOMemory(){
        PatientDAOMemory pmem = new PatientDAOMemory();
        ActiveSubstanceDAOMemory submem = new ActiveSubstanceDAOMemory();
        Patient p1 = pmem.find(123123123);

        Prescription presc = new Prescription("Wolff-Parkinson-White", new Doctor("John", "Doe", "Cardiology"), p1);
        PrescriptionLine line = new PrescriptionLine(Form.PILL, new Concentration(10, Unit.mg_per_g), "For 10 days", submem.find("Paracetamol"));
        presc.addLine(line);
        line = new PrescriptionLine(Form.PILL, new Concentration(40, Unit.mg_per_g), "For 20 days", submem.find("Diddy Juice"));
        presc.addLine(line);
        save(presc);

        presc = new Prescription("White", new Doctor("John", "Doe", "Cardiology"), p1);
        line = new PrescriptionLine(Form.PILL, new Concentration(10, Unit.mg_per_g), "For 10 days", submem.find("Paracetamol"));
        presc.addLine(line);
        save(presc);

        presc = new Prescription("JUICE", new Doctor("John", "Doe", "Cardiology"), p1);
        line = new PrescriptionLine(Form.PILL, new Concentration(10, Unit.mg_per_g), "For 10 days", submem.find("Diddy Juice"));
        presc.addLine(line);
        save(presc);

        presc = new Prescription("White", new Doctor("John", "Doe", "Cardiology"), p1);
        line = new PrescriptionLine(Form.PILL, new Concentration(10, Unit.mg_per_g), "For 10 days", submem.find("Paracetamol"));
        presc.addLine(line);
        line = new PrescriptionLine(Form.PILL, new Concentration(10, Unit.mg_per_g), "For 10 days", submem.find("Paracetamol"));
        presc.addLine(line);
        save(presc);
    }

    public void delete(Prescription entity) {
        entities.remove(entity);
    }

    public List<Prescription> findAll() {
        ArrayList<Prescription> result = new ArrayList<Prescription>();
        result.addAll(entities);
        return result;
    }

    public void save(Prescription entity) {
        entities.add(entity);
    }

    public List<Prescription> findPrescriptionByPatient(Patient patient){
        ArrayList<Prescription> result = new ArrayList<Prescription>();
        for (Prescription prescr : entities){
            if (prescr.getPatient().equals(patient)){
                result.add(prescr);
            }
        }
        return result;
    }
}
