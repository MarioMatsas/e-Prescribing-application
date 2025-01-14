package com.example.software_eng_asoee_2024.view.PharmaceuticalProductEdit.Edit;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.MedicineType;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.domain.Unit;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;
import com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Edit.PharmaceuticalProductEditPresenter;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class PharmaceuticalProductEditPresenterTest {
    private PharmaceuticalProductEditViewStub view;
    private PharmaceuticalProductEditPresenter presenter;
    private static PharmaceuticalProductDAOMemory ppMem;
    private static ActiveSubstanceDAOMemory acMem;
    private static ArrayList<ActiveSubstance> acList = new ArrayList<ActiveSubstance>();
    private static ArrayList<Concentration> cList = new ArrayList<Concentration>();


    @BeforeClass
    public static void setUpOnce() {

        acList.add(new ActiveSubstance("1", 10d));

        cList.add(new Concentration(10d, Unit.mg_per_disk));

        ppMem = new PharmaceuticalProductDAOMemory();

        ppMem.save(new PharmaceuticalProduct("A", 100, Form.PILL, MedicineType.GENERIC, acList, cList, "info"));
        ppMem.save(new PharmaceuticalProduct("B", 350, Form.CREAM, MedicineType.ORIGINAL, acList, cList, "info2"));

        acMem = new ActiveSubstanceDAOMemory();
        acMem.save(acList.get(0));
        acMem.save(new ActiveSubstance("2", 25d));
    }

    @Before
    public void setUp() {
        view = new PharmaceuticalProductEditViewStub();
        presenter = new PharmaceuticalProductEditPresenter();

        presenter.setPharmaceuticalProductDAO(ppMem);
        presenter.setActiveSubstanceDAO(acMem);
        presenter.setView(view);
    }

    @Test
    public void checkView() {
        presenter.setView(view);
        Assert.assertEquals(presenter.getView(), view);
    }

    @Test
    public void checkSpinners() {
        presenter.createPharmaceuticalProductSpinner();
        Assert.assertEquals(ppMem.findAll(), view.ppSpinner);
        presenter.createActiveSubstanceSpinner();
        Assert.assertEquals(acMem.findAll(), view.acSpinner);
    }

    @Test
    public void checkEdit() {
        List<PharmaceuticalProduct> ppList = ppMem.findAll();

        Assert.assertEquals(2, ppList.size());

        presenter.editPharmaceuticalProduct(null, "C", "1000", Form.PILL, MedicineType.GENERIC, acList, cList, "info");
        Assert.assertEquals("None selected to be delete", view.msg);

        presenter.editPharmaceuticalProduct(ppList.get(0), "", "1000", Form.PILL, MedicineType.GENERIC, acList, cList, "info");
        Assert.assertEquals("Not all fields are filled in", view.msg);

        presenter.editPharmaceuticalProduct(ppList.get(0), "C", "", Form.PILL, MedicineType.GENERIC, acList, cList, "info");
        Assert.assertEquals("Not all fields are filled in", view.msg);

        presenter.editPharmaceuticalProduct(ppList.get(0), "C", "1000", Form.PILL, MedicineType.GENERIC, new ArrayList<ActiveSubstance>(), cList, "info");
        Assert.assertEquals("No Active Substances have been given", view.msg);

        presenter.editPharmaceuticalProduct(ppList.get(0), "C", "hi", Form.PILL, MedicineType.GENERIC, acList, cList, "info");
        Assert.assertEquals("Retail Price should be a number", view.msg);

        presenter.editPharmaceuticalProduct(ppList.get(0), "B", "350", Form.CREAM, MedicineType.ORIGINAL, acList, cList, "info2");
        Assert.assertEquals("Cant have two identical pharmaceutical products", view.msg);

        presenter.editPharmaceuticalProduct(ppList.get(0), "C", "100", Form.PILL, MedicineType.GENERIC, acList, cList, "info");
        Assert.assertEquals("Done!", view.msg);
        Assert.assertEquals("C", ppMem.findAll().get(0).getName());
    }

}
