package com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Choice;

import com.example.software_eng_asoee_2024.dao.PharmaceuticalProductDAO;
import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.MedicineType;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;

import java.util.ArrayList;
import java.util.List;


public class PharmaceuticalProductChoicePresenter {
    private PharmaceuticalProductChoiceView view;
    private PharmaceuticalProductDAO pharmaceuticalProductDAO;

    public PharmaceuticalProductChoiceView getView() {
        return view;
    }

    public void setView(PharmaceuticalProductChoiceView view) {
        this.view = view;
    }

    public boolean createPharmaceuticalProduct(String name, Integer retailPrice, Form form, MedicineType type, ArrayList<ActiveSubstance> activeSubs, List<Concentration> activeSubstanceConcentrations, String info) {
        this.pharmaceuticalProductDAO.save(new PharmaceuticalProduct(name, retailPrice, form, type, activeSubs, activeSubstanceConcentrations, info));
        return true;
    }

    public void setPharmaceuticalProductDAO(PharmaceuticalProductDAOMemory DAO) {
        this.pharmaceuticalProductDAO = DAO;
    }
}
