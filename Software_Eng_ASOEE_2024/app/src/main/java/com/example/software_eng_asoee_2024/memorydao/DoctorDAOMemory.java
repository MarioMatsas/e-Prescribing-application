package com.example.software_eng_asoee_2024.memorydao;

import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.dao.DoctorDAO;

import java.util.ArrayList;
import java.util.List;

public class DoctorDAOMemory implements DoctorDAO {

    static ArrayList<Doctor> entities = new ArrayList<Doctor>();

    public void delete(Doctor entity) {
        entities.remove(entity);
    }

    public List<Doctor> findAll() {
        ArrayList<Doctor> result = new ArrayList<Doctor>();
        result.addAll(entities);
        return result;
    }

    public void save(Doctor entity) {
        entities.add(entity);
    }

    public Doctor find(String firstName, String lastName) {
        for (Doctor doctor : entities)
            if (doctor.getFirstName().equals(firstName) && doctor.getLastName().equals(lastName))
                return doctor;
        // Not found
        return null;
    }
}
