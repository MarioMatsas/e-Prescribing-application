package com.example.software_eng_asoee_2024.view.PrescriptionCreation.PatientSearching;

import com.example.software_eng_asoee_2024.views.PrescriptionCreation.PatientSearching.PatientSearchingView;

public class PatientSearchingViewStub implements PatientSearchingView {

    private String errorMessage;

    public void prepareForCreate(){

    }
    public void navigateToCreation(Integer patientSSN){

    }

    public void showError(String message){
        errorMessage = message;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
