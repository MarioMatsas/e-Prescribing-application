package com.example.software_eng_asoee_2024.views.Login;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.software_eng_asoee_2024.memorydao.PharmacistDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.DoctorDAOMemory;

public class LoginViewModel extends ViewModel {
    private LoginPresenter presenter;

    public LoginViewModel() {
        presenter = new LoginPresenter();
        presenter.setDoctorDAO(new DoctorDAOMemory());
        presenter.setPharmacistDAO(new PharmacistDAOMemory());

    }

    public LoginPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("Login", "On Cleared");
    }
}
