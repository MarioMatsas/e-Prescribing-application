package com.example.software_eng_asoee_2024.views.NOHCS;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.nohcs_select), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(NOHCSSelectionViewModel.class);
        NOHCSSelectionPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        ActiveSubstanceBtn = findViewById(R.id.active_substance_btn);
        PharmaceuticalProductBtn = findViewById(R.id.pharmaceutical_product_btn);


        //defining the behavior of the buttons
        ActiveSubstanceBtn.setOnClickListener(v -> navigateToActiveSubstanceChoicesScreen());
        PharmaceuticalProductBtn.setOnClickListener(v -> navigateToPharmaceuticalProductChoicesScreen());
    }

    /**
     *  Navigates the NOHCS Employee to the active-substance screen where he can navigate again to the specific action
     *  he wants to do with active substances
     */
    @Override
    public void navigateToActiveSubstanceChoicesScreen() {
        Intent intent = new Intent(this, ActiveSubstanceChoiceActivity.class);
        startActivity(intent);
    }

    /**
     *  Navigates the NOHCS Employee to the pharmaceutical-product screen where he can navigate again to the specific action
     *  he wants to do with pharmaceutical products
     */
    @Override
    public void navigateToPharmaceuticalProductChoicesScreen() {
        Intent intent = new Intent(this, PharmaceuticalProductChoiceActivity.class);
        startActivity(intent);
    }
}
