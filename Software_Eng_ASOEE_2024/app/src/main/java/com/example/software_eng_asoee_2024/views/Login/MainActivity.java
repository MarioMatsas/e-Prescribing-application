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
import com.example.software_eng_asoee_2024.dao.Initializer;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.NOHCS_Employee;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.memorydao.MemoryInitializer;
import com.example.software_eng_asoee_2024.views.NOHCS.NOHCSSelectionActivity;
import com.example.software_eng_asoee_2024.views.PrescreptionExecution.Selection.PrescriptionSelectionActivity;
import com.example.software_eng_asoee_2024.views.PrescriptionCreation.PatientSearching.PatientSearchingActivity;
import com.example.software_eng_asoee_2024.views.Report.ReportActivity;
import com.example.software_eng_asoee_2024.views.SignUp.SignUpActivity;

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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        LoginPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        loginButton = findViewById(R.id.move_to_execution);
        signupButton = findViewById(R.id.sign_up);
        username = findViewById(R.id.username_login);
        password = findViewById(R.id.passsword_login);
        createAccount = findViewById(R.id.create_acc_txt);
        errorMessage = findViewById(R.id.error_text);
        logo = findViewById(R.id.eopyy_image_login);

        loginButton.setOnClickListener(v -> login());

        signupButton.setOnClickListener(v -> navigateToSignUpScreen());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Reset username and password fields
        username.setText("");
        password.setText("");

        // Reset error message
        errorMessage.setText(""); // Hides the error message
    }

    /**
     *
     * Method used to login as a doctor, pharmacist, employee or admin
     */
    @Override
    public void login() {
        viewModel.getPresenter().login(username.getText().toString(), password.getText().toString());
    }

    /**
     *
     *  Navigates the admin to the report screen where he can view this month's unlawful doctors
     */
    @Override
    public void navigateToReportScreen(){
        Intent intent = new Intent(this, ReportActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates to doctor main screen
     * @param doctor
     */
    @Override
    public void navigateToDoctorScreen(Doctor doctor) {
        Intent intent = new Intent(this, PatientSearchingActivity.class);
        intent.putExtra("doctorName", doctor.getFirstName());
        intent.putExtra("doctorSurname", doctor.getLastName());
        startActivity(intent);
    }

    /**
     *
     * Navigates to pharmacist main screen
     * @param pharmacist
     */
    @Override
    public void navigateToPharmacistScreen(Pharmacist pharmacist) {
        Intent intent = new Intent(this, PrescriptionSelectionActivity.class);
        intent.putExtra("pharmacistName", pharmacist.getFirstName());
        intent.putExtra("pharmacistSurname", pharmacist.getLastName());
        //intent.putExtra("pharmacist", pharmacist);
        startActivity(intent);
    }

    /**
     * Navigates to employee main screen
     * @param employee
     */
    @Override
    public void navigateToNOHCSScreen(NOHCS_Employee employee) {
        Intent intent = new Intent(this, NOHCSSelectionActivity.class);
        intent.putExtra("employee", employee);
        startActivity(intent);
    }

    /**
     *
     * Navigates to sign up page
     */
    @Override
    public void navigateToSignUpScreen() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    /**
     * Updates the sreens error message
     * @param message
     */
    @Override
    public void showError(String message) {
        errorMessage.setText(message);
    }
}