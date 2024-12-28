package com.example.software_eng_asoee_2024.views.PrescreptionExecution.Execution;

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
import com.example.software_eng_asoee_2024.domain.PharmacudicalProduct;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.PrescriptionLine;
import com.example.software_eng_asoee_2024.views.PrescreptionExecution.Selection.PrescriptionSelectionPresenter;
import com.example.software_eng_asoee_2024.views.PrescreptionExecution.Selection.PrescriptionSelectionView;
import com.example.software_eng_asoee_2024.views.PrescreptionExecution.Selection.PrescriptionSelectionViewModel;

import java.util.List;

public class PrescriptionExecutionActivity extends AppCompatActivity implements PrescriptionExecutionView {
    private PrescriptionExecutionViewModel viewModel;
    private Button advanceExecutionButton;
    private EditText quantity;
    private TextView showPrescriptionLineInstructions;
    private TextView errorMessage;
    private ImageView logo;
    private Spinner productsSpinner;
    //private List<Prescription> currentPrescriptions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.execute_prescription);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.execute_prescription), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(PrescriptionExecutionViewModel.class);
        PrescriptionExecutionPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        advanceExecutionButton = findViewById(R.id.advance_execution_button);
        quantity = findViewById(R.id.product_quantity);
        showPrescriptionLineInstructions = findViewById(R.id.execution_instructions);
        errorMessage = findViewById(R.id.error_text_exe);
        logo = findViewById(R.id.eopyy_image_login);
        productsSpinner = findViewById(R.id.product_spinner);

        Intent intent = getIntent();
        Prescription selectedPrescription = viewModel.getPresenter().getPrescription(((Integer) intent.getSerializableExtra("selectedPrescriptionId")));
        Pharmacist pharmacist = (Pharmacist) getIntent().getSerializableExtra("pharmacist");
        presenter.init(selectedPrescription, pharmacist);
        updateDisplayInfo(selectedPrescription, 0);
    }
    @Override
    public void updateDisplayInfo(Prescription prescription, int currentIndex) {
        List<PrescriptionLine> lines = prescription.getPrescriptionLines();
        PrescriptionLine line = lines.get(currentIndex); // It will have at least one line
        updateLineInfo(line);
        viewModel.getPresenter().showPrescriptionLineProducts(line);
        if (currentIndex == lines.size()-1) {
            advanceExecutionButton.setText("Finish");
            advanceExecutionButton.setOnClickListener(v -> finishExecution(prescription));
        }
        else {
            advanceExecutionButton.setText("Next Line");
            advanceExecutionButton.setOnClickListener(v -> nextLine(prescription, currentIndex));
        }
    }
    @Override
    public void updateLineInfo(PrescriptionLine line) {
        showPrescriptionLineInstructions.setText("Instructions: "+line.getInstructions());
    }
    @Override
    public void nextLine(Prescription prescription, int index){
        if (addProduct(prescription, index, false)) { // Add product and proceed only if successful
            quantity.getText().clear();
            productsSpinner.setAdapter(null);
            errorMessage.setText("");
            updateDisplayInfo(prescription, index+1); // Update the display for the next line
        }
    }
    @Override
    public boolean addProduct(Prescription prescription, int index, boolean finalLine){
        return viewModel.getPresenter().addProductToBuy((PharmacudicalProduct) productsSpinner.getSelectedItem(), quantity.getText().toString());
    }

    @Override
    public void finishExecution(Prescription prescription){
        boolean finished = addProduct(prescription, 0, true);
        if (finished) {
            viewModel.getPresenter().finishExecution(prescription);
            finish();
        }
    }

    @Override
    public void updateProductSpinner(List<PharmacudicalProduct> products) {
        //currentPrescriptions = prescriptions;
        ArrayAdapter<PharmacudicalProduct> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, products);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productsSpinner.setAdapter(adapter);

        //errorMessage.setText(""); // Clear previous errors
    }

    @Override
    public void clearProductSpinner() {
        productsSpinner.setAdapter(null);
    }

    @Override
    public void showError(String message) {
        errorMessage.setText(message);
    }
}
