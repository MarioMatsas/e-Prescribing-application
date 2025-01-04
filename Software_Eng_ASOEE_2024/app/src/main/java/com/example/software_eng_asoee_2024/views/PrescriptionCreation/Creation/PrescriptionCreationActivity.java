package com.example.software_eng_asoee_2024.views.PrescriptionCreation.Creation;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.views.PrescreptionExecution.Execution.PrescriptionExecutionPresenter;
import com.example.software_eng_asoee_2024.views.PrescreptionExecution.Execution.PrescriptionExecutionViewModel;
import com.example.software_eng_asoee_2024.views.PrescriptionCreation.Creation.PrescriptionCreationViewModel;
import com.example.software_eng_asoee_2024.views.PrescriptionCreation.Creation.PrescriptionCreationView;

public class PrescriptionCreationActivity extends AppCompatActivity implements PrescriptionCreationView {

    private PrescriptionCreationViewModel viewModel;
    private Button addLineBtn;
    private Button createPrescriptionBtn;
    private EditText conc_qty;
    private EditText instructions;
    private EditText diagnosis;
    private TextView errorMessage;
    private ImageView logo;
    private Spinner unitSpinner;
    private Spinner formSpinner;
    private Spinner activeSubstanceSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.create_prescription);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.creating_prescription), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(PrescriptionCreationViewModel.class);
        PrescriptionCreationPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        createPrescriptionBtn = findViewById(R.id.create_prescr_btn);
        addLineBtn = findViewById(R.id.add_line_button);
        conc_qty = findViewById(R.id.select_conc_qty);
        instructions = findViewById(R.id.instructions);
        diagnosis = findViewById(R.id.Diagnosis);
        errorMessage = findViewById(R.id.error_text_cre);
        logo = findViewById(R.id.eopyy_image_login);
        unitSpinner = findViewById(R.id.select_unit_spinner);
        activeSubstanceSpinner = findViewById(R.id.select_ActiveSubstance_spinner);
        formSpinner = findViewById(R.id.select_form_spinner);

        Intent intent = getIntent();
        Prescription selectedPrescription = viewModel.getPresenter().getPrescription(((Integer) intent.getSerializableExtra("selectedPrescriptionId")));
        Doctor doctor = (Doctor) getIntent().getSerializableExtra("doctor");
        presenter.init(selectedPrescription, doctor);
        //updateDisplayInfo(selectedPrescription, 0);
    }

}
