package com.example.software_eng_asoee_2024.view.SignUp;

import com.example.software_eng_asoee_2024.dao.Initializer;
import com.example.software_eng_asoee_2024.memorydao.DoctorDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.MemoryInitializer;
import com.example.software_eng_asoee_2024.memorydao.PharmacistDAOMemory;
import com.example.software_eng_asoee_2024.view.Login.LoginViewStub;
import com.example.software_eng_asoee_2024.views.Login.LoginPresenter;
import com.example.software_eng_asoee_2024.views.SignUp.SignUpPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SignUpPresenterTest {

    SignUpViewStub viewStub;
    SignUpPresenter presenter;

    @Before
    public void setup(){
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        viewStub = new SignUpViewStub();
        presenter = new SignUpPresenter();
        presenter.setView(viewStub);
        presenter.setDoctorDAO(new DoctorDAOMemory());
        presenter.setPharmacistDAO(new PharmacistDAOMemory());
    }

    /**
     *
     * Successful doctor account created
     */
    @Test
    public void doctorSignUp(){
        boolean result = presenter.signUp("username1", "password1", "password1", "speciality", "Doctor");
        Assert.assertTrue(result);
        Assert.assertNotNull(new DoctorDAOMemory().find("username1", "password1"));
    }

    /**
     *
     * Successful pharmacist account created
     */
    @Test
    public void pharmacistSignUp(){
        boolean result = presenter.signUp("username2", "password2", "password2","", "Pharmacist");
        Assert.assertTrue(result);
        Assert.assertNotNull(new PharmacistDAOMemory().find("username2", "password2"));
    }

    /**
     *
     * Password and repeatPassword don't match
     */
    @Test
    public void repeatPasswordNoMatch(){
        boolean result = presenter.signUp("username2", "password4", "password5","", "Pharmacist");
        Assert.assertFalse(result);
        Assert.assertEquals(viewStub.getErrorMessage(), "Passwords don't match, try again.");
    }

    /**
     * No speciality selected for the doctor
     *
     */
    @Test
    public void doctorNoSpeciality(){
        boolean result = presenter.signUp("username2", "password4", "password4","", "Doctor");
        Assert.assertFalse(result);
        Assert.assertEquals(viewStub.getErrorMessage(), "You need to enter your speciality.");
    }

    /**
     *
     * User already registered
     */
    @Test
    // TODO EMP
    public void userRegistered(){
        boolean result = presenter.signUp("d", "ch", "ch","fgjf", "Doctor");
        Assert.assertFalse(result);
        result = presenter.signUp("m", "m", "m","fgjf", "Doctor");
        Assert.assertFalse(result);
        result = presenter.signUp("admin", "0000", "0000","fgjf", "Pharmacist");
        Assert.assertFalse(result);
    }
}
