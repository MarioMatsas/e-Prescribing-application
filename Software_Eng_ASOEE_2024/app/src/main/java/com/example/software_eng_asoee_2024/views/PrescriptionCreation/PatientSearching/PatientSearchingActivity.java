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

import com.example.software_eng_asoee_2024.views.PrescreptionExecution.Execution.PrescriptionExecutionActivity;

public class PatientSearchingActivity extends AppCompatActivity implements PatientSearchingView{

    private PatientSearchingViewModel viewModel;
    private Button createPrescriptionButton;
    private EditText SSN;
    private TextView errorMessage;
    private ImageView logo;

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
        errorMessage = findViewById(R.id.error_message_patient_search);
        logo = findViewById(R.id.eopyy_image_login);
        createPrescriptionButton.setOnClickListener(v -> navigateToCreation());
    }

    @Override
    protected void onResume() {
        super.onResume();
        SSN.getText().clear();
        errorMessage.setText(""); // Clear error message
    }

    @Override
    public void navigateToCreation(){
        Intent intent = new Intent(this, PrescriptionExecutionActivity.class);
        startActivity(intent);
        }

    @Override
    public void showError(String message) {
        errorMessage.setText(message);
    }
}
