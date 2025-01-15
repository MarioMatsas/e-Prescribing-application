package com.example.software_eng_asoee_2024.views.NOHCS;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;

public class NOHCSSelectionViewModel extends ViewModel {
    private NOHCSSelectionPresenter presenter;

    public NOHCSSelectionViewModel(){
        presenter = new NOHCSSelectionPresenter();
    }

    public NOHCSSelectionPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("NOHCSSelection", "On Cleared");
    }
}
