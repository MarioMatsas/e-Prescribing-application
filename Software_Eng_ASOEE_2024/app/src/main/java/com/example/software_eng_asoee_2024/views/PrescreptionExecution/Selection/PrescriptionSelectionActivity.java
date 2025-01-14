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
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.views.PrescreptionExecution.Execution.PrescriptionExecutionActivity;

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
    private String pharmacist_fn;
    private String pharmacist_ln;
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
        pharmacist_fn = getIntent().getStringExtra("pharmacistName");
        pharmacist_ln = getIntent().getStringExtra("pharmacistSurname");
    }

    @Override
    protected void onResume() {
        super.onResume();
        SSN.getText().clear();
        prescription.setAdapter(null); // Clear spinner
        errorMessage.setText(""); // Clear error message
    }

    /**
     * Sends the user to the prescription execution screen
     *
     */
    @Override
    public void navigateToExecution(){
        boolean res = viewModel.getPresenter().navigateToExecution((Prescription)prescription.getSelectedItem());
        if (res){
            Prescription selectedPrescription = (Prescription) prescription.getSelectedItem();
            Intent intent = new Intent(this, PrescriptionExecutionActivity.class);
            intent.putExtra("selectedPrescriptionId", selectedPrescription.getId()); // Pass the chosen prescription to the execution
            intent.putExtra("pharmacistName", pharmacist_fn);
            intent.putExtra("pharmacistSurname", pharmacist_ln);
            startActivity(intent);
        }
    }

    /**
     * Makes a call to the presenter so he can update the prescription spinner
     *
     * @param presenter
     */
    @Override
    public void showPatientPrescriptions(PrescriptionSelectionPresenter presenter){
        presenter.showPatientPrescriptions(SSN.getText().toString());
    }

    /**
     * Updates the product spinner, with the prescriptions available to
     * that patient
     *
     * @param prescriptions
     */
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
