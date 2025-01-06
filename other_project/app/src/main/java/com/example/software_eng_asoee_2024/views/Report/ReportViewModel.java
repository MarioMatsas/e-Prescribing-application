package com.example.software_eng_asoee_2024.views.Report;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.software_eng_asoee_2024.memorydao.DoctorDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmacistDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.ReportObjectDAOMemory;
import com.example.software_eng_asoee_2024.views.Login.LoginPresenter;

public class ReportViewModel extends ViewModel {
    private ReportPresenter presenter;

    public ReportViewModel() {
        presenter = new ReportPresenter();
        presenter.setReportDAO(new ReportObjectDAOMemory());

    }

    public ReportPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("Login", "On Cleared");
    }
}
