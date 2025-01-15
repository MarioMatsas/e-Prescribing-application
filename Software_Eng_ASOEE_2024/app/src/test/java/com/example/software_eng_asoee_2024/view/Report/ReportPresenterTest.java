package com.example.software_eng_asoee_2024.view.Report;

import com.example.software_eng_asoee_2024.dao.Initializer;
import com.example.software_eng_asoee_2024.dao.ReportObjectDAO;
import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Date;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.DoctorDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.MemoryInitializer;
import com.example.software_eng_asoee_2024.memorydao.PatientDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.ReportObjectDAOMemory;
import com.example.software_eng_asoee_2024.views.Report.ReportPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ReportPresenterTest {
    ReportViewStub viewStub;
    ReportPresenter presenter;

    @Before
    public void setup(){
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        viewStub = new ReportViewStub();
        presenter = new ReportPresenter();
        presenter.setView(viewStub);
        presenter.setReportDAO(new ReportObjectDAOMemory());
    }

    /**
     * Test the view getter
     *
     */
    @Test
    public void getView(){
        presenter.setView(viewStub);
        Assert.assertEquals(viewStub, presenter.getView());
    }

    /**
     * Test the case that no report lines are added
     *
     */
    @Test
    public void getCleanReport(){
        ArrayList<String> reportLines = presenter.generateReport();
        Assert.assertTrue(reportLines.isEmpty());
    }

    /**
     * Test the case that a line is added
     *
     */
    @Test
    public void getSuspectReport(){
        new ReportObjectDAOMemory().update(new DoctorDAOMemory().find("m","m"),new PatientDAOMemory().find(123123123), new ActiveSubstanceDAOMemory().find("Paracetamol"), new Date(), 15.0);
        ArrayList<String> reportLines = presenter.generateReport();
        Assert.assertEquals(1, reportLines.size());
    }
}
