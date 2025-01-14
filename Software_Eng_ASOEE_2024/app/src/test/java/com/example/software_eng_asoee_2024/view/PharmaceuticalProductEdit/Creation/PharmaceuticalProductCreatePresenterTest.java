package com.example.software_eng_asoee_2024.view.PharmaceuticalProductEdit.Creation;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.MedicineType;
import com.example.software_eng_asoee_2024.domain.Unit;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;
import com.example.software_eng_asoee_2024.view.PharmaceuticalProductEdit.Creation.PharmaceuticalProductCreateViewStub;
import com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Creation.PharmaceuticalProductCreationPresenter;

import org.junit.*;

import java.util.ArrayList;

public class PharmaceuticalProductCreatePresenterTest {
    private PharmaceuticalProductCreateViewStub view;
    private PharmaceuticalProductCreationPresenter presenter;
    @Before
    public void setUp() {
        view = new PharmaceuticalProductCreateViewStub();
        presenter = new PharmaceuticalProductCreationPresenter();
        presenter.setPharmaceuticalProductDAO(new PharmaceuticalProductDAOMemory());
        ActiveSubstanceDAOMemory acMem = new ActiveSubstanceDAOMemory();
        acMem.save(new ActiveSubstance("1", 10d));
        acMem.save(new ActiveSubstance("2", 10d));
        presenter.setActiveSubstanceDAO(acMem);
        presenter.setView(view);
    }

    @Test
    public void checkView() {
        presenter.setView(view);
        Assert.assertEquals(presenter.getView(), view);
    }

    @Test
    public void checkCreation() {
        ArrayList<ActiveSubstance> acList = new ArrayList<ActiveSubstance>();
        acList.add(new ActiveSubstance("1", 10d));
        ArrayList<Concentration> cList = new ArrayList<Concentration>();
        cList.add(new Concentration(10d, Unit.mg_per_disk));
        presenter.createPharmaceuticalProduct("A", "100", Form.PILL, MedicineType.ORIGINAL, acList, cList, "info");
        Assert.assertEquals(view.msg, "Done!");
        presenter.createPharmaceuticalProduct("A", "100", Form.PILL, MedicineType.ORIGINAL, acList, cList, "info");
        Assert.assertEquals(view.msg, "Cant have two identical pharmaceutical products");
        presenter.createPharmaceuticalProduct("", "100", Form.CREAM, MedicineType.ORIGINAL, acList, cList, "info");
        Assert.assertEquals(view.msg, "Not all fields are filled in");
        presenter.createPharmaceuticalProduct("A", "", Form.CREAM, MedicineType.ORIGINAL, acList, cList, "info");
        Assert.assertEquals(view.msg, "Not all fields are filled in");
        presenter.createPharmaceuticalProduct("A", "100", Form.CREAM, MedicineType.ORIGINAL, new ArrayList<ActiveSubstance>(), cList, "info");
        Assert.assertEquals(view.msg, "No Active Substances have been given");
        presenter.createPharmaceuticalProduct("A", "Hi", Form.CREAM, MedicineType.ORIGINAL, acList, cList, "info");
        Assert.assertEquals(view.msg, "Retail Price should be a number");
        presenter.createPharmaceuticalProduct("A", "100", Form.CREAM, MedicineType.ORIGINAL, acList, cList, "info");
        Assert.assertEquals(view.msg, "Done!");
    }

    @Test
    public void checkSpinner() {
        presenter.createActiveSubstanceSpinner();
        Assert.assertEquals(2, view.spinner.size());
    }
}
