package com.example.software_eng_asoee_2024.views.SignUp;

import com.example.software_eng_asoee_2024.dao.NOHCSEmployeeDAO;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.dao.DoctorDAO;
import com.example.software_eng_asoee_2024.dao.PharmacistDAO;

public class SignUpPresenter {
    private SignUpView view;
    private DoctorDAO doctorDAO;
    private PharmacistDAO pharmacistDAO;
    private NOHCSEmployeeDAO empDAO;

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

    public void setEmpDAO(NOHCSEmployeeDAO empDAO){
        this.empDAO = empDAO;
    }

    /**
     * Checks if the account is available. If so the user account is
     * created as either a doctor or a pharmacist
     *
     * @param username
     * @param password
     * @param repeatPassword
     * @param speciality
     * @param user
     * @return
     */
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

    /**
     * Checks if an account with such credentials already exists
     *
     * @param username
     * @param password
     * @return
     */
    public boolean userExists(String username, String password){
        System.out.println(username + " " + password);
        // Check if the user with those credentials already exists
        if (doctorDAO.find(username, password) != null) return true;
        if (pharmacistDAO.find(username, password) != null) return true;
        if (empDAO.find(username, password) != null) return true;
        if (username.equals("admin") && password.equals("0000")) return true;
        return false;
    }

}
