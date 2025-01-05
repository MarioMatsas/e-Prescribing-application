package com.example.software_eng_asoee_2024.view.PrescriptionExecution.Execution;

import com.example.software_eng_asoee_2024.domain.PharmacudicalProduct;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.PrescriptionLine;
import com.example.software_eng_asoee_2024.views.PrescreptionExecution.Execution.PrescriptionExecutionView;

import java.util.List;

public class PrescriptionExecutionViewStub implements PrescriptionExecutionView {
    private String errorMessage;
    private String updateSpinnerMessage;
    public void updateDisplayInfo(Prescription prescription, int currentIndex){

    }

    public void updateLineInfo(PrescriptionLine line){

    }

    public void updateProductSpinner(List<PharmacudicalProduct> products){
        updateSpinnerMessage = String.valueOf(products.size());
    }

    public void nextLine(Prescription prescription, int index){

    }

    public void finishExecution(Prescription prescription){

    }

    public boolean addProduct(Prescription prescription, int index, boolean finalLine){
        return true;
    }

    public void clearProductSpinner(){

    }

    public void showError(String message){
        errorMessage = message;
    }

    public String getErrorMessage(){
        return errorMessage;
    }

    public String getUpdateMessage(){
        return updateSpinnerMessage;
    }

}
