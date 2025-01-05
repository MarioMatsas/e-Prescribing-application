package com.example.software_eng_asoee_2024.views.Login;

import com.example.software_eng_asoee_2024.domain.Pharmacist;

public interface LoginView {
    void login();
    void navigateToDoctorScreen(Doctor doctor);
    void navigateToPharmacistScreen(Pharmacist pharmacist);
    void navigateToSignUpScreen();
    void showError(String message);
}
