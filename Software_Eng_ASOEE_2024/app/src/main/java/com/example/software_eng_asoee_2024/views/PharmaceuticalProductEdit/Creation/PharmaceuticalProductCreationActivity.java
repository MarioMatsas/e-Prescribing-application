package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Creation;

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
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;

public class PharmaceuticalProductCreationActivity extends AppCompatActivity implements PharmaceuticalProductCreationView {

    private PharmaceuticalProductCreationViewModel viewModel;
    private Button addPharmaceuticalProductBtn;
    private EditText PharmaceuticalProductName;
    private EditText ExpectedQuantityPerMonth;
    private TextView out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.create_pharmaceutical_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.create_pharmaceutical_product), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(PharmaceuticalProductCreationViewModel.class);
        PharmaceuticalProductCreationPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        addPharmaceuticalProductBtn = findViewById(R.id.create_pharmaceutical_product_btn);
        PharmaceuticalProductName = findViewById(R.id.select_pharmaceutical_product_name);
        ExpectedQuantityPerMonth = findViewById(R.id.select_pharmaceutical_product_eqpm);
        out = findViewById(R.id.error_text_cas);


        //defining the behavior of the two buttons
        addPharmaceuticalProductBtn.setOnClickListener(v -> addPharmaceuticalProduct());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void addPharmaceuticalProduct() {
        try {
            if (PharmaceuticalProductName.getText().toString().isEmpty() || ExpectedQuantityPerMonth.getText().toString().isEmpty())
                throw new IllegalArgumentException("Not all fields are filled in");
            viewModel.getPresenter().createPharmaceuticalProduct(new PharmaceuticalProduct(PharmaceuticalProductName.getText().toString(), Double.parseDouble(ExpectedQuantityPerMonth.getText().toString())));
            out.setText("Done!");
        } catch (NumberFormatException e) {
            out.setText("Expected Quantity Per Month should be a number");
        } catch (Exception e) {
            out.setText(e.getMessage());

        }
    }
}
