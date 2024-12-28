package com.example.software_eng_asoee_2024.view.PrescriptionExecution.Selection;

import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.views.PrescreptionExecution.Selection.PrescriptionSelectionPresenter;
import com.example.software_eng_asoee_2024.views.PrescreptionExecution.Selection.PrescriptionSelectionView;

import java.util.List;

public class PrescriptionSelectionViewStub implements PrescriptionSelectionView {
    private String errorMessage;
    private String updateMessage;

    public void navigateToExecution(){

    }
    public void showPatientPrescriptions(PrescriptionSelectionPresenter presenter){

    }
    public void updatePrescriptionSpinner(List<Prescription> prescriptions){
        updateMessage = "message";
    }
    public void clearPrescriptionSpinner(){

    }
    public void showError(String message){
        errorMessage = message;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public String getUpdateMessage(){
        return updateMessage;
    }
}
