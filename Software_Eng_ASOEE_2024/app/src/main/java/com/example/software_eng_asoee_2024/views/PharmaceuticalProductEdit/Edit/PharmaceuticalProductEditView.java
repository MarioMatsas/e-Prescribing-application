package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Edit;

import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;

import java.util.List;

public interface PharmaceuticalProductEditView {
    void editPharmaceuticalProduct();
    void createPharmaceuticalProductSpinner(List<PharmaceuticalProduct> pharmaceuticalProducts);
}
