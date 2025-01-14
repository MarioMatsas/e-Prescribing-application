package com.example.software_eng_asoee_2024.view.ActiveSubstance.Deletion;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.MedicineType;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.PrescriptionLine;
import com.example.software_eng_asoee_2024.domain.Unit;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;
import com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Deletion.ActiveSubstanceDeletePresenter;

import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class ActiveSubstanceDeletePresenterTest {
    private ActiveSubstanceDeleteViewStub view;
    private ActiveSubstanceDeletePresenter presenter;
    private List<ActiveSubstance> acList;
    private ActiveSubstanceDAOMemory acMem;
    private PrescriptionDAOMemory prMem;
    private PharmaceuticalProductDAOMemory ppMem;
    @Before
    public void setUp() {
        view = new ActiveSubstanceDeleteViewStub();
        presenter = new ActiveSubstanceDeletePresenter();
        acMem = new ActiveSubstanceDAOMemory();
        acMem.save(new ActiveSubstance("A", 15d));
        acMem.save(new ActiveSubstance("B", 1d));
        acMem.save(new ActiveSubstance("C", 31d));
        acList = acMem.findAll();
        presenter.setActiveSubstanceDAO(acMem);
        prMem = new PrescriptionDAOMemory();

        Prescription tp = new Prescription("AB", new Doctor("A", "A", "A"), new Patient("B", "B", 123123123));
        tp.addLine(new PrescriptionLine(Form.PILL, new Concentration(15d, Unit.mg_per_disk), "na", acList.get(0)));
        prMem.save(tp);
        tp = new Prescription("BA", new Doctor("B", "B", "B"), new Patient("V", "V", 321321321));
        tp.addLine(new PrescriptionLine(Form.PILL, new Concentration(15d, Unit.mg_per_disk), "na", acList.get(0)));
        tp.addLine(new PrescriptionLine(Form.PILL, new Concentration(15d, Unit.mg_per_disk), "na", acList.get(2)));
        prMem.save(tp);
        presenter.setPrescriptionDAO(prMem);

        ppMem = new PharmaceuticalProductDAOMemory();
        ArrayList<ActiveSubstance> tac = new ArrayList<ActiveSubstance>();
        tac.add(acList.get(0));
        ArrayList<Concentration> acc = new ArrayList<Concentration>();
        acc.add(new Concentration(15d, Unit.mg_per_disk));
        ppMem.save(new PharmaceuticalProduct("Prod", 10, Form.PILL, MedicineType.ORIGINAL, (ArrayList<ActiveSubstance>) tac.clone(), (ArrayList<Concentration>) acc.clone(), ""));
        tac.add(acList.get(2));
        acc.add(new Concentration(15d, Unit.mg_per_disk));
        ppMem.save(new PharmaceuticalProduct("Pro2", 10, Form.PILL, MedicineType.GENERIC, tac, acc, ""));
        presenter.setPharmaceuticalProductDAO(ppMem);

        presenter.setView(view);
    }

    @Test
    public void checkView() {
        presenter.setView(view);
        Assert.assertEquals(presenter.getView(), view);
    }

    @Test
    public void checkSpinnerCreation() {
        presenter.createActiveSubstanceSpinner();
        Assert.assertEquals(view.acList, acList);
    }

    @Test
    public void checkDeletion() {
        Assert.assertFalse(presenter.deleteActiveSubstance(null));
        Assert.assertEquals(view.msg, "None selected to be edited");
        Assert.assertEquals(3, acMem.findAll().size());
        Assert.assertEquals(2, prMem.findAll().size());
        Assert.assertEquals(2, ppMem.findAll().size());

        Assert.assertFalse(presenter.deleteActiveSubstance(acList.get(1)));
        Assert.assertEquals(view.msg, "Done!");
        Assert.assertEquals(2, acMem.findAll().size());
        Assert.assertEquals(2, prMem.findAll().size());
        Assert.assertEquals(2, ppMem.findAll().size());

        Assert.assertFalse(presenter.deleteActiveSubstance(acList.get(0)));
        Assert.assertEquals(view.msg, "Done!");
        Assert.assertEquals(1, acMem.findAll().size());
        Assert.assertEquals(1, prMem.findAll().size());
        Assert.assertEquals(1, ppMem.findAll().size());


        Assert.assertTrue(presenter.deleteActiveSubstance(acList.get(2)));
        Assert.assertEquals(view.msg, "Done!");
        Assert.assertEquals(0, acMem.findAll().size());
        Assert.assertEquals(0, prMem.findAll().size());
        Assert.assertEquals(0, ppMem.findAll().size());
    }

}
