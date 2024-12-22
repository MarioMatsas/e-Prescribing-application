package com.example.software_eng_asoee_2024.views.SignUp;

import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.dao.DoctorDAO;
import com.example.software_eng_asoee_2024.dao.PharmacistDAO;

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

    public void signUp(String username, String password, String repeatPassword, String speciality, String user){
        // Password miss match check
        if (!(password.equals(repeatPassword))) {
            view.showError("Passwords don't match, try again.");
            return;
        }

        if (user.equals("Doctor")){
            // Lack of speciality check
            if (speciality.isEmpty()){
                view.showError("You need to enter your speciality.");
                return;
            }
            doctorDAO.save(new Doctor(username, password, speciality));
        }
        else{
            pharmacistDAO.save(new Pharmacist(username, password));
        }
    }
}
