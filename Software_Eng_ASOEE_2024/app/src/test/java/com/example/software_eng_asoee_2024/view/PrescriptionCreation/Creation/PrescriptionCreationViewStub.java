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
        errorMessage = message;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
