package com.example.software_eng_asoee_2024.views.Login;


import com.example.software_eng_asoee_2024.dao.DoctorDAO;
import com.example.software_eng_asoee_2024.dao.PharmacistDAO;

public class LoginPresenter {
    private LoginView view;
    private DoctorDAO doctorDAO;
    private PharmacistDAO pharmacistDAO;

    public LoginView getView() {
        return view;
    }

    public void setView(LoginView view) {
        this.view = view;
    }

    public void setDoctorDAO(DoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    public void setPharmacistDAO(PharmacistDAO pharmacistDAO) {
        this.pharmacistDAO = pharmacistDAO;
    }

}
