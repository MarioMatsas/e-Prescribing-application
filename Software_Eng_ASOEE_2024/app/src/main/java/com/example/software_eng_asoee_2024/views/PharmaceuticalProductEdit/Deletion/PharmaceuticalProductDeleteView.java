package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Deletion;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;

import java.util.ArrayList;
import java.util.List;

public interface PharmaceuticalProductDeleteView {
    void createPharmaceuticalProductSpinner(List<PharmaceuticalProduct> pharmaceuticalProducts);
    void deletePharmaceuticalProduct(PharmaceuticalProduct as);
    void showMessage(String s);
}
