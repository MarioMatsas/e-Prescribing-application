package com.example.software_eng_asoee_2024.view.ActiveSubstance.Deletion;

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
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionExecutionDAOMemory;
import com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Deletion.ActiveSubstanceDeletePresenter;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class ActiveSubstanceDeletePresenterTest {
    private ActiveSubstanceDeleteViewStub view;
    private ActiveSubstanceDeletePresenter presenter;
    private List<ActiveSubstance> acList;
    private ActiveSubstanceDAOMemory acMem;
    private PrescriptionDAOMemory prMem;
    private PharmaceuticalProductDAOMemory ppMem;
    private PrescriptionExecutionDAOMemory pxMem;

    @Before
    public void setUp() {
        acMem = new ActiveSubstanceDAOMemory();

        for(ActiveSubstance ac : acMem.findAll())
            acMem.delete(ac);

        acMem.save(new ActiveSubstance("A", 15d));
        acMem.save(new ActiveSubstance("B", 1d));
        acMem.save(new ActiveSubstance("C", 31d));
        acList = acMem.findAll();

        prMem = new PrescriptionDAOMemory();

        for(Prescription pr : prMem.findAll())
            prMem.delete(pr);

        Prescription tp = new Prescription("AB", new Doctor("A", "A", "A"), new Patient("B", "B", 123123123));
        tp.addLine(new PrescriptionLine(Form.PILL, new Concentration(15d, Unit.mg_per_disk), "na", acList.get(0)));
        prMem.save(tp);
        tp = new Prescription("BA", new Doctor("B", "B", "B"), new Patient("V", "V", 321321321));
        tp.addLine(new PrescriptionLine(Form.PILL, new Concentration(15d, Unit.mg_per_disk), "na", acList.get(0)));
        tp.addLine(new PrescriptionLine(Form.PILL, new Concentration(15d, Unit.mg_per_disk), "na", acList.get(2)));
        prMem.save(tp);

        ppMem = new PharmaceuticalProductDAOMemory();

        for(PharmaceuticalProduct pp : ppMem.findAll())
            ppMem.delete(pp);

        ArrayList<ActiveSubstance> tac = new ArrayList<ActiveSubstance>();
        tac.add(acList.get(0));
        ArrayList<Concentration> acc = new ArrayList<Concentration>();
        acc.add(new Concentration(15d, Unit.mg_per_disk));
        ppMem.save(new PharmaceuticalProduct("Prod", 10, Form.PILL, MedicineType.ORIGINAL, (ArrayList<ActiveSubstance>) tac.clone(), (ArrayList<Concentration>) acc.clone(), ""));
        tac.add(acList.get(2));
        acc.add(new Concentration(15d, Unit.mg_per_disk));
        ppMem.save(new PharmaceuticalProduct("Pro2", 10, Form.PILL, MedicineType.GENERIC, tac, acc, ""));

        pxMem = new PrescriptionExecutionDAOMemory();

        for(PrescriptionExecution px : pxMem.findAll())
            pxMem.delete(px);

        PrescriptionExecution tpx = new PrescriptionExecution(new Pharmacist("a", "b"), prMem.findAll().get(0));
        pxMem.save(tpx);

        tp = new Prescription("BAB", new Doctor("B", "B", "B"), new Patient("V", "V", 321321321));
        tp.addLine(new PrescriptionLine(Form.PILL, new Concentration(10d, Unit.mg_per_disk), "inf", new ActiveSubstance("a", 10d)));
        tp.addLine(new PrescriptionLine(Form.PILL, new Concentration(10d, Unit.mg_per_disk), "inf2", new ActiveSubstance("a2", 10d)));
        tp.addLine(new PrescriptionLine(Form.PILL, new Concentration(10d, Unit.mg_per_disk), "inf", acList.get(2)));

        tpx = new PrescriptionExecution(new Pharmacist("b", "c"), tp);
        tpx.addProductQuantity(new ProductQuantity(ppMem.findAll().get(1),10));
        pxMem.save(tpx);


        view = new ActiveSubstanceDeleteViewStub();
        presenter = new ActiveSubstanceDeletePresenter();
        presenter.setPharmaceuticalProductDAO(ppMem);
        presenter.setActiveSubstanceDAO(acMem);
        presenter.setPrescriptionDAO(prMem);
        presenter.setPrescriptionExecutionDAO(pxMem);
        presenter.setView(view);
    }


    /**
     * checks if the spinner is loaded properly
     */
    @Test
    public void checkSpinnerCreation() {
        presenter.createActiveSubstanceSpinner();
        Assert.assertEquals(acList, view.acList);
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
     * checks if the items are deleted properly.
     */
    @Test
    public void checkDeletion() {
        List<ActiveSubstance> acList = acMem.findAll();
        Assert.assertFalse(presenter.deleteActiveSubstance(null));
        Assert.assertEquals(view.msg, "None selected to be edited");
        Assert.assertEquals(3, acMem.findAll().size());
        Assert.assertEquals(2, prMem.findAll().size());
        Assert.assertEquals(2, ppMem.findAll().size());
        Assert.assertEquals(2, pxMem.findAll().size());

        List<ActiveSubstance> l1 = acMem.findAll();
        List<Prescription> l2 = prMem.findAll();
        List<PharmaceuticalProduct> l3 = ppMem.findAll();
        List<PrescriptionExecution> l4 = pxMem.findAll();

        Assert.assertFalse(presenter.deleteActiveSubstance(acList.get(1)));
        Assert.assertEquals(view.msg, "Done!");
        Assert.assertEquals(2, acMem.findAll().size());
        Assert.assertEquals(2, prMem.findAll().size());
        Assert.assertEquals(2, ppMem.findAll().size());
        Assert.assertEquals(2, pxMem.findAll().size());

        l1 = acMem.findAll();
        l2 = prMem.findAll();
        l3 = ppMem.findAll();
        l4 = pxMem.findAll();

        Assert.assertFalse(presenter.deleteActiveSubstance(acList.get(0)));
        l1 = acMem.findAll();
        l2 = prMem.findAll();
        l3 = ppMem.findAll();
        l4 = pxMem.findAll();
        Assert.assertEquals(view.msg, "Done!");
        Assert.assertEquals(1, acMem.findAll().size());
        Assert.assertEquals(1, prMem.findAll().size());
        Assert.assertEquals(1, ppMem.findAll().size());
        Assert.assertEquals(1, pxMem.findAll().size());


        Assert.assertTrue(presenter.deleteActiveSubstance(acList.get(2)));
        l1 = acMem.findAll();
        l2 = prMem.findAll();
        l3 = ppMem.findAll();
        l4 = pxMem.findAll();
        Assert.assertEquals(view.msg, "Done!");
        Assert.assertEquals(0, acMem.findAll().size());
        Assert.assertEquals(0, prMem.findAll().size());
        Assert.assertEquals(0, ppMem.findAll().size());
        Assert.assertEquals(0, pxMem.findAll().size());
    }

}
