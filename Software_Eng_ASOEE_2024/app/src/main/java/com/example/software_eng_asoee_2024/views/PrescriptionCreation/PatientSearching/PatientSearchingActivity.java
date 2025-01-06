package com.example.software_eng_asoee_2024.views.PrescriptionCreation.PatientSearching;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.software_eng_asoee_2024.R;

import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.views.PrescreptionExecution.Execution.PrescriptionExecutionActivity;
import com.example.software_eng_asoee_2024.views.PrescriptionCreation.Creation.PrescriptionCreationActivity;

public class PatientSearchingActivity extends AppCompatActivity implements PatientSearchingView{

    private PatientSearchingViewModel viewModel;
    private Button createPrescriptionButton;
    private EditText SSN;
    private EditText diagnosis;
    private TextView errorMessage;
    private ImageView logo;
    private Doctor doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.search_patient);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.searching_patient), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(PatientSearchingViewModel.class);
        PatientSearchingPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        doctor = (Doctor) getIntent().getSerializableExtra("doctor");

        createPrescriptionButton = findViewById(R.id.create_prescr_btn);
        SSN = findViewById(R.id.find_patient);
        diagnosis = findViewById(R.id.Diagnosis);
        errorMessage = findViewById(R.id.error_message_patient_search);
        logo = findViewById(R.id.eopyy_image_login);

        createPrescriptionButton.setOnClickListener(v -> navigateToCreation());
    }

    @Override
    protected void onResume() {
        super.onResume();
        SSN.setText("");
        errorMessage.setText(""); // Clear error message
    }

//    @Override
//    public void patient_login() {
//        viewModel.getPresenter().patient_login(SSN.getText().toString());
//    }

//    @Override
//    public void navigateToCreationScreen(Patient patient){//this is called in presenter, so the control is in presenter, we dont want that
//        Intent intent = new Intent(this, PrescriptionCreationActivity.class);
//        intent.putExtra("patient", patient);
//        intent.putExtra("doctor", doctor); //also passing the doctor because the prescription needs the information of doctor
//        startActivity(intent);
//    }

    public void navigateToCreation(){//with this, the presenter just checks if everything is okay, the control is in activity
        if(diagnosis.getText().toString().isEmpty()){
//                showError("Give Diagnosis First");
            errorMessage.setText("Give Diagnosis First");
            return;
        }

        Patient res = viewModel.getPresenter().check_patient_login(SSN.getText().toString());
//        String diag = (String) ((CharSequence) diagnosis);
        if (res != null){
            Intent intent = new Intent(this, PrescriptionCreationActivity.class);
            intent.putExtra("patient", res);
            intent.putExtra("doctor", doctor);
            intent.putExtra("diagnosis", diagnosis.getText().toString());
            startActivity(intent);
        }
    }

    @Override
    public void showError(String message) {
        errorMessage.setText(message);
    }
}
