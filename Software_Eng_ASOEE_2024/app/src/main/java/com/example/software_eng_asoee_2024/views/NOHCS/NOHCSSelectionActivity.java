package com.example.software_eng_asoee_2024.views.NOHCS;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.software_eng_asoee_2024.R;
import com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Choice.ActiveSubstanceChoiceActivity;
import com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Choice.PharmaceuticalProductChoiceActivity;

public class NOHCSSelectionActivity extends AppCompatActivity implements NOHCSSelectionView {

    private NOHCSSelectionViewModel viewModel;
    private Button ActiveSubstanceBtn;
    private Button PharmaceuticalProductBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.nohcs_select);

        viewModel = new ViewModelProvider(this).get(NOHCSSelectionViewModel.class);
        NOHCSSelectionPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        ActiveSubstanceBtn = findViewById(R.id.active_substance_btn);
        PharmaceuticalProductBtn = findViewById(R.id.pharmaceutical_product_btn);


        //defining the behavior of the two buttons
        ActiveSubstanceBtn.setOnClickListener(v -> navigateToActiveSubstanceChoicesScreen());
        PharmaceuticalProductBtn.setOnClickListener(v -> navigateToPharmaceuticalProductChoicesScreen());
    }

    @Override
    public void navigateToActiveSubstanceChoicesScreen() {
        Intent intent = new Intent(this, ActiveSubstanceChoiceActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToPharmaceuticalProductChoicesScreen() {
        Intent intent = new Intent(this, PharmaceuticalProductChoiceActivity.class);
        startActivity(intent);
    }
}
