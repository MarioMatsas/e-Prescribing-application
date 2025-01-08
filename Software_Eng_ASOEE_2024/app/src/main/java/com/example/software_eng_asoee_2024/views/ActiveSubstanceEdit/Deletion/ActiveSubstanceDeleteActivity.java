package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Deletion;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.software_eng_asoee_2024.domain.Prescription;

import java.util.List;

public class ActiveSubstanceDeleteActivity extends AppCompatActivity implements ActiveSubstanceDeleteView {

    private ActiveSubstanceDeleteViewModel viewModel;
    private Button deleteActiveSubstanceBtn;
    private Spinner ActiveSubstanceSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.create_active_substance);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.create_active_substance), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(ActiveSubstanceDeleteViewModel.class);
        ActiveSubstanceDeletePresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        ActiveSubstanceSpinner = findViewById(R.id.delete_active_substance_spinner);

        presenter.createActiveSubstanceSpinner();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void createActiveSubstanceSpinner(List<ActiveSubstance> activeSubstances) {
        ArrayAdapter<ActiveSubstance> adapter = new ArrayAdapter<ActiveSubstance>(this, android.R.layout.simple_spinner_item, activeSubstances);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        ActiveSubstanceSpinner.setAdapter(adapter);
    }
}
