package com.example.software_eng_asoee_2024.view.PrescriptionCreation.Creation;

import com.example.software_eng_asoee_2024.views.PrescriptionCreation.Creation.PrescriptionCreationView;

public class PrescriptionCreationViewStub implements PrescriptionCreationView {

    String errorMessage;

    public void populateActiveSubSpinner(){

    }
    public void setupFormSpinner(){

    }
    public void createPrescription(){

    }
    public void addLine(){

    }
    public void clearFields(){

    }
    public void showError(String message){

    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public void updateText(String amount, String days, String unit) {
        errorMessage = amount +" "+ days +" "+ unit;
    }
}
