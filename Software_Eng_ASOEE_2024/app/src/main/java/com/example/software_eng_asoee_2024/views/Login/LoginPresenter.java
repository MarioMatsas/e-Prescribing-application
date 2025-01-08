package com.example.software_eng_asoee_2024.views.Login;


import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.NOHCS_Employee;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.dao.DoctorDAO;
import com.example.software_eng_asoee_2024.dao.PharmacistDAO;
import com.example.software_eng_asoee_2024.dao.NOHCSEmployeeDAO;

public class LoginPresenter {
    private LoginView view;
    private DoctorDAO doctorDAO;
    private PharmacistDAO pharmacistDAO;
    private NOHCSEmployeeDAO NOHCSEmployeeDAO;

    public LoginView getView() {
        return view;
    }

    public void setView(LoginView view) {
        this.view = view;
    }

    public void setDoctorDAO(DoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }
    public void setNOHCSEmployeeDAO(NOHCSEmployeeDAO NOHCSEmployeeDAO) {
        this.NOHCSEmployeeDAO = NOHCSEmployeeDAO;
    }

    public void setPharmacistDAO(PharmacistDAO pharmacistDAO) {
        this.pharmacistDAO = pharmacistDAO;
    }

    public void login(String username, String password){

        if (username.equals("admin") && password.equals("0000")){
            System.out.println(username);
            System.out.println(password);
            view.navigateToReportScreen();
            return;
        }
        // Search through the doctor accounts
        Doctor doc = doctorDAO.find(username, password);
        if (doc != null) {
            view.navigateToDoctorScreen(doc);
            return;
        }
        // Search through the pharmacist accounts
        /*for (Pharmacist ph1: pharmacistDAO.findAll()){
            System.out.println(ph1.getFirstName() + " " + ph1.getLastName());
        }*/
        Pharmacist ph = pharmacistDAO.find(username, password);
        if (ph != null) {
            view.navigateToPharmacistScreen(ph);
            return;
        }
        NOHCS_Employee emp = NOHCSEmployeeDAO.find(username, password);
        if (emp != null) {
            view.navigateToNOHCSScreen(emp);
            return;
        }
        view.showError("User not found.");
    }

}
