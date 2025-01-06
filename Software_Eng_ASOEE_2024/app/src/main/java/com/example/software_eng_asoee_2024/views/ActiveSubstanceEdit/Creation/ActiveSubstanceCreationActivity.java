package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Creation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Unit;
import com.example.software_eng_asoee_2024.views.Login.MainActivity;

public class ActiveSubstanceCreationActivity extends AppCompatActivity implements ActiveSubstanceCreationView {

    private ActiveSubstanceCreationViewModel viewModel;
    private Button addLineBtn;
    private Button createPrescriptionBtn;
    private EditText conc_qty;
    private EditText instructions;
//    private EditText diagnosis;
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

        viewModel = new ViewModelProvider(this).get(ActiveSubstanceCreationViewModel.class);
        ActiveSubstanceCreationPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        createPrescriptionBtn = findViewById(R.id.create_prescr_btn);
        addLineBtn = findViewById(R.id.add_line_button);
        conc_qty = findViewById(R.id.select_conc_qty);
        instructions = findViewById(R.id.instructions);
//        diagnosis = findViewById(R.id.Diagnosis);
        errorMessage = findViewById(R.id.error_text_cre);
        logo = findViewById(R.id.eopyy_image_login);
        unitSpinner = findViewById(R.id.select_unit_spinner);
        activeSubstanceSpinner = findViewById(R.id.select_ActiveSubstance_spinner);
        formSpinner = findViewById(R.id.select_form_spinner);

        Doctor selectedDoctor = (Doctor) getIntent().getSerializableExtra("doctor");
        Patient selectedPatient = (Patient) getIntent().getSerializableExtra("patient");
        String givenDiagnosis = (String) getIntent().getSerializableExtra("diagnosis");
        presenter.init(selectedDoctor, selectedPatient, givenDiagnosis);
        //updateDisplayInfo(selectedPrescription, 0);

        //defining the behavior of the two buttons
        addLineBtn.setOnClickListener(v -> addPrescriptionLine());
        createPrescriptionBtn.setOnClickListener(v -> finishCreation());

        //initializing the spinners
//        ArrayAdapter<Form> formArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, presenter.initFormSpinner());
//        formArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        formSpinner.setAdapter(formArrayAdapter);
        //defining the behavior of the spinners
        formSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // This triggers even for the first/default selection
                Form selectedItem = (Form) parent.getItemAtPosition(position);
                //Toast.makeText(getApplicationContext(), "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Not triggered unless the Spinner is cleared or reset
            }
        });

//        ArrayAdapter<Unit> unitArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, presenter.initUnitSpinner());
//        unitArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        unitSpinner.setAdapter(unitArrayAdapter);
        unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // This triggers even for the first/default selection
                Unit selectedItem = (Unit) parent.getItemAtPosition(position);
                //Toast.makeText(getApplicationContext(), "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Not triggered unless the Spinner is cleared or reset
            }
        });

//        ArrayAdapter<ActiveSubstance> actSubArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, presenter.initActiveSubstanceSpinner());
//        actSubArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        activeSubstanceSpinner.setAdapter(actSubArrayAdapter);
        activeSubstanceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // This triggers even for the first/default selection
                ActiveSubstance selectedItem = (ActiveSubstance) parent.getItemAtPosition(position);
                //Toast.makeText(getApplicationContext(), "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Not triggered unless the Spinner is cleared or reset
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        unitSpinner.setAdapter(null);
//        formSpinner.setAdapter(null);
//        activeSubstanceSpinner.setAdapter(null);
        //maybe i won't let the information to be lost when the activity pauses

        // Reset error message
        errorMessage.setText(""); // Hides the error message
    }

    @Override
    public void addPrescriptionLine() {
        boolean res = viewModel.getPresenter().
                addPrescriptionLine((ActiveSubstance) activeSubstanceSpinner.getSelectedItem(), (Form) formSpinner.getSelectedItem(),
                        (Unit) unitSpinner.getSelectedItem(), conc_qty.getText().toString(), instructions.getText().toString());
    }

    @Override
    public void finishCreation() {
        //todo
        boolean invalid_prescr = viewModel.getPresenter().registerPrescription();//if empty then it's invalid
        if(invalid_prescr){
            errorMessage.setText("No line is registered yet");
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);//Do???: after creating the prescription we go to the the sign up screen
        startActivity(intent);
    }

    @Override
    public void showError(String message) {
        errorMessage.setText(message);
    }

}
