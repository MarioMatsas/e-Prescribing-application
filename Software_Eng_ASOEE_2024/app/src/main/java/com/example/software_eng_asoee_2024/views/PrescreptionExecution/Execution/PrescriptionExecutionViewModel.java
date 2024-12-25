package com.example.software_eng_asoee_2024.views.PrescreptionExecution.Execution;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.software_eng_asoee_2024.domain.PharmacudicalProduct;
import com.example.software_eng_asoee_2024.memorydao.PatientDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmacudicalProductDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;
import com.example.software_eng_asoee_2024.views.PrescreptionExecution.Selection.PrescriptionSelectionPresenter;

public class PrescriptionExecutionViewModel extends ViewModel {
    private PrescriptionExecutionPresenter presenter;

    public PrescriptionExecutionViewModel() {
        presenter = new PrescriptionExecutionPresenter();
        presenter.setPrescriptionDAO(new PrescriptionDAOMemory());
        presenter.setPharmacudicalProdcutDAO(new PharmacudicalProductDAOMemory());
    }

    public PrescriptionExecutionPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("PrescriptionSelection", "On Cleared");
    }
}
