package com.example.software_eng_asoee_2024.view.PharmaceuticalProductEdit.Deletion;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.MedicineType;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.domain.Unit;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;
import com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Deletion.PharmaceuticalProductDeletePresenter;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class PharmaceuticalProductDeletePresenterTest {
    private PharmaceuticalProductDeleteViewStub view;
    private PharmaceuticalProductDeletePresenter presenter;
    private static PharmaceuticalProductDAOMemory ppMem;

    @BeforeClass
    public static void setUpOnce() {
        ArrayList<ActiveSubstance> acList = new ArrayList<ActiveSubstance>();
        acList.add(new ActiveSubstance("1", 10d));
        ArrayList<Concentration> cList = new ArrayList<Concentration>();
        cList.add(new Concentration(10d, Unit.mg_per_disk));

        ppMem = new PharmaceuticalProductDAOMemory();

        ppMem.save(new PharmaceuticalProduct("A", 100, Form.PILL, MedicineType.GENERIC, acList, cList, "info"));
        ppMem.save(new PharmaceuticalProduct("B", 350, Form.CREAM, MedicineType.ORIGINAL, acList, cList, "info2"));
    }

    @Before
    public void setUp() {
        view = new PharmaceuticalProductDeleteViewStub();
        presenter = new PharmaceuticalProductDeletePresenter();

        presenter.setPharmaceuticalProductDAO(ppMem);
        presenter.setView(view);
    }

    @Test
    public void checkView() {
        presenter.setView(view);
        Assert.assertEquals(presenter.getView(), view);
    }

    @Test
    public void checkDelete() {
        List<PharmaceuticalProduct> ppList = ppMem.findAll();

        Assert.assertFalse(presenter.deletePharmaceuticalProduct(null));
        Assert.assertEquals(2, ppMem.findAll().size());
        Assert.assertEquals("None selected to be deleted", view.msg);

        Assert.assertFalse(presenter.deletePharmaceuticalProduct(ppList.get(0)));
        Assert.assertEquals(1, ppMem.findAll().size());
        Assert.assertEquals("Done!", view.msg);

        Assert.assertTrue(presenter.deletePharmaceuticalProduct(ppList.get(1)));
        Assert.assertEquals(0, ppMem.findAll().size());
        Assert.assertEquals("Done!", view.msg);
    }

    @Test
    public void checkSpinner() {
        presenter.createPharmaceuticalProductSpinner();
        Assert.assertEquals(view.spinner, ppMem.findAll());
    }

}
