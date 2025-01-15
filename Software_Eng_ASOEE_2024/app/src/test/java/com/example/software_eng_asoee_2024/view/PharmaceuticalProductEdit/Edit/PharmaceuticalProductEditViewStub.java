package com.example.software_eng_asoee_2024.view.PharmaceuticalProductEdit.Edit;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Edit.PharmaceuticalProductEditView;

import java.util.List;

public class PharmaceuticalProductEditViewStub implements PharmaceuticalProductEditView {
    public String msg;
    public List<PharmaceuticalProduct> ppSpinner;
    public List<ActiveSubstance> acSpinner;
    @Override
    public void editPharmaceuticalProduct() {

    }

    @Override
    public void createPharmaceuticalProductSpinner(List<PharmaceuticalProduct> pharmaceuticalProducts) {
        ppSpinner = pharmaceuticalProducts;
    }

    @Override
    public void createActiveSubstanceSpinner(List<ActiveSubstance> activeSubstances) {
        acSpinner = activeSubstances;
    }

    @Override
    public void showMessage(String s) {
        msg = s;
    }

    @Override
    public void createActiveSubstanceList(){

    }
}
