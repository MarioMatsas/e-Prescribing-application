package com.example.software_eng_asoee_2024.view.PharmaceuticalProductEdit.Creation;

import static com.example.software_eng_asoee_2024.domain.Form.CREAM;
import static com.example.software_eng_asoee_2024.domain.Form.PILL;
import static com.example.software_eng_asoee_2024.domain.Form.SPRAY;
import static com.example.software_eng_asoee_2024.domain.Form.SYRUP;

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

        for(ActiveSubstance ac : acMem.findAll())
            acMem.delete(ac);

        acMem.save(new ActiveSubstance("1", 10d));
        acMem.save(new ActiveSubstance("2", 10d));

        presenter.setActiveSubstanceDAO(acMem);
        presenter.setView(view);
    }

    /**
     * tests the getter and setter of presenter
     */
    @Test
    public void checkView() {
        presenter.setView(view);
        Assert.assertEquals(presenter.getView(), view);
    }

    /**
     * Checks if the method sets the proper message per occasion
     */
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

    /**
     * checks if the spinner is loaded properly
     */
    @Test
    public void checkSpinner() {
        presenter.createActiveSubstanceSpinner();
        Assert.assertEquals(2, view.spinner.size());
    }

    /**
     *
     * Tests if the correct unit is returned, according to the selected form
     */
    @Test
    public void testUnitReturn(){
        Assert.assertEquals(presenter.onSelectedUnit(PILL), Unit.mg_per_disk);
        Assert.assertEquals(presenter.onSelectedUnit(CREAM), Unit.mg_per_g);
        Assert.assertEquals(presenter.onSelectedUnit(SPRAY), Unit.mg_per_dose);
        Assert.assertEquals(presenter.onSelectedUnit(SYRUP), Unit.mg_per_ml);
    }

    /**
     *
     * Tests if the addition of the substance was handled correctly when it was added
     */
    @Test
    public void testSubstAddToProd(){
        presenter.addSubToProduct(null, "123", new ArrayList<ActiveSubstance>(),
                new ArrayList<Concentration>(), Unit.mg_per_disk);

        presenter.addSubToProduct(new ActiveSubstance("Paracetamol", 10d), "", new ArrayList<ActiveSubstance>(),
                new ArrayList<Concentration>(), Unit.mg_per_disk);
        Assert.assertEquals("Concentration isn't filled in", view.msg);

        presenter.addSubToProduct(new ActiveSubstance("Paracetamol", 10d), "ertet", new ArrayList<ActiveSubstance>(),
                new ArrayList<Concentration>(), Unit.mg_per_disk);
        Assert.assertEquals("Concentration should be a number", view.msg);

        presenter.addSubToProduct(new ActiveSubstance("Paracetamol", 10d), "2", new ArrayList<ActiveSubstance>(),
                new ArrayList<Concentration>(), Unit.mg_per_disk);
        Assert.assertEquals("Added!", view.msg);
    }
}
