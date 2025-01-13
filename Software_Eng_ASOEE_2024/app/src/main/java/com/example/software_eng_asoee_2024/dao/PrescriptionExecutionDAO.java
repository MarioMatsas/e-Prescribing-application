package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.PrescriptionExecution;

import java.util.List;

public interface PrescriptionExecutionDAO {
    /**
     * Deletes the selected PrescriptionExecution
     * @param entity selected PrescriptionExecution
     */
    void delete(PrescriptionExecution entity);

    /**
     *
     * @return all the PrescriptionExecution objects
     */
    List<PrescriptionExecution> findAll();

    /**
     * Saves the selected PrescriptionExecution
     * @param entity selected PrescriptionExecution
     */
    void save(PrescriptionExecution entity);

    /**
     * Returns the PrescriptionExecution which has the Prescription with the given Id
     * @param Id Id of the Prescription in the PrescriptionExecution
     * @return the selected PrescriptionExecution
     */
    PrescriptionExecution findByPrescriptionId(int Id);
}
