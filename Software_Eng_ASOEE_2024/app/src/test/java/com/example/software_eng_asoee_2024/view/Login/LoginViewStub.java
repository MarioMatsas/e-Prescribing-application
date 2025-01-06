package com.example.software_eng_asoee_2024.view.Login;

import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.views.Login.LoginView;

public class LoginViewStub implements LoginView {
    private String errorMessage;
    private String navDocMessage;
    private String navPhMessage;
    @Override
    public void login() {

    }

    @Override
    public void navigateToDoctorScreen(Doctor doctor) {
        navDocMessage = "Success doctor";
    }

    @Override
    public void navigateToPharmacistScreen(Pharmacist pharmacist) {
        navPhMessage = "Success pharmacist";
    }

    @Override
    public void navigateToSignUpScreen() {

    }

    @Override
    public void showError(String message) {
        this.errorMessage = message;
    }

    public String getNavDocMessage() {
        return navDocMessage;
    }

    public String getNavPhMessage() {
        return navPhMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
