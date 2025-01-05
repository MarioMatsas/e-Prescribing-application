package com.example.software_eng_asoee_2024.view.Login;

import com.example.software_eng_asoee_2024.dao.Initializer;
import com.example.software_eng_asoee_2024.memorydao.DoctorDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.MemoryInitializer;
import com.example.software_eng_asoee_2024.memorydao.PharmacistDAOMemory;
import com.example.software_eng_asoee_2024.views.Login.LoginPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginPresenterTest {
    LoginViewStub viewStub;
    LoginPresenter presenter;

    @Before
    public void setup(){
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        viewStub = new LoginViewStub();
        presenter = new LoginPresenter();
        presenter.setView(viewStub);
        presenter.setDoctorDAO(new DoctorDAOMemory());
        presenter.setPharmacistDAO(new PharmacistDAOMemory());
    }

    @Test
    public void doctorLogin(){
        presenter.login("m", "m");
        Assert.assertEquals("Success doctor", viewStub.getNavDocMessage());
    }

    @Test
    public void pharmacistLogin(){
        presenter.login("d", "ch");
        Assert.assertEquals("Success pharmacist", viewStub.getNavPhMessage());
    }

    @Test
    public void failLogin(){
        presenter.login("ch", "p");
        Assert.assertEquals("User not found.", viewStub.getErrorMessage());
    }

}
