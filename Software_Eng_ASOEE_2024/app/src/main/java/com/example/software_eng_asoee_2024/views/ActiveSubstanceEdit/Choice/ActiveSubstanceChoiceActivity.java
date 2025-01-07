package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Choice;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.software_eng_asoee_2024.R;

public class ActiveSubstanceChoiceActivity extends AppCompatActivity implements ActiveSubstanceChoiceView {

    private ActiveSubstanceChoiceViewModel viewModel;
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

        viewModel = new ViewModelProvider(this).get(ActiveSubstanceChoiceViewModel.class);
        ActiveSubstanceChoicePresenter presenter = viewModel.getPresenter();
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
