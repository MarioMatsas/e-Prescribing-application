package com.example.software_eng_asoee_2024.view.ActiveSubstance.Creation;

import com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Creation.ActiveSubstanceCreationView;

public class ActiveSubstanceCreateViewStub implements ActiveSubstanceCreationView {
    public String msg = "";
    @Override
    public void addActiveSubstance() {

    }

    @Override
    public void showMessage(String s) {
        msg = s;
    }
}
