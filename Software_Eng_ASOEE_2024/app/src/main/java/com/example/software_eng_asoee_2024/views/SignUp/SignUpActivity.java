package com.example.software_eng_asoee_2024.views.SignUp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.software_eng_asoee_2024.R;
import com.example.software_eng_asoee_2024.views.Login.LoginPresenter;
import com.example.software_eng_asoee_2024.views.Login.LoginViewModel;


public class SignUpActivity extends AppCompatActivity implements SignUpView {
    private SignUpViewModel viewModel;
    private Button signupButton;
    private EditText username;
    private EditText password;
    private EditText repeatPassword;
    private EditText speciality;
    private TextView createAccount;
    private TextView errorMessage;
    private ImageView logo;
    private Spinner role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sign_up_activity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sign_up_activity_id), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        SignUpPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        signupButton = findViewById(R.id.sign_up_buttton_2);
        username = findViewById(R.id.username_sign_up);
        password = findViewById(R.id.password_su);
        repeatPassword = findViewById(R.id.repeat_password_su);
        speciality = findViewById(R.id.speciality);
        createAccount = findViewById(R.id.create_account_sign_up);
        errorMessage = findViewById(R.id.error_message_su);
        logo = findViewById(R.id.eopyy_image_login);
        /*
        *  Set up the spinner
        * */
        role = findViewById(R.id.role_selection);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(adapter);

        signupButton.setOnClickListener(v -> {
            viewModel.getPresenter().signUp(username.getText().toString(), password.getText().toString(),
                    repeatPassword.getText().toString(),speciality.getText().toString() ,role.getSelectedItem().toString());
        });
    }

    @Override
    public void showError(String message) {
        errorMessage.setText(message);
    }
}
