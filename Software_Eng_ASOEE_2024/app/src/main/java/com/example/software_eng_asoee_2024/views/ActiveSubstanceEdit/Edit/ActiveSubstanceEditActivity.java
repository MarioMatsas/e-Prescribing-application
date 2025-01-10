package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Edit;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Edit.ActiveSubstanceEditPresenter;
import com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Edit.ActiveSubstanceEditView;
import com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Edit.ActiveSubstanceEditViewModel;

import java.util.List;

public class ActiveSubstanceEditActivity extends AppCompatActivity implements ActiveSubstanceEditView {

    private ActiveSubstanceEditViewModel viewModel;
    private Button editActiveSubstanceBtn;
    private Spinner activeSubstanceSpinner;
    private EditText name;
    private EditText eqpm;
    private TextView out;
    private ActiveSubstance selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.edit_active_substance);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.edit_active_substance), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(ActiveSubstanceEditViewModel.class);
        ActiveSubstanceEditPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        activeSubstanceSpinner = findViewById(R.id.edit_active_substance_spinner);
        editActiveSubstanceBtn = findViewById(R.id.edit_active_substance_btn);
        name = findViewById(R.id.edit_active_substance_name);
        eqpm = findViewById(R.id.edit_active_substance_eqpm);
        out = findViewById(R.id.edit_active_substance_output_txt);


        presenter.createActiveSubstanceSpinner();

        editActiveSubstanceBtn.setOnClickListener(v -> editActiveSubstance());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void createActiveSubstanceSpinner(List<ActiveSubstance> activeSubstances) {
        if(activeSubstances.isEmpty()) {
            activeSubstanceSpinner.setAdapter(null);
            return;
        }
        ArrayAdapter<ActiveSubstance> adapter = new ArrayAdapter<ActiveSubstance>(this, android.R.layout.simple_spinner_item, activeSubstances);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activeSubstanceSpinner.setAdapter(adapter);
        selected = activeSubstances.get(0);

        activeSubstanceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                name.setText(activeSubstances.get(position).getName());
                eqpm.setText(activeSubstances.get(position).getExpectedQuantityPerMonth().toString());
                name.setEnabled(true);
                eqpm.setEnabled(true);
                selected = activeSubstances.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                name.setEnabled(false);
                eqpm.setEnabled(false);
            }
        });
    }

    public void editActiveSubstance() {
        if(selected == null) return;
        try {
            if (name.getText().toString().isEmpty() || eqpm.getText().toString().isEmpty())
                throw new IllegalArgumentException("Not all fields are filled in");
            viewModel.getPresenter().editActiveSubstance(selected, new ActiveSubstance(name.getText().toString(), Double.parseDouble(eqpm.getText().toString())));
            showMessage("Done!");
        } catch (NumberFormatException e) {
            showMessage("Expected Quantity Per Month should be a number");
        } catch (Exception e) {
            showMessage(e.getMessage());

        }
    }

    @Override
    public void showMessage(String s) {
        out.setText(s);
    }
}