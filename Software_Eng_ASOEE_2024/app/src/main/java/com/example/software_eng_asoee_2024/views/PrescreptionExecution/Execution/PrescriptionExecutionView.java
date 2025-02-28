package com.example.software_eng_asoee_2024.views.PrescreptionExecution.Execution;

import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.PrescriptionLine;

import java.util.List;

public interface PrescriptionExecutionView {
    void updateDisplayInfo(Prescription prescription, int currentIndex);
    void updateLineInfo(PrescriptionLine line);
    void updateProductSpinner(List<PharmaceuticalProduct> products);
    void nextLine(Prescription prescription, int index);
    void finishExecution(Prescription prescription);
    boolean addProduct(Prescription prescription, int index, boolean finalLine);
    void clearProductSpinner();
    void showError(String message);
}
