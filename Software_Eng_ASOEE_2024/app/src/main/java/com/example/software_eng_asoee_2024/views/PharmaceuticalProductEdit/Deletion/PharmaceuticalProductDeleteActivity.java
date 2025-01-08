package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Deletion;

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

public class PharmaceuticalProductDeleteActivity extends AppCompatActivity implements PharmaceuticalProductDeleteView {

    private PharmaceuticalProductDeleteViewModel viewModel;
    private Button addPharmaceuticalProductBtn;
    private EditText PharmaceuticalProductName;
    private EditText ExpectedQuantityPerMonth;
    private TextView errorMessage;

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

        viewModel = new ViewModelProvider(this).get(PharmaceuticalProductDeleteViewModel.class);
        PharmaceuticalProductDeletePresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        addPharmaceuticalProductBtn = findViewById(R.id.create_active_substance_btn);
        PharmaceuticalProductName = findViewById(R.id.select_active_substance_name);
        ExpectedQuantityPerMonth = findViewById(R.id.select_active_substance_eqpm);
        errorMessage = findViewById(R.id.error_text_cas);


        //defining the behavior of the two buttons
//        addPharmaceuticalProductBtn.setOnClickListener(v -> addPharmaceuticalProduct());
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

//    @Override
//    public void addPharmaceuticalProduct() {
//        viewModel.getPresenter().createPharmaceuticalProduct(PharmaceuticalProductName.toString(), Double.parseDouble(ExpectedQuantityPerMonth.toString()));
//    }
//
//    @Override
//    public void finishCreation() {
//        // TODO
//        return;
//    }

}
