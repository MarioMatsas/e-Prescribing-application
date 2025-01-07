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
    private Button addActiveSubstanceBtn;
    private EditText ActiveSubstanceName;
    private EditText ExpectedQuantityPerMonth;
    private TextView errorMessage;
    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.create_active_substance);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.creating_prescription), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });  TODO: WTF is this ^^^^ ?

        viewModel = new ViewModelProvider(this).get(ActiveSubstanceCreationViewModel.class);
        ActiveSubstanceCreationPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        addActiveSubstanceBtn = findViewById(R.id.create_active_substance_btn);
        ActiveSubstanceName = findViewById(R.id.select_active_substance_name);
        ExpectedQuantityPerMonth = findViewById(R.id.select_active_substance_eqpm);
        errorMessage = findViewById(R.id.error_text_cas);
        logo = findViewById(R.id.eopyy_image_login);


        //defining the behavior of the two buttons
        addActiveSubstanceBtn.setOnClickListener(v -> addActiveSubstance());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Reset error message
        errorMessage.setText(""); // Hides the error message
    }

    @Override
    public void showError(String message) {
        errorMessage.setText(message);
    }

    @Override
    public void addActiveSubstance() {
        viewModel.getPresenter().createActiveSubstance(ActiveSubstanceName.toString(), Double.parseDouble(ExpectedQuantityPerMonth.toString()));
    }

    @Override
    public void finishCreation() {
        return;
    }

}
