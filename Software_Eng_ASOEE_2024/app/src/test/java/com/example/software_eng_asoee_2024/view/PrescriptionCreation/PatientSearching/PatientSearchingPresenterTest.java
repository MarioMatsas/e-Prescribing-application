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

    @Before
    public void setup(){
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        viewStub = new PatientSearchingViewStub();
        presenter = new PatientSearchingPresenter();
        presenter.setView(viewStub);
        presenter.setPatientDAO(new PatientDAOMemory());
    }

    @Test
    public void patientFoundCheck(){
        presenter.findPatient(String.valueOf(1231230023), "The Flu");
        Assert.assertEquals(viewStub.getErrorMessage(), "Patient not found.");
    }

    @Test
    public void wrongSSNFormatCheck(){
        presenter.findPatient("123123a23", "The Flu");
        Assert.assertEquals(viewStub.getErrorMessage(), "Invalid SSN format.");
    }

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
