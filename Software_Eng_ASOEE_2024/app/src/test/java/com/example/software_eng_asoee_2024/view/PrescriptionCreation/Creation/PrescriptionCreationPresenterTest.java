package com.example.software_eng_asoee_2024.view.PrescriptionCreation.Creation;

import com.example.software_eng_asoee_2024.dao.Initializer;
import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.Unit;
import com.example.software_eng_asoee_2024.memorydao.DoctorDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.MemoryInitializer;
import com.example.software_eng_asoee_2024.memorydao.PatientDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.ReportObjectDAOMemory;
import com.example.software_eng_asoee_2024.views.PrescriptionCreation.Creation.PrescriptionCreationPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PrescriptionCreationPresenterTest {
    PrescriptionCreationViewStub viewStub;
    PrescriptionCreationPresenter presenter;

    /**
     * Πριν τα τεστ, δημιουργεί και αρχικοποιεί την μνήμη του presenter.
     */
    @Before
    public void setup(){
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        viewStub = new PrescriptionCreationViewStub();
        presenter = new PrescriptionCreationPresenter();
        presenter.setView(viewStub);
        presenter.setPatientDAO(new PatientDAOMemory());
        presenter.setDoctorDAO(new DoctorDAOMemory());
        presenter.setPrescriptionDAO(new PrescriptionDAOMemory());
        presenter.setSubstanceDAO(new ActiveSubstanceDAOMemory());
        presenter.setReportDAO(new ReportObjectDAOMemory());
        presenter.init("m", "m", 123123123, "The Flu");
    }

    @Test
    public void notFoundPrescription(){
        Assert.assertNull(presenter.getPrescription(354557));
    }

    @Test
    public void foundActiveSubstances(){
        Assert.assertNotNull(presenter.getActiveSubs());
    }

    /**
     * Ελέγχει την λειτουρία της μεθόδου getView.
     */
    @Test
    public void checkGetView(){
        presenter.setView(viewStub);
        Assert.assertEquals(presenter.getView(), viewStub);
    }

    /**
     * Ελέγχει αν θα επιστραψεί false για άδεια συνταγή.
     */
    @Test
    public void emptyPrescriptionTest(){
        Assert.assertFalse(presenter.createPrescription());
    }

    /**
     * Ελέγχει αν θα επιστραψεί true για συνταγή που έχει γραμμές.
     */
    @Test
    public void createdPrescriptionTest(){
        presenter.addPrescriptionline(
                new ActiveSubstance("Aspirin", 22d), Form.PILL,
                "32.2", String.valueOf(Unit.mg_per_disk), "32", "8", "Every Morning");
        Assert.assertTrue(presenter.createPrescription());
    }

    /**
     * Ελέγχει αν όντως δεν θα δημιουργηθεί η γραμμή συνταγής, καθώς έχει λάθος input.
     */
    @Test
    public void prescriptionLineNotAdded(){
        //conc_amount and days are  wrong
        Assert.assertFalse(presenter.addPrescriptionline(
                new ActiveSubstance("Aspirin", 5d), Form.PILL,
                "3.0f", String.valueOf(Unit.mg_per_disk), "32.1", "22d", "Every Morning"));
    }

    /**
     * Ελέγχει αν όντως θα δημιουργηθεί η γραμμή συνταγής, καθώς έχει σωστό input.
     */
    @Test
    public void prescriptionLineAdded(){
        Assert.assertTrue(presenter.addPrescriptionline(
                new ActiveSubstance("Aspirin", 22d), Form.PILL,
                "32.2", String.valueOf(Unit.mg_per_disk), "32", "22", "Every Morning"));
    }

    @Test
    public void ErrorsFoundWrongConcAmount(){//concAmount should be double
        Assert.assertTrue(presenter.errorsFound(Form.PILL, "a2f", "3", "4"));
        Assert.assertTrue(presenter.errorsFound(Form.PILL, "a39", "3", "4"));
        Assert.assertTrue(presenter.errorsFound(Form.PILL, "True", "3", "4"));
    }

    /**
     * Ελέγχει αν θα επιστρέψει οτι δεν υπάρχει σφάλμα,
     * για σωστό input pdAmount για το συγκεκριμένο form.
     */
    @Test
    public void checkErrorsNotFoundpdAmountForm(){
        Assert.assertFalse(presenter.errorsFound(Form.PILL, "32.2", "3", "4"));
        Assert.assertFalse(presenter.errorsFound(Form.SPRAY, "32.2", "3", "4"));
        Assert.assertFalse(presenter.errorsFound(Form.CREAM, "32.2", "3.0", "4"));
        Assert.assertFalse(presenter.errorsFound(Form.SYRUP, "32.2", "3.0", "4"));

    }

    /**
     * Ελέγχει αν θα επιστρέψει οτι υπάρχει σφάλμα,
     * για λάθος input pdAmount, για το συγκεκριμένο form.
     */
    @Test
    public void checkErrorsFoundpdAmountForm(){//for pill or spray: pdAmount should be int, else: pdAmount should be double
        Assert.assertTrue(presenter.errorsFound(Form.PILL, "32.2", "afk", "4"));
        Assert.assertTrue(presenter.errorsFound(Form.PILL, "32.2", "32.0", "4"));

        Assert.assertTrue(presenter.errorsFound(Form.SPRAY, "32.2", "3.0f", "4"));

        Assert.assertTrue(presenter.errorsFound(Form.CREAM, "32.2f", "3a", "4"));

        Assert.assertTrue(presenter.errorsFound(Form.SYRUP, "32.2", "afg", "4"));
    }

    /**
     * Ελέγχει αν θα επιστρέψει οτι υπάρχει σφάλμα,
     * για λάθος input mer;vn, για το συγκεκριμένο form.
     */
    @Test
    public void checkErrorsFoundDays(){//days should be int
        Assert.assertTrue(presenter.errorsFound(Form.CREAM, "32.2", "3.0", "4.0f"));
        Assert.assertTrue(presenter.errorsFound(Form.SPRAY, "32.2", "3", "a"));

    }

    /**
     * Ελέγχει αν θα επιστρέψει οτι υπάρχει σφάλμα,
     * για λάθος input pdAmount, για το συγκεκριμένο form.
     */
    @Test
    public void checkErrorsFoundRightInput(){
        Assert.assertFalse(presenter.errorsFound(Form.SYRUP, "32.2", "3.0", "4"));
        Assert.assertFalse(presenter.errorsFound(Form.SPRAY, "32.2", "3", "12"));
    }


    /**
     * Ελέγχει αν θα επιστρέψει οτι υπάρχει σφάλμα για λάθος input pdAmount, για το συγκεκριμένο form
     */
    @Test
    public void noErrorsFound(){
        Assert.assertFalse(presenter.errorsFound(Form.PILL, "32.2", "3", "2"));
    }

    /**
     * Ελέγχει αν θα επιστρέψει οτι υπάρχει σφάλμα για λάθος input pdAmount, για το συγκεκριμένο form
     */
    @Test
    public void checkErrorEmptyString(){
        Assert.assertTrue(presenter.error("", "int"));

        Assert.assertTrue(presenter.error("", "double"));
    }


    /**
     * Ελέγχει αν θα επιστρέψει οτι υπάρχει σφάλμα για λάθος input.
     */
    @Test
    public void checkErrorWrongInput() {

        Assert.assertTrue(presenter.error("asdf ", "int"));

        Assert.assertTrue(presenter.error("fifo", "double"));

    }

    /**
     * Ελέγχει αν θα επιστρέψει οτι δεν υπάρχει σφάλμα για σωστό input.
     */
    @Test
    public void checkErrorRightInput() {

        Assert.assertFalse(presenter.error("12", "int"));

        Assert.assertFalse(presenter.error("12.3", "double"));

        Assert.assertFalse(presenter.error("12.3", "test string"));

    }

}
