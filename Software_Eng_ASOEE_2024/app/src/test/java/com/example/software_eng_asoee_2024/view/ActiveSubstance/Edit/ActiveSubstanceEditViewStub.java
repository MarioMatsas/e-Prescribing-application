package com.example.software_eng_asoee_2024.view.ActiveSubstance.Edit;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Edit.ActiveSubstanceEditView;

import java.util.List;

public class ActiveSubstanceEditViewStub implements ActiveSubstanceEditView {
    public String msg;
    public List<ActiveSubstance> acList;

    @Override
    public void createActiveSubstanceSpinner(List<ActiveSubstance> activeSubstances) {
        acList = activeSubstances;
    }

    @Override
    public void editActiveSubstance() {

    }

    @Override
    public void showMessage(String s) {
        msg = s;
    }
}
