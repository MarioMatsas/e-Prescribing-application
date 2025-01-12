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
import com.example.software_eng_asoee_2024.domain.Pharmacist;
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
    String doctorName;
    String doctorSurname;

    /**
     * Δημιουργεί to layout και αρχικοποιεί
     * το activity.
     * @param savedInstanceState το Instance state
     */
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

        createPrescriptionButton = findViewById(R.id.create_prescr_btn);
        SSN = findViewById(R.id.find_patient);
        diagnosis = findViewById(R.id.Diagnosis);
        errorMessage = findViewById(R.id.error_message_patient_search);
        logo = findViewById(R.id.eopyy_image_login);
        createPrescriptionButton.setOnClickListener(v -> prepareForCreate());

        Intent intent = getIntent();
        doctorName = intent.getStringExtra("doctorName");
        doctorSurname = intent.getStringExtra("doctorSurname");
    }

    @Override
    protected void onResume() {
        super.onResume();
        SSN.setText("");
        diagnosis.setText("");
        errorMessage.setText(""); // Clear error message
    }

    /**
     * Καλεί τον presenter, μέσω του viewModel, να ελέγξει και να επιβεβαιώσει
     * την δημιουργία του επόμενου activity(PrescriptionCreation).
     */
    public void prepareForCreate(){
        viewModel.getPresenter().findPatient(SSN.getText().toString(), diagnosis.getText().toString());
    }

    /**
     * Δημιουργεί το επόμενο activity και το εκκινεί.
     * @param patientSSN ΑΜΚΑ ασθενή
     */
    @Override
    public void navigateToCreation(Integer patientSSN){
        Intent intent = new Intent(this, PrescriptionCreationActivity.class);
        // Pass the patient ssn, and doctor credentials in order to use the same objects from the dao's
        intent.putExtra("doctorName", doctorName);
        intent.putExtra("doctorSurname", doctorSurname);
        intent.putExtra("patientSSN", patientSSN);
        intent.putExtra("diagnosis", diagnosis.getText().toString());
        startActivity(intent);
    }

    /**
     * Ορίζει στο errorMessage μήνυμα,
     * το περιεχόμενο του message.
     * @param message Το περιεχόμενο του μηνύματος
     */
    @Override
    public void showError(String message) {
        errorMessage.setText(message);
    }
}
