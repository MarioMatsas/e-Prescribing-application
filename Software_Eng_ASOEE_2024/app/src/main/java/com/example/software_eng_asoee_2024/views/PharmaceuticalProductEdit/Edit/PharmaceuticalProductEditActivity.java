package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Edit;

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
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Edit.PharmaceuticalProductEditPresenter;
import com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Edit.PharmaceuticalProductEditView;
import com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Edit.PharmaceuticalProductEditViewModel;

import java.util.List;

public class PharmaceuticalProductEditActivity extends AppCompatActivity implements PharmaceuticalProductEditView {

    private PharmaceuticalProductEditViewModel viewModel;
    private Button editPharmaceuticalProductBtn;
    private Spinner pharmaceuticalProductSpinner;
    private EditText name;
    private EditText eqpm;
    private TextView out;
    private PharmaceuticalProduct selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.edit_pharmaceutical_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.edit_pharmaceutical_product), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(PharmaceuticalProductEditViewModel.class);
        PharmaceuticalProductEditPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        pharmaceuticalProductSpinner = findViewById(R.id.edit_pharmaceutical_product_spinner);
        editPharmaceuticalProductBtn = findViewById(R.id.edit_pharmaceutical_product_btn);
        name = findViewById(R.id.edit_pharmaceutical_product_name);
        eqpm = findViewById(R.id.edit_pharmaceutical_product_eqpm);
        out = findViewById(R.id.edit_pharmaceutical_product_output_txt);


        presenter.createPharmaceuticalProductSpinner();

        editPharmaceuticalProductBtn.setOnClickListener(v -> editPharmaceuticalProduct());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void createPharmaceuticalProductSpinner(List<PharmaceuticalProduct> pharmaceuticalProducts) {
        if(pharmaceuticalProducts.isEmpty()) {
            pharmaceuticalProductSpinner.setAdapter(null);
            return;
        }
        ArrayAdapter<PharmaceuticalProduct> adapter = new ArrayAdapter<PharmaceuticalProduct>(this, android.R.layout.simple_spinner_item, pharmaceuticalProducts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pharmaceuticalProductSpinner.setAdapter(adapter);
        selected = pharmaceuticalProducts.get(0);

        pharmaceuticalProductSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                name.setText(pharmaceuticalProducts.get(position).getName());
//                eqpm.setText(pharmaceuticalProducts.get(position).getExpectedQuantityPerMonth().toString());
                name.setEnabled(true);
                eqpm.setEnabled(true);
                selected = pharmaceuticalProducts.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                name.setEnabled(false);
                eqpm.setEnabled(false);
            }
        });
    }

    public void editPharmaceuticalProduct() {
        if(selected == null) return;
        try {
            if (name.getText().toString().isEmpty() || eqpm.getText().toString().isEmpty())
                throw new IllegalArgumentException("Not all fields are filled in");
//            viewModel.getPresenter().editPharmaceuticalProduct(selected, new PharmaceuticalProduct(name.getText().toString(), Double.parseDouble(eqpm.getText().toString())));
            out.setText("Done!");
        } catch (NumberFormatException e) {
            out.setText("Expected Quantity Per Month should be a number");
        } catch (Exception e) {
            out.setText(e.getMessage());

        }
    }
}