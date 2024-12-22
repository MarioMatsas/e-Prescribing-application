package com.example.software_eng_asoee_2024.views.Login;

public interface LoginView {
    void showError(String message);
    void navigateToDoctorScreen();
    void navigateToPharmacistScreen();
    void navigateToSignUpScreen();
}
