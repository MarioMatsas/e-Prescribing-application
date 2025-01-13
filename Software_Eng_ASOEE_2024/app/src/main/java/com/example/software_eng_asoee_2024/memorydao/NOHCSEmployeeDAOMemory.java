package com.example.software_eng_asoee_2024.memorydao;

import com.example.software_eng_asoee_2024.dao.NOHCSEmployeeDAO;
import com.example.software_eng_asoee_2024.domain.NOHCS_Employee;

import java.util.ArrayList;
import java.util.List;

public class NOHCSEmployeeDAOMemory implements NOHCSEmployeeDAO {

    static ArrayList<NOHCS_Employee> entities = new ArrayList<NOHCS_Employee>();

    /**
     * Βγάζει από τους διαθέσιμους υπαλλήλους τον συγκεκριμένο.
     * @param entity υπάλληλο προς αφαίρεση
     */
    public void delete(NOHCS_Employee entity) {
        entities.remove(entity);
    }

    /**
     * Βρίσκει και επιστρέφει όλους τους διαθέσιμους υπαλλήλους.
     * @return επιστρέφει όλους τους διαθέσιμους υπαλλήλους.
     */
    public List<NOHCS_Employee> findAll() {
        return new ArrayList<NOHCS_Employee>(entities);
    }

    /**
     * Αποθηκεύει τον υπάλληλο στην λίστα με τους διαθέσιμους.
     * @param entity υπάλληλο που είναι να αποθηκευτεί
     */
    public void save(NOHCS_Employee entity) {
        entities.add(entity);
    }

    /**
     * Βρίσκει τον υπάλληλο με συγκερκιμένο όνομα και επίθετο.
     * Αν δεν τον βρεί, επιστρέφει null
     * @param firstName το όνομα του υπαλλήλου που θέλουμε να βρούμε
     * @param lastName το επώνυμο του υπαλλήλου που θέλουμε να βρούμε
     * @return επιστρέφει τον υπάλληλο
     */
    public NOHCS_Employee find(String firstName, String lastName) {
        for (NOHCS_Employee employee : entities)
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName))
                return employee;
        // Not found
        return null;
    }
}
