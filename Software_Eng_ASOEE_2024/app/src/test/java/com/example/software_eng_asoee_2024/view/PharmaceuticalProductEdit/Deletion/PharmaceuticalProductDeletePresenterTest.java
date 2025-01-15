package com.example.software_eng_asoee_2024.view.PharmaceuticalProductEdit.Deletion;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.MedicineType;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.PrescriptionExecution;
import com.example.software_eng_asoee_2024.domain.PrescriptionLine;
import com.example.software_eng_asoee_2024.domain.ProductQuantity;
import com.example.software_eng_asoee_2024.domain.Unit;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionExecutionDAOMemory;
import com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Deletion.PharmaceuticalProductDeletePresenter;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class PharmaceuticalProductDeletePresenterTest {
    private PharmaceuticalProductDeleteViewStub view;
    private PharmaceuticalProductDeletePresenter presenter;
    private PharmaceuticalProductDAOMemory ppMem;
    private PrescriptionExecutionDAOMemory pxMem;

    @Before
    public void setUp() {
        ArrayList<ActiveSubstance> acList = new ArrayList<ActiveSubstance>();
        acList.add(new ActiveSubstance("1", 10d));

        ArrayList<Concentration> cList = new ArrayList<Concentration>();
        cList.add(new Concentration(10d, Unit.mg_per_disk));

        ppMem = new PharmaceuticalProductDAOMemory();

        for(PharmaceuticalProduct pp : ppMem.findAll())
            ppMem.delete(pp);

        ppMem.save(new PharmaceuticalProduct("A", 100, Form.PILL, MedicineType.GENERIC, acList, cList, "info"));
        ppMem.save(new PharmaceuticalProduct("B", 350, Form.CREAM, MedicineType.ORIGINAL, acList, cList, "info2"));

        pxMem = new PrescriptionExecutionDAOMemory();

        for(PrescriptionExecution px : pxMem.findAll())
            pxMem.delete(px);

        Prescription pr = new Prescription("ba", new Doctor("v", "f", "s"), new Patient("A", "c", 123123123));
        pr.addLine(new PrescriptionLine(Form.PILL, new Concentration(10d, Unit.mg_per_disk), "info", acList.get(0)));
        PrescriptionExecution px = new PrescriptionExecution(new Pharmacist("a", "b"), pr);
        px.addProductQuantity(new ProductQuantity(ppMem.findAll().get(0), 10));
        pxMem.save(px);

        px = new PrescriptionExecution(new Pharmacist("a", "b"), pr);
        px.addProductQuantity(new ProductQuantity(ppMem.findAll().get(0), 10));
        px.addProductQuantity(new ProductQuantity(new PharmaceuticalProduct("Aasdasd", 10, Form.PILL, MedicineType.GENERIC, acList, cList, "info"), 10));
        pxMem.save(px);

        view = new PharmaceuticalProductDeleteViewStub();
        presenter = new PharmaceuticalProductDeletePresenter();

        presenter.setPharmaceuticalProductDAO(ppMem);
        presenter.setPrescriptionExecutionDAO(pxMem);
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

        List<PharmaceuticalProduct> ppl = ppMem.findAll();
        List<PrescriptionExecution> pxl = pxMem.findAll();

        Assert.assertEquals(2, ppMem.findAll().size());
        Assert.assertEquals(2, pxMem.findAll().size());
        Assert.assertEquals("None selected to be deleted", view.msg);

        Assert.assertFalse(presenter.deletePharmaceuticalProduct(ppList.get(0)));

        ppl = ppMem.findAll();
        pxl = pxMem.findAll();

        Assert.assertEquals(1, ppMem.findAll().size());
        Assert.assertEquals(1, pxMem.findAll().size());
        Assert.assertEquals("Done!", view.msg);

        Assert.assertTrue(presenter.deletePharmaceuticalProduct(ppList.get(1)));

        ppl = ppMem.findAll();
        pxl = pxMem.findAll();

        Assert.assertEquals(0, ppMem.findAll().size());
        Assert.assertEquals(1, pxMem.findAll().size());
        Assert.assertEquals("Done!", view.msg);
    }

    @Test
    public void checkSpinner() {
        presenter.createPharmaceuticalProductSpinner();
        Assert.assertEquals(view.spinner, ppMem.findAll());
    }

}
