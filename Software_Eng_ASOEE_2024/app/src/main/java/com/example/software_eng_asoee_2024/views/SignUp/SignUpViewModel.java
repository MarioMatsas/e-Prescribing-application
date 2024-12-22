package com.example.software_eng_asoee_2024.views.SignUp;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.software_eng_asoee_2024.memorydao.DoctorDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmacistDAOMemory;

public class SignUpViewModel extends ViewModel  {
    private SignUpPresenter presenter;

    public SignUpViewModel() {
        presenter = new SignUpPresenter();
        presenter.setDoctorDAO(new DoctorDAOMemory());
        presenter.setPharmacistDAO(new PharmacistDAOMemory());

    }

    public SignUpPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("SignUp", "On Cleared");
    }
}
