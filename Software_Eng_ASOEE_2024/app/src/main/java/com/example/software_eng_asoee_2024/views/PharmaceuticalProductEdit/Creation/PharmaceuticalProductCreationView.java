package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Creation;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;

import java.util.List;

public interface PharmaceuticalProductCreationView {
    void addPharmaceuticalProduct();
    void addActiveSubstanceToPharmaceuticalProduct();
    void createActiveSubstanceSpinner(List<ActiveSubstance> activeSubstances);
}
