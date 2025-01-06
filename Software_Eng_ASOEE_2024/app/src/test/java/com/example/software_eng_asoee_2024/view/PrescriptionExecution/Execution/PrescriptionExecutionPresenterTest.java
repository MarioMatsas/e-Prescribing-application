package com.example.software_eng_asoee_2024.view.PrescriptionExecution.Execution;

import com.example.software_eng_asoee_2024.dao.Initializer;
import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.domain.PrescriptionLine;
import com.example.software_eng_asoee_2024.domain.Unit;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.MemoryInitializer;
import com.example.software_eng_asoee_2024.memorydao.PatientDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;
import com.example.software_eng_asoee_2024.view.PrescriptionExecution.Selection.PrescriptionSelectionViewStub;
import com.example.software_eng_asoee_2024.views.PrescreptionExecution.Execution.PrescriptionExecutionPresenter;
import com.example.software_eng_asoee_2024.views.PrescreptionExecution.Selection.PrescriptionSelectionPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PrescriptionExecutionPresenterTest {
    PrescriptionExecutionViewStub viewStub;
    PrescriptionExecutionPresenter presenter;

    @Before
    public void setup(){
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        viewStub = new PrescriptionExecutionViewStub();
        presenter = new PrescriptionExecutionPresenter();
        presenter.setView(viewStub);
        presenter.setPharmaceuticalProdcutDAO(new PharmaceuticalProductDAOMemory());
        presenter.setPrescriptionDAO(new PrescriptionDAOMemory());
        presenter.init(new PrescriptionDAOMemory().findAll().get(0), new Pharmacist());
    }

    @Test
    public void noProductsToShow(){
        ActiveSubstance as = new ActiveSubstance();
        PrescriptionLine line = new PrescriptionLine(Form.PILL, new Concentration(10, Unit.mg_per_g), "For 10 days, 2 pills per day", as);
        presenter.showPrescriptionLineProducts(line);
        Assert.assertEquals(viewStub.getErrorMessage(), "No products found.");

        line = new PrescriptionLine(Form.CREAM, new Concentration(10, Unit.mg_per_g), "For 10 days, 2 pills per day", as);
        presenter.showPrescriptionLineProducts(line);
        Assert.assertEquals(viewStub.getErrorMessage(), "No products found.");
    }

    @Test
    public void productsToShow(){
        PrescriptionLine line = new PrescriptionLine(Form.PILL, new Concentration(10, Unit.mg_per_g), "For 10 days, 2 pills per day", new ActiveSubstanceDAOMemory().find("Ibuprofen"));
        presenter.showPrescriptionLineProducts(line);
        Assert.assertEquals(viewStub.getUpdateMessage(), "2");
    }

    @Test
    public void productNotFound(){
        presenter.addProductToBuy(null, "12");
        Assert.assertEquals(viewStub.getErrorMessage(), "No products.");
    }

    @Test
    public void productFound(){
        Assert.assertTrue(presenter.addProductToBuy(new PharmaceuticalProductDAOMemory().find("Brufen Plus"), "5"));
    }

    @Test
    public void emptyQuantity(){
        presenter.addProductToBuy(new PharmaceuticalProductDAOMemory().find("Brufen Plus"), "");
        Assert.assertEquals(viewStub.getErrorMessage(), "Quantity cannot be empty.");
    }

    @Test
    public void invalidQuantityFormat(){
        presenter.addProductToBuy(new PharmaceuticalProductDAOMemory().find("Brufen Plus"), "asf532");
        Assert.assertEquals(viewStub.getErrorMessage(), "Invalid quantity format.");
    }


}
