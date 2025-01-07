package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Choice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.software_eng_asoee_2024.R;
import com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Choice.ActiveSubstanceChoiceActivity;

public class PharmaceuticalProductChoiceActivity extends AppCompatActivity implements PharmaceuticalProductChoiceView {

    private PharmaceuticalProductChoiceViewModel viewModel;
    private Button addBtn;
    private Button editBtn;
    private Button removeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.pharmaceutical_product_choices);

        viewModel = new ViewModelProvider(this).get(PharmaceuticalProductChoiceViewModel.class);
        PharmaceuticalProductChoicePresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        addBtn = findViewById(R.id.new_pharmaceutical_product_btn);
        editBtn = findViewById(R.id.edit_pharmaceutical_product_btn);
        removeBtn = findViewById(R.id.delete_pharmaceutical_product_btn);


        //defining the behavior of the buttons
        addBtn.setOnClickListener(v -> addPharmaceuticalProduct());
        editBtn.setOnClickListener(v -> editPharmaceuticalProduct());
        removeBtn.setOnClickListener(v -> deletePharmaceuticalProduct());

    }

    // TODO
    @Override
    public void addPharmaceuticalProduct() {

    }

    @Override
    public void editPharmaceuticalProduct() {

    }

    @Override
    public void deletePharmaceuticalProduct() {

    }
}
