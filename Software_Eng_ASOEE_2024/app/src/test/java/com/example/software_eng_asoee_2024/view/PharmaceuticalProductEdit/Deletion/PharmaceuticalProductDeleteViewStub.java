package com.example.software_eng_asoee_2024.view.PharmaceuticalProductEdit.Deletion;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Deletion.PharmaceuticalProductDeleteView;

import java.util.List;

public class PharmaceuticalProductDeleteViewStub implements PharmaceuticalProductDeleteView {
    public String msg;
    public List<PharmaceuticalProduct> spinner;
    @Override
    public void createPharmaceuticalProductSpinner(List<PharmaceuticalProduct> pharmaceuticalProducts) {
        spinner = pharmaceuticalProducts;
    }

    @Override
    public void deletePharmaceuticalProduct(PharmaceuticalProduct as) {

    }

    @Override
    public void showMessage(String s) {
        msg = s;
    }
}
