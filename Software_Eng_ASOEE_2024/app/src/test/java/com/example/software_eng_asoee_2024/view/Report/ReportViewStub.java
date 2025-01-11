package com.example.software_eng_asoee_2024.view.Report;

import com.example.software_eng_asoee_2024.views.Report.ReportView;

public class ReportViewStub implements ReportView {
    private String reportMsg;
    public void generateReport(){
        reportMsg = "REPORT COMPLETE";
    }

    public String getReportMsg(){
        return reportMsg;
    }
}
