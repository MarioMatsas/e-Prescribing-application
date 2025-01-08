package com.example.software_eng_asoee_2024.views.Report;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.software_eng_asoee_2024.R;
import com.example.software_eng_asoee_2024.dao.Initializer;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.memorydao.MemoryInitializer;
import com.example.software_eng_asoee_2024.views.Login.LoginPresenter;
import com.example.software_eng_asoee_2024.views.Login.LoginViewModel;
import com.example.software_eng_asoee_2024.views.PrescreptionExecution.Selection.PrescriptionSelectionActivity;
import com.example.software_eng_asoee_2024.views.PrescriptionCreation.PatientSearching.PatientSearchingActivity;
import com.example.software_eng_asoee_2024.views.SignUp.SignUpActivity;

import java.util.ArrayList;
/**
 *
 *  A class used to handle all of the generate report screen's UI
 *  and user interactions with the screen.
 *
 */
public class ReportActivity extends AppCompatActivity implements ReportView {
    private ReportViewModel viewModel;
    private Button generateReportButton;
    private TextView doctorsText;
    private ImageView logo;
    private Spinner recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.generate_report);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.generate_report_id), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(ReportViewModel.class);
        ReportPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        generateReportButton = findViewById(R.id.generate_report_btn);
        doctorsText = findViewById(R.id.unlawful_docs_txt);
        logo = findViewById(R.id.eopyy_image_login);
        recycler = findViewById(R.id.report_spinner);

        generateReportButton.setOnClickListener(v -> generateReport());
    }

    @Override
    public void generateReport() {
        System.out.println("GENERATE");
        ArrayList<String> doctors = viewModel.getPresenter().generateReport();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, doctors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        recycler.setAdapter(adapter);
    }
}
