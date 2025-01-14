package com.example.software_eng_asoee_2024.view.PrescriptionExecution.Selection;

import com.example.software_eng_asoee_2024.dao.Initializer;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.memorydao.DoctorDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.MemoryInitializer;
import com.example.software_eng_asoee_2024.memorydao.PatientDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmacistDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;
import com.example.software_eng_asoee_2024.view.SignUp.SignUpViewStub;
import com.example.software_eng_asoee_2024.views.PrescreptionExecution.Selection.PrescriptionSelectionPresenter;
import com.example.software_eng_asoee_2024.views.SignUp.SignUpPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PrescriptionSelectionPresenterTest {
    PrescriptionSelectionViewStub viewStub;
    PrescriptionSelectionPresenter presenter;

    @Before
    public void setup(){
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        viewStub = new PrescriptionSelectionViewStub();
        presenter = new PrescriptionSelectionPresenter();
        presenter.setView(viewStub);
        presenter.setPatientDAO(new PatientDAOMemory());
        presenter.setPrescriptionDAO(new PrescriptionDAOMemory());
    }

    /**
     * Test view getter
     *
     */
    @Test
    public void getView(){
        Assert.assertEquals(viewStub, presenter.getView());
    }

    /**
     *
     * Check if there are no prescriptions
     */
    @Test
    public void noPrescriptionsTest(){
        new PatientDAOMemory().save(new Patient("2", "3", 4));
        presenter.showPatientPrescriptions("4");
        Assert.assertEquals(viewStub.getErrorMessage(), "No prescriptions found.");
    }

    /**
     *
     * Check if patient was not found is handled correctly
     */
    @Test
    public void patientNotFound(){
        presenter.showPatientPrescriptions("123123125");
        Assert.assertEquals(viewStub.getErrorMessage(), "Patient not found.");
    }

    /**
     *
     * Check if patient was found
     */
    @Test
    public void patientFound(){
        presenter.showPatientPrescriptions("123123123");
        Assert.assertEquals(viewStub.getUpdateMessage(), "message");
    }

    /**
     *
     * Check if the empty SSN is handled correctly
     */
    @Test
    public void emptySSN(){
        presenter.showPatientPrescriptions("");
        Assert.assertEquals(viewStub.getErrorMessage(), "SSN cannot be empty.");
    }

    /**
     *
     * Check if an invalid SSN is handled correctly
     */
    @Test
    public void invalidSSNFormat(){
        presenter.showPatientPrescriptions("123bc8d289");
        Assert.assertEquals(viewStub.getErrorMessage(), "Invalid SSN format.");
    }

    /**
     *
     * Check if the navigation to the execution phase was successful
     */
    @Test
    public void successExecutionNav(){
        Assert.assertTrue(presenter.navigateToExecution(new PrescriptionDAOMemory().findAll().get(0)));
    }

    /**
     *
     * Check if the navigation to the execution phase was unsuccessful
     */
    @Test
    public void notSuccessExecutionNav(){
        Assert.assertFalse(presenter.navigateToExecution(null));
    }

}
