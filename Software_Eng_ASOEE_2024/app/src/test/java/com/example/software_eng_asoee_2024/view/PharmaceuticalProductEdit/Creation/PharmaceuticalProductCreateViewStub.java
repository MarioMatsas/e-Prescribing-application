package com.example.software_eng_asoee_2024.view.PharmaceuticalProductEdit.Creation;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Creation.PharmaceuticalProductCreationView;

import java.util.List;

public class PharmaceuticalProductCreateViewStub implements PharmaceuticalProductCreationView {
    public String msg;

    public List<ActiveSubstance> spinner;

    @Override
    public void addPharmaceuticalProduct() {

    }

    @Override
    public void addActiveSubstanceToPharmaceuticalProduct() {

    }

    @Override
    public void createActiveSubstanceSpinner(List<ActiveSubstance> activeSubstances) {
        spinner = activeSubstances;
    }

    @Override
    public void showMessage(String s) {
        msg = s;
    }

    @Override
    public void createActiveSubstanceList(){

    }
}
