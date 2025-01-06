package com.example.software_eng_asoee_2024.views.SignUp;

import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.dao.DoctorDAO;
import com.example.software_eng_asoee_2024.dao.PharmacistDAO;
/**
 *
 *  A class used to handle data, via the use
 *  of domain classes and DAO's, which is used
 *  by the SignUpActivity in order to update the screen
 */
public class SignUpPresenter {
    private SignUpView view;
    private DoctorDAO doctorDAO;
    private PharmacistDAO pharmacistDAO;

    public SignUpView getView() {
        return view;
    }

    public void setView(SignUpView view) {
        this.view = view;
    }

    public void setDoctorDAO(DoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    public void setPharmacistDAO(PharmacistDAO pharmacistDAO) {
        this.pharmacistDAO = pharmacistDAO;
    }

    public boolean signUp(String username, String password, String repeatPassword, String speciality, String user){
        // Check if every field is filled
        if (username.isEmpty() || password.isEmpty()){
            view.showError("Username and/or password fields can't be empty");
            return false;
        }
        // Password miss match check
        if (!(password.equals(repeatPassword))) {
            view.showError("Passwords don't match, try again.");
            return false;
        }

        if (userExists(username, password)){
            view.showError("Chosen credentials already in use.");
            return false;
        }

        if (user.equals("Doctor")){
            // Lack of speciality check
            if (speciality.isEmpty()){
                view.showError("You need to enter your speciality.");
                return false;
            }
            doctorDAO.save(new Doctor(username, password, speciality));
        }
        else{
            pharmacistDAO.save(new Pharmacist(username, password));
        }
        return true;
    }

    public boolean userExists(String username, String password){
        System.out.println(username + " " + password);
        // Check if the user with those credentials already exists
        if (doctorDAO.find(username, password) != null) return true;
        if (pharmacistDAO.find(username, password) != null) return true;
        return false;
    }

}
