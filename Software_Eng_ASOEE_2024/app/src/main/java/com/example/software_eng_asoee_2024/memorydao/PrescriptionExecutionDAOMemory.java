package com.example.software_eng_asoee_2024.memorydao;

import com.example.software_eng_asoee_2024.dao.PrescriptionDAO;
import com.example.software_eng_asoee_2024.dao.PrescriptionExecutionDAO;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.PrescriptionExecution;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionExecutionDAOMemory implements PrescriptionExecutionDAO {
    static ArrayList<PrescriptionExecution> entities = new ArrayList<PrescriptionExecution>();

    /**
     * Deletes the selected PrescriptionExecution
     * @param entity selected PrescriptionExecution
     */
    public void delete(PrescriptionExecution entity) {
        entities.remove(entity);
    }

    /**
     *
     * @return all the PrescriptionExecution objects
     */
    public List<PrescriptionExecution> findAll() {
        ArrayList<PrescriptionExecution> result = new ArrayList<PrescriptionExecution>(entities);
        return result;
    }

    /**
     * Saves the selected PrescriptionExecution
     * @param entity selected PrescriptionExecution
     */
    public void save(PrescriptionExecution entity) {
        entities.add(entity);
    }

    /**
     * Returns the PrescriptionExecution which has the Prescription with the given Id
     * @param Id Id of the Prescription in the PrescriptionExecution
     * @return the selected PrescriptionExecution
     */
    public PrescriptionExecution findByPrescriptionId(int Id){
        for (PrescriptionExecution prescexe : entities){
            if (prescexe.getPrescription().getId() == Id){
                return prescexe;
            }
        }
        return null;
    }
}