package com.example.software_eng_asoee_2024.views.Report;

import com.example.software_eng_asoee_2024.dao.DoctorDAO;
import com.example.software_eng_asoee_2024.dao.PharmacistDAO;
import com.example.software_eng_asoee_2024.dao.ReportObjectDAO;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.views.Login.LoginView;

import java.util.ArrayList;
import java.util.HashMap;

public class ReportPresenter {
    private ReportView view;
    private ReportObjectDAO reportDAO;

    public ReportView getView() {
        return view;
    }

    public void setView(ReportView view) {
        this.view = view;
    }

    public void setReportDAO(ReportObjectDAO reportDAO) {
        this.reportDAO = reportDAO;
    }

    public ArrayList<String> generateReport(){
        HashMap<Doctor, Integer> doctorsOffences = reportDAO.getUnlawfulDoctors();
        ArrayList<String> reportLines = new ArrayList<String>();
        for (Doctor doc : doctorsOffences.keySet()) {
            System.out.println("FOUND");
            String line = "Dr. " + doc.getFirstName() +" "+doc.getLastName()+", has committed "+doctorsOffences.get(doc)+" offenses";
            reportLines.add(line);
        }
        System.out.println(reportLines.size());
        return reportLines;
    }
}
