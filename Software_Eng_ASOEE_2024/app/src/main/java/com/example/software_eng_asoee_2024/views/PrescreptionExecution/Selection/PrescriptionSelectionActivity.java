package com.example.software_eng_asoee_2024.views.PrescreptionExecution.Selection;

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
import com.example.software_eng_asoee_2024.domain.Prescription;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionSelectionActivity extends AppCompatActivity implements PrescriptionSelectionView {
    private PrescriptionSelectionViewModel viewModel;
    private Button showPrescriptionsButton;
    private Button executePrescriptionButton;
    private EditText SSN;
    private TextView selectPrescription;
    private TextView errorMessage;
    private ImageView logo;
    private Spinner prescription;
    //private List<Prescription> currentPrescriptions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.select_prescription);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.selection_prescription), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(PrescriptionSelectionViewModel.class);
        PrescriptionSelectionPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        showPrescriptionsButton = findViewById(R.id.show_prescriptions);
        executePrescriptionButton = findViewById(R.id.move_to_execution);
        SSN = findViewById(R.id.select_patient);
        selectPrescription = findViewById(R.id.select_prescription_txt);
        errorMessage = findViewById(R.id.error_massage_presc_sel);
        logo = findViewById(R.id.eopyy_image_login);
        prescription = findViewById(R.id.select_prescription_spinner);

        showPrescriptionsButton.setOnClickListener(v -> showPatientPrescriptions(presenter));

        executePrescriptionButton.setOnClickListener(v -> navigateToExecution());
    }
    @Override
    public void navigateToExecution(){
        /*if (!currentPrescriptions.isEmpty()) {
                Prescription selectedPrescription = (Prescription) prescription.getSelectedItem();
                Intent intent = new Intent(this, PrescriptionExecutionActivity.class);
                intent.putExtra(this, selectedPrescription);
                startActivity(intent);
            }
            else {
                showError("No prescriptions found for this patient.");
            }*/
    }
    @Override
    public void showPatientPrescriptions(PrescriptionSelectionPresenter presenter){
        if (SSN.getText().toString().isEmpty()) {  // Check if the SSN field is empty
            showError("SSN cannot be empty.");
            clearPrescriptionSpinner();
            return;
        }
        try {
            int ssn = Integer.parseInt(SSN.getText().toString());  // Try to parse the SSN
            presenter.showPatientPrescriptions(ssn);  // Proceed with showing prescriptions
        } catch (NumberFormatException e) {
            showError("Invalid SSN format.");
            clearPrescriptionSpinner();
        }
    }

    @Override
    public void updatePrescriptionSpinner(List<Prescription> prescriptions) {
        //currentPrescriptions = prescriptions;
        ArrayAdapter<Prescription> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, prescriptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prescription.setAdapter(adapter);

        //errorMessage.setText(""); // Clear previous errors
    }

    @Override
    public void clearPrescriptionSpinner() {
        // Clear the spinner when there's no valid patient
        prescription.setAdapter(null);
    }

    @Override
    public void showError(String message) {
        errorMessage.setText(message);
    }
}
