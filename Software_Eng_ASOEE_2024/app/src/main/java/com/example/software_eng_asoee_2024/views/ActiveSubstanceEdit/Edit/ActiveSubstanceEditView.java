package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Edit;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;

import java.util.List;

public interface ActiveSubstanceEditView {
    void editActiveSubstance();
    void createActiveSubstanceSpinner(List<ActiveSubstance> activeSubstances);

    void showMessage(String s);
}
