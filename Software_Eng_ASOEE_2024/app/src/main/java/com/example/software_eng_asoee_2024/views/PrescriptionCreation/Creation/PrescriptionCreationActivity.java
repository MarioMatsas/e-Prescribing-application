package com.example.software_eng_asoee_2024.views.PrescriptionCreation.Creation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.Unit;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionCreationActivity extends AppCompatActivity implements PrescriptionCreationView {

    private PrescriptionCreationViewModel viewModel;
    private Button addLineBtn;
    private Button createPrescriptionBtn;
    private EditText concentrationAmount;
    private EditText concentrationUnit;
    private EditText instructions;
    private EditText amountPerDay;
    private EditText numberOfDay;
    private TextView errorMessage;
    private ImageView logo;
    private Spinner unitSpinner;
    private Spinner formSpinner;
    private Spinner activeSubstanceSpinner;
    private Patient patient;
    private Doctor doctor;

    /**
     * Δημιουργεί to layout και αρχικοποιεί
     * το activity.
     * @param savedInstanceState το Instance state
     */
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
        concentrationAmount = findViewById(R.id.select_conc_qty);
        concentrationUnit = findViewById(R.id.concentration_unit);
        instructions = findViewById(R.id.extra_instructions);
        numberOfDay = findViewById(R.id.number_of_days);
        amountPerDay = findViewById(R.id.amount_per_day);
        errorMessage = findViewById(R.id.error_text_cre);
        logo = findViewById(R.id.eopyy_image_login);
        activeSubstanceSpinner = findViewById(R.id.select_ActiveSubstance_spinner);
        formSpinner = findViewById(R.id.select_form_spinner);

        createPrescriptionBtn.setOnClickListener(v -> createPrescription());

        addLineBtn.setOnClickListener(v -> addLine());

        Intent intent = getIntent();
        String doctorName = intent.getStringExtra("doctorName");
        String doctorSurname = intent.getStringExtra("doctorSurname");
        Integer patientSSN = intent.getIntExtra("patientSSN", 0);
        String diagnosis = intent.getStringExtra("diagnosis");

        viewModel.getPresenter().init(doctorName, doctorSurname, patientSSN, diagnosis);
        //updateDisplayInfo(selectedPrescription, 0);
        populateActiveSubSpinner();
        setupFormSpinner();
    }

    /**
     * Δημιουργεί και γεμίζει το spinner για τις δραστικές ουσίες.
     */
    @Override
    public void populateActiveSubSpinner() {
        // Fetch active substances from the presenter
        List<ActiveSubstance> activeSubs = viewModel.getPresenter().getActiveSubs();

        // Set up the adapter to work directly with ActiveSubstance objects
        ArrayAdapter<ActiveSubstance> activeSubAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, activeSubs);
        activeSubAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activeSubstanceSpinner.setAdapter(activeSubAdapter);
    }

    /**
     * Αρχικοποιεί το spinner της οθόνης, για το Form.
     * Κάθε Form έχει δική του μονάδα μέτρησης (Unit) και
     * για αυτό υπάρχει switch για να κάνει έλεγχο κάθε φορά.
     */
    @Override
    public void setupFormSpinner() {
        // Get all enum values as an array of Form
        Form[] formOptions = Form.values();

        // Set up the adapter with the enum values themselves
        ArrayAdapter<Form> formAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, formOptions);
        formAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        formSpinner.setAdapter(formAdapter);

        // Add listener for item selection
        formSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                viewModel.getPresenter().onFormSelected(formOptions[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    /**
     * Θέτει κατάλληλα τα hints για τον χρήστη.
     * @param amount Το περιεχόμενο του μηνύματος
     * @param days Ο αριθμός των ημερών, που θα φαίνεται στο μήνυμα
     */
    @Override
    public void updateText(String amount, String days, String unit){
        amountPerDay.setText("");
        amountPerDay.setHint(amount);
        numberOfDay.setText("");
        numberOfDay.setHint(days);
        concentrationUnit.setText(unit);
    }

    /**
     * Κοιτά αν μπορεί να δημιουργηθεί η συνταγή(μέσω του presenter)
     * και αν μπορεί, τερματίζει το activity.
     */
    @Override
    public void createPrescription(){
        if (viewModel.getPresenter().createPrescription()){
            finish();
        }
    }

    /**
     * Καλεί τον presenter, μέσω του viewModel, για να προσθέσει την γραμμή στην συνταγή.
     */
    @Override
    public void addLine(){
        viewModel.getPresenter().addPrescriptionline((ActiveSubstance) activeSubstanceSpinner.getSelectedItem(),
                (Form) formSpinner.getSelectedItem(), concentrationAmount.getText().toString(), concentrationUnit.getText().toString(),
                amountPerDay.getText().toString(), numberOfDay.getText().toString(), instructions.getText().toString());
    }

    /**
     * Καθαρίζει τα πεδία συμπλήρωσης, για να μήν μπερδευτεί ο χρήστης.
     */
    @Override
    public void clearFields(){
        concentrationAmount.setText("");
        amountPerDay.setText("");
        numberOfDay.setText("");
        instructions.setText("");
        showError("");
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
