package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Deletion;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.domain.Unit;
import com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Creation.PharmaceuticalProductCreationViewModel;

import java.util.ArrayList;
import java.util.List;

public class PharmaceuticalProductDeleteActivity extends AppCompatActivity implements PharmaceuticalProductDeleteView {

    private PharmaceuticalProductDeleteViewModel viewModel;
    private Button deletePharmaceuticalProductBtn;
    private Spinner pharmaceuticalProductSpinner;
    private EditText pharmaceuticalProductName;
    private EditText retailPrice;
    private TextView out;
    private EditText form;
    private EditText type;
    private EditText information;
    private ListView activeSubstanceViewList;
    private ArrayList<ActiveSubstance> activeSubstanceList = new ArrayList<ActiveSubstance>();
    private ArrayList<Concentration> concentrationList = new ArrayList<Concentration>();
    private PharmaceuticalProduct selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.delete_pharmaceutical_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.delete_pharmaceutical_product), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(PharmaceuticalProductDeleteViewModel.class);
        PharmaceuticalProductDeletePresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        pharmaceuticalProductSpinner = findViewById(R.id.delete_pharmaceutical_product_spinner);
        deletePharmaceuticalProductBtn = findViewById(R.id.delete_pharmaceutical_product_btn);
        pharmaceuticalProductName = findViewById(R.id.delete_pharmaceutical_product_name);
        out = findViewById(R.id.delete_pharmaceutical_product_output_txt);
        retailPrice = findViewById(R.id.delete_pharmaceutical_product_price);
        form = findViewById(R.id.delete_pharmaceutical_product_form);
        type = findViewById(R.id.delete_pharmaceutical_product_type);
        information = findViewById(R.id.delete_pharmaceutical_product_info);
        activeSubstanceViewList = findViewById(R.id.delete_pharmaceutical_product_ac_list);

        presenter.createPharmaceuticalProductSpinner();

        deletePharmaceuticalProductBtn.setOnClickListener(v -> deletePharmaceuticalProduct(selected));
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
                selected = pharmaceuticalProducts.get(position);
                pharmaceuticalProductName.setText(selected.getName());
                retailPrice.setText(selected.getRetailPrice().toString());
                form.setText(selected.getForm().name());
                type.setText(selected.getMedicineType().name());
                information.setText(selected.getInformation());
                activeSubstanceList.clear();
                activeSubstanceList.addAll(selected.getActiveSubstances());
                concentrationList.clear();
                concentrationList.addAll(selected.getActiveSubstanceConcentrations());
                createActiveSubstanceList();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    public void deletePharmaceuticalProduct(PharmaceuticalProduct ac) {
        if(selected == null) return;
        if(viewModel.getPresenter().deletePharmaceuticalProduct(ac)) {
            selected = null;
            pharmaceuticalProductName.setText("");
            retailPrice.setText("");
            form.setText("");
            type.setText("");
            information.setText("");
            activeSubstanceList.clear();
            concentrationList.clear();
            createActiveSubstanceList();
        }
        out.setText("Done!");
    }

    public void createActiveSubstanceList() {
        ArrayList<String> temp = new ArrayList<>();
        for(int i = 0; i < activeSubstanceList.size(); i++) {
            temp.add(activeSubstanceList.get(i).toString() + "\n" + concentrationList.get(i) + "\n-");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, temp);
        activeSubstanceViewList.setAdapter(adapter);
    }
}
