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

    /**
     * Adds a new active substance to the system.
     * Extracts data from the UI and calls the presenter function to create the active substance.
     */
    @Override
    public void addActiveSubstance() {
        viewModel.getPresenter().createActiveSubstance(ActiveSubstanceName.getText().toString(), ExpectedQuantityPerMonth.getText().toString());
    }

    /**
     * Displays a message to the user
     *
     * @param s the message to be displayed
     */
    @Override
    public void showMessage(String s) {
        out.setText(s);
    }
}
