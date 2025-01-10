package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Creation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.software_eng_asoee_2024.R;
import com.example.software_eng_asoee_2024.domain.ActiveSubstance;

public class ActiveSubstanceCreationActivity extends AppCompatActivity implements ActiveSubstanceCreationView {

    private ActiveSubstanceCreationViewModel viewModel;
    private Button addActiveSubstanceBtn;
    private EditText ActiveSubstanceName;
    private EditText ExpectedQuantityPerMonth;
    private TextView out;

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

        viewModel = new ViewModelProvider(this).get(ActiveSubstanceCreationViewModel.class);
        ActiveSubstanceCreationPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        addActiveSubstanceBtn = findViewById(R.id.create_active_substance_btn);
        ActiveSubstanceName = findViewById(R.id.select_active_substance_name);
        ExpectedQuantityPerMonth = findViewById(R.id.select_active_substance_eqpm);
        out = findViewById(R.id.error_text_cas);


        //defining the behavior of the buttons
        addActiveSubstanceBtn.setOnClickListener(v -> addActiveSubstance());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void addActiveSubstance() {
        try {
            if (ActiveSubstanceName.getText().toString().isEmpty() || ExpectedQuantityPerMonth.getText().toString().isEmpty())
                throw new IllegalArgumentException("Not all fields are filled in");
            viewModel.getPresenter().createActiveSubstance(new ActiveSubstance(ActiveSubstanceName.getText().toString(), Double.parseDouble(ExpectedQuantityPerMonth.getText().toString())));
            out.setText("Done!");
        } catch (NumberFormatException e) {
            out.setText("Expected Quantity Per Month should be a number");
        } catch (Exception e) {
            out.setText(e.getMessage());

        }
    }
}
