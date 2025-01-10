package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Edit;

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
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.MedicineType;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.domain.Unit;
import com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Edit.PharmaceuticalProductEditPresenter;
import com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Edit.PharmaceuticalProductEditView;
import com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Edit.PharmaceuticalProductEditViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class PharmaceuticalProductEditActivity extends AppCompatActivity implements PharmaceuticalProductEditView {

    private PharmaceuticalProductEditViewModel viewModel;
    private Button editPharmaceuticalProductBtn;
    private Spinner pharmaceuticalProductSpinner;
    private Button addActiveSubstanceBtn;
    private Button popActiveSubstanceBtn;
    private TextView out;

    private EditText pharmaceuticalProductName;
    private EditText retailPrice;
    private Spinner formSpinner;
    private Spinner typeSpinner;
    private Spinner activeSubstanceSpinner;
    private EditText concentrationInput;
    private EditText concentrationUnit;
    private EditText information;
    private ListView activeSubstanceViewList;
    private ArrayList<ActiveSubstance> activeSubstanceList = new ArrayList<ActiveSubstance>();
    private ArrayList<Concentration> concentrationList = new ArrayList<Concentration>();
    private  PharmaceuticalProduct selected;
    private Unit selectedUnit;
     private Map<Form, Integer> formDict = new HashMap<Form, Integer>();



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
        addActiveSubstanceBtn = findViewById(R.id.edit_pharmaceutical_product_add_ac_btn);
        popActiveSubstanceBtn = findViewById(R.id.edit_pharmaceutical_product_pop_ac_btn);
        pharmaceuticalProductName = findViewById(R.id.edit_pharmaceutical_product_name);
        retailPrice = findViewById(R.id.edit_pharmaceutical_product_price);
        out = findViewById(R.id.edit_pharmaceutical_product_output_txt);
        formSpinner = findViewById(R.id.edit_pharmaceutical_product_form_spinner);
        typeSpinner = findViewById(R.id.edit_pharmaceutical_product_type_spinner);
        activeSubstanceSpinner = findViewById(R.id.edit_pharmaceutical_product_acs_spinner);
        concentrationInput = findViewById(R.id.edit_pharmaceutical_product_concentration);
        concentrationUnit = findViewById(R.id.edit_pharmaceutical_product_concentration_unit);
        information = findViewById(R.id.edit_pharmaceutical_product_info);
        activeSubstanceViewList = findViewById(R.id.edit_pharmaceutical_product_ac_list);

        formDict.put(Form.PILL, 0);
        formDict.put(Form.CREAM, 1);
        formDict.put(Form.SPRAY, 2);
        formDict.put(Form.SYRUP, 3);

        presenter.createPharmaceuticalProductSpinner();

        //defining the behavior of the buttons
        editPharmaceuticalProductBtn.setOnClickListener(v -> editPharmaceuticalProduct());
        addActiveSubstanceBtn.setOnClickListener(v -> addActiveSubstanceToPharmaceuticalProduct());
        popActiveSubstanceBtn.setOnClickListener(v -> {
            if(!activeSubstanceList.isEmpty() && !concentrationList.isEmpty()) {
                activeSubstanceList.remove(activeSubstanceList.size() - 1);
                concentrationList.remove(concentrationList.size() - 1);
                createActiveSubstanceList();
                viewModel.getPresenter().createActiveSubstanceSpinner();
            }
        });
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
                formSpinner.setSelection(formDict.get(selected.getForm()));
                typeSpinner.setSelection(selected.getMedicineType().equals(MedicineType.GENERIC) ? 0 : 1);
                information.setText(selected.getInformation());
                activeSubstanceList = (ArrayList<ActiveSubstance>) selected.getActiveSubstances();
                concentrationList = (ArrayList<Concentration>) selected.getActiveSubstanceConcentrations();

                createActiveSubstanceList();
                createFormAndTypeSpinners();
                viewModel.getPresenter().createActiveSubstanceSpinner();

                pharmaceuticalProductName.setEnabled(true);
                retailPrice.setEnabled(true);
                typeSpinner.setEnabled(true);
                activeSubstanceSpinner.setEnabled(true);
                concentrationInput.setEnabled(true);
                information.setEnabled(true);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {activeSubstanceList.clear();
                activeSubstanceList.clear();
                concentrationList.clear();

                createActiveSubstanceList();
                createFormAndTypeSpinners();

                pharmaceuticalProductName.setEnabled(false);
                retailPrice.setEnabled(false);
                typeSpinner.setEnabled(false);
                activeSubstanceSpinner.setEnabled(false);
                concentrationInput.setEnabled(false);
                information.setEnabled(false);
                selected = null;
            }
        });
    }

    @Override
    public void createActiveSubstanceSpinner(List<ActiveSubstance> activeSubstances) {
        if(activeSubstances.isEmpty()) {
            activeSubstanceSpinner.setAdapter(null);
            return;
        }
        ArrayList<ActiveSubstance> temp = new ArrayList<>();
        for(ActiveSubstance activeSubstance : activeSubstances) {
            if(!activeSubstanceList.contains(activeSubstance)) temp.add(activeSubstance);
        }
        ArrayAdapter<ActiveSubstance> adapter = new ArrayAdapter<ActiveSubstance>(this, android.R.layout.simple_spinner_item, temp);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activeSubstanceSpinner.setAdapter(adapter);
    }

    public void editPharmaceuticalProduct() {
        viewModel.getPresenter().editPharmaceuticalProduct(selected, pharmaceuticalProductName.getText().toString(), retailPrice.getText().toString(), (Form) formSpinner.getSelectedItem(), (MedicineType) typeSpinner.getSelectedItem(), activeSubstanceList, concentrationList, information.getText().toString());
    }

    public void addActiveSubstanceToPharmaceuticalProduct() {
        if(activeSubstanceSpinner.getSelectedItem() == null) return;
        try {
            if (concentrationInput.getText().toString().isEmpty())
                throw new IllegalArgumentException("Concentration isn't filled in");
            activeSubstanceList.add((ActiveSubstance) activeSubstanceSpinner.getSelectedItem());
            concentrationList.add(new Concentration(Double.parseDouble(concentrationInput.getText().toString()), selectedUnit));
            createActiveSubstanceList();
            viewModel.getPresenter().createActiveSubstanceSpinner();
            showMessage("Added!");
        } catch (NumberFormatException e) {
            showMessage("Concentration should be a number");
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }
    public void createActiveSubstanceList() {
        ArrayList<String> temp = new ArrayList<>();
        for(int i = 0; i < activeSubstanceList.size(); i++) {
            temp.add(activeSubstanceList.get(i).toString() + "\n" + concentrationList.get(i) + "\n-");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, temp);
        activeSubstanceViewList.setAdapter(adapter);

        formSpinner.setEnabled(temp.isEmpty());
    }

    public void createFormAndTypeSpinners() {
        // Get all enum values as an array of MedicineType
        MedicineType[] medicineTypeOptions = MedicineType.values();

        // Set up the adapter with the enum values themselves
        ArrayAdapter<MedicineType> medicineTypeAdapter = new ArrayAdapter<MedicineType>(this, android.R.layout.simple_spinner_item, medicineTypeOptions);
        medicineTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(medicineTypeAdapter);

        Form[] formOptions = Form.values();

        // Set up the adapter with the enum values themselves
        ArrayAdapter<Form> formAdapter = new ArrayAdapter<Form>(this, android.R.layout.simple_spinner_item, formOptions);
        formAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        formSpinner.setAdapter(formAdapter);

        // Add listener for item selection
        formSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (formOptions[position]) {
                    case PILL:
                        selectedUnit = Unit.mg_per_disk;
                        break;
                    case CREAM:
                        selectedUnit = Unit.mg_per_g;
                        break;
                    case SPRAY:
                        selectedUnit = Unit.mg_per_dose;
                        break;
                    case SYRUP:
                        selectedUnit = Unit.mg_per_ml;
                        break;
                    default:
                        break;
                }
                concentrationUnit.setText(selectedUnit.name());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

    }
    @Override
    public void showMessage(String s) {
        out.setText(s);
    }
}