package com.example.software_eng_asoee_2024.view.PrescriptionCreation.PatientSearching;

import com.example.software_eng_asoee_2024.dao.Initializer;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.memorydao.MemoryInitializer;
import com.example.software_eng_asoee_2024.memorydao.PatientDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;
import com.example.software_eng_asoee_2024.views.PrescriptionCreation.PatientSearching.PatientSearchingPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PatientSearchingPresenterTest {

    PatientSearchingViewStub viewStub;
    PatientSearchingPresenter presenter;

    /**
     * Πριν τα τεστ, δημιουργεί και αρχικοποιεί την μνήμη του presenter.
     */
    @Before
    public void setup(){
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        viewStub = new PatientSearchingViewStub();
        presenter = new PatientSearchingPresenter();
        presenter.setView(viewStub);
        presenter.setPatientDAO(new PatientDAOMemory());
    }

    /**
     * Ελέγχει αν επιστρέφει το σωστό view.
     */
    @Test
    public void checkGetView(){
        presenter.setView(viewStub);
        Assert.assertEquals(presenter.getView(), viewStub);
    }

    /**
     * Ελέγχει αν θα επιστρέψει οτι υπάρχει σφάλμα, όταν δίνεται αμκα που δεν υπάρχει.
     */
    @Test
    public void patientNotFoundCheck(){
        presenter.findPatient("1213433", "The Flu");
        Assert.assertEquals(viewStub.getErrorMessage(), "Patient not found.");
    }

    /**
     * Ελέγχει αν θα επιστρέψει οτι ολα οκ, όταν δίνεται αμκα που υπάρχει.
     */
    @Test
    public void patientFoundCheck(){
        presenter.findPatient("123123123", "The Flu");
        Assert.assertNull(viewStub.getErrorMessage());
    }

    /**
     * Ελέγχει αν θα εμφανίζεται το κατάλληλο μήνυμα, για λάθος ssn format.
     */
    @Test
    public void wrongSSNFormatCheck(){
        presenter.findPatient("123123a23", "The Flu");
        Assert.assertEquals(viewStub.getErrorMessage(), "Invalid SSN format.");
    }

    /**
     * Ελέγχει αν θα εμφανίζεται το κατάλληλο μήνυμα, όταν λείπει κάποιο input.
     */
    @Test
    public void emptyFieldsCheck(){
        presenter.findPatient("", "The Flu");
        Assert.assertEquals(viewStub.getErrorMessage(), "Fields can't be empty.");

        presenter.findPatient("", "");
        Assert.assertEquals(viewStub.getErrorMessage(), "Fields can't be empty.");

        presenter.findPatient("123123123", "");
        Assert.assertEquals(viewStub.getErrorMessage(), "Fields can't be empty.");
    }
}
