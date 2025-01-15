package com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Choice;

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
import com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Creation.ActiveSubstanceCreationActivity;
import com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Edit.ActiveSubstanceEditActivity;
import com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Deletion.ActiveSubstanceDeleteActivity;

public class ActiveSubstanceChoiceActivity extends AppCompatActivity implements ActiveSubstanceChoiceView {

    private ActiveSubstanceChoiceViewModel viewModel;
    private Button addActiveSubstanceChoiceBtn;
    private Button changeActiveSubstanceChoiceBtn;
    private Button deleteActiveSubstanceChoiceBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.active_substance_choices);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.active_substance_choices), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(ActiveSubstanceChoiceViewModel.class);
        ActiveSubstanceChoicePresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        addActiveSubstanceChoiceBtn = findViewById(R.id.new_active_substance_btn);
        changeActiveSubstanceChoiceBtn = findViewById(R.id.edit_active_substance_btn);
        deleteActiveSubstanceChoiceBtn = findViewById(R.id.delete_active_substance_btn);

        //defining the behavior of the buttons
        addActiveSubstanceChoiceBtn.setOnClickListener(v -> navigateToAddActiveSubstance());
        changeActiveSubstanceChoiceBtn.setOnClickListener(v -> navigateToEditActiveSubstance());
        deleteActiveSubstanceChoiceBtn.setOnClickListener(v -> navigateToDeleteActiveSubstance());
    }


    /**
     *  Navigates the NOHCS Employee to the add-active-substance screen where he can create new active substances
     */
    @Override
    public void navigateToAddActiveSubstance() {
        Intent intent = new Intent(this, ActiveSubstanceCreationActivity.class);
        startActivity(intent);
    }

    /**
     *  Navigates the NOHCS Employee to the edit-active-substance screen where he can edit existing active substances
     */
    @Override
    public void navigateToEditActiveSubstance() {
        Intent intent = new Intent(this, ActiveSubstanceEditActivity.class);
        startActivity(intent);
    }

    /**
     *  Navigates the NOHCS Employee to the delete-active-substance screen where he can delete active substances
     */
    @Override
    public void navigateToDeleteActiveSubstance() {
        Intent intent = new Intent(this, ActiveSubstanceDeleteActivity.class);
        startActivity(intent);
    }
}
