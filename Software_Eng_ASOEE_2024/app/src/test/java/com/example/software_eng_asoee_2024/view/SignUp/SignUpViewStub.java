package com.example.software_eng_asoee_2024.view.SignUp;

import com.example.software_eng_asoee_2024.views.SignUp.SignUpView;

public class SignUpViewStub implements SignUpView {
    private String signUpMessage;
    private String errorMessage;
    public void signUp(){

    }


    public void showError(String message){
        errorMessage = message;
    }

    public String getsSignUpMessage() {
        return signUpMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
