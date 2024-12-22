package com.example.software_eng_asoee_2024.views.Login;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.software_eng_asoee_2024.R;

public class MainActivity extends AppCompatActivity implements LoginView{
    private LoginViewModel viewModel;
    private Button loginButton;
    private Button signupButton;
    private EditText username;
    private EditText password;
    private TextView createAccount;
    private TextView errorMessage;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        LoginPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        loginButton = findViewById(R.id.login_button);
        signupButton = findViewById(R.id.sign_up);
        username = findViewById(R.id.username_login);
        password = findViewById(R.id.passsword_login);
        createAccount = findViewById(R.id.create_acc_txt);
        errorMessage = findViewById(R.id.error_text);
        logo = findViewById(R.id.eopyy_image_login);

        loginButton.setOnClickListener(v -> {
            viewModel.getPresenter().login(username.getText().toString(), password.getText().toString());
        });

        signupButton.setOnClickListener(v -> navigateToSignUpScreen());
    }

    @Override
    public void showError(String message) {
        errorMessage.setText(message);
    }

    @Override
    public void navigateToDoctorScreen() {
        /*Intent intent = new Intent(this, DoctorActivity.class);
        startActivity(intent);
        finish();*/
    }

    @Override
    public void navigateToPharmacistScreen() {
        /*Intent intent = new Intent(this, PharmacistActivity.class);
        startActivity(intent);
        finish();*/
    }

    @Override
    public void navigateToSignUpScreen() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}