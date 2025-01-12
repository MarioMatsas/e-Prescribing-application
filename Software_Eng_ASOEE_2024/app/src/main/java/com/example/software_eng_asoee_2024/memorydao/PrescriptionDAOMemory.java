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

    public void delete(Prescription entity) {
        entities.remove(entity);
    }

    public List<Prescription> findAll() {
        ArrayList<Prescription> result = new ArrayList<Prescription>(entities);
        return result;
    }

    public void save(Prescription entity) {
        entities.add(entity);
    }

    public Prescription findPrescriptionById(int Id){
        for (Prescription prescr : entities){
            if (prescr.getId()==Id){
                return prescr;
            }
        }
        return null;
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
