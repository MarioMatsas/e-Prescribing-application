package com.example.software_eng_asoee_2024.view.ActiveSubstance.Deletion;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Deletion.ActiveSubstanceDeleteView;

import java.util.ArrayList;
import java.util.List;

public class ActiveSubstanceDeleteViewStub implements ActiveSubstanceDeleteView {
    public String msg = "";
    public List<ActiveSubstance> acList;
    @Override
    public void createActiveSubstanceSpinner(List<ActiveSubstance> activeSubstances) {
        acList = activeSubstances;
    }

    @Override
    public void deleteActiveSubstance(ActiveSubstance as) {

    }

    @Override
    public void showMessage(String s) {
        msg = s;
    }
}
