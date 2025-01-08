package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Choice;

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
import com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Choice.PharmaceuticalProductChoiceActivity;
import com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Creation.PharmaceuticalProductCreationActivity;
import com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Deletion.PharmaceuticalProductDeleteActivity;
import com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Edit.PharmaceuticalProductEditActivity;

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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.pharmaceutical_product_choices), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(PharmaceuticalProductChoiceViewModel.class);
        PharmaceuticalProductChoicePresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        addBtn = findViewById(R.id.new_pharmaceutical_product_btn);
        editBtn = findViewById(R.id.edit_pharmaceutical_product_btn);
        removeBtn = findViewById(R.id.delete_pharmaceutical_product_btn);


        //defining the behavior of the buttons
        addBtn.setOnClickListener(v -> navigateToAddPharmaceuticalProduct());
        editBtn.setOnClickListener(v -> navigateToEditPharmaceuticalProduct());
        removeBtn.setOnClickListener(v -> navigateToDeletePharmaceuticalProduct());

    }

    @Override
    public void navigateToAddPharmaceuticalProduct() {
        Intent intent = new Intent(this, PharmaceuticalProductCreationActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToEditPharmaceuticalProduct() {
        Intent intent = new Intent(this, PharmaceuticalProductEditActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToDeletePharmaceuticalProduct() {
        Intent intent = new Intent(this, PharmaceuticalProductDeleteActivity.class);
        startActivity(intent);
    }
}
