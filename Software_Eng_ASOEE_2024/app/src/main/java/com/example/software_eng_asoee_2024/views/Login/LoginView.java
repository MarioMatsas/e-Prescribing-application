package com.example.software_eng_asoee_2024.views.Login;

public interface LoginView {
    void login();
    void navigateToDoctorScreen();
    void navigateToPharmacistScreen();
    void navigateToSignUpScreen();
    void showError(String message);
}
