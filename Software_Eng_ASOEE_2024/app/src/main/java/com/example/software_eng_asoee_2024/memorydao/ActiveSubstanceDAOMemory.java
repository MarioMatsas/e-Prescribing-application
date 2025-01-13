package com.example.software_eng_asoee_2024.memorydao;

import com.example.software_eng_asoee_2024.dao.ActiveSubstanceDAO;
import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Doctor;

import java.util.ArrayList;
import java.util.List;

public class ActiveSubstanceDAOMemory implements ActiveSubstanceDAO {
    static ArrayList<ActiveSubstance> entities = new ArrayList<ActiveSubstance>();

    /**
     * Βγάζει από τις διαθέσιμες ουσίες την συγκεκριμένη.
     * @param entity δραστική ουσία προς αφαίρεση
     */
    public void delete(ActiveSubstance entity) {
        entities.remove(entity);
    }

    /**
     * Βρίσκει και επιστρέφει όλες τις διαθέσιμες δραστικές ουσίες.
     * @return επιστρέφει όλες τις διαθέσιμες δραστικές ουσίες
     */
    public List<ActiveSubstance> findAll() {
        ArrayList<ActiveSubstance> result = new ArrayList<ActiveSubstance>();
        result.addAll(entities);
        return result;
    }

    /**
     * Αποθηκεύει δραστική ουσία στην λίστα με τις διαθέσιμες.
     * @param entity δραστική ουσία που είναι να αποθηκευτεί
     */
    public void save(ActiveSubstance entity) {
        entities.add(entity);
    }

    /**
     * Επεξεργάζεται μια δραστική ουσία.
     * @param edit δραστική ουσία που είναι να τροποποιηθεί
     * @param editTo δραστική ουσία που είναι να αντιγραφούν τα στοιχεία της
     */
    public void edit(ActiveSubstance edit, ActiveSubstance editTo) {
        edit.setName(editTo.getName());
        editTo.setExpectedQuantityPerMonth(editTo.getExpectedQuantityPerMonth());
    }

    /**
     * Βρίσκει την δραστική ουσία με συγκερκιμένο όνομα name.
     * Αν δεν την βρεί, επιστρέφει null
     * @param name το όνομα της δραστικής ουσίας που θέλουμε να βρούμε
     * @return επιστρέφει την δραστική ουσία
     */
    public ActiveSubstance find(String name) {
        for (ActiveSubstance subst : entities)
            if (subst.getName().equals(name))
                return subst;
        // Not found
        return null;
    }
}
