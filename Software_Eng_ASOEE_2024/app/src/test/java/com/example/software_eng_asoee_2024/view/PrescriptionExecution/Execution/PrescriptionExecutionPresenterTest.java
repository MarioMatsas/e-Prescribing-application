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
import com.example.software_eng_asoee_2024.memorydao.PharmacistDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionExecutionDAOMemory;
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
        presenter.setPharmacistDAO(new PharmacistDAOMemory());
        presenter.setPrescriptionExecutionDAO(new PrescriptionExecutionDAOMemory());
        presenter.init(new PrescriptionDAOMemory().findAll().get(0), "d", "ch");
    }

    /**
     * Test view getter
     *
     */
    @Test
    public void getView(){
        Assert.assertEquals(viewStub, presenter.getView());
    }

    /**
     *
     * No products found that match the active substance, form and concentration in the line
     */
    @Test
    public void noProductsToShow(){
        ActiveSubstance as = new ActiveSubstance("HALLO!", 10.3);
        PrescriptionLine line = new PrescriptionLine(Form.PILL, new Concentration(10.0, Unit.mg_per_g), "For 10 days, 2 pills per day", as);
        presenter.showPrescriptionLineProducts(line);
        Assert.assertEquals(viewStub.getErrorMessage(), "No products found.");

        line = new PrescriptionLine(Form.CREAM, new Concentration(10.0, Unit.mg_per_g), "For 10 days, 2 pills per day", as);
        presenter.showPrescriptionLineProducts(line);
        Assert.assertEquals(viewStub.getErrorMessage(), "No products found.");
    }

    /**
     *
     * Check if the correct number of products is shown
     */
    @Test
    public void productsToShow(){
        PrescriptionLine line = new PrescriptionLine(Form.PILL, new Concentration(40.0, Unit.mg_per_disk), "For 10 days, 2 pills per day", new ActiveSubstanceDAOMemory().find("Ibuprofen"));
        presenter.showPrescriptionLineProducts(line);
        Assert.assertEquals(viewStub.getUpdateMessage(), "1");
    }

    /**
     *
     * Makes sure that no products were found that match the line info
     */
    @Test
    public void productNotFound(){
        presenter.addProductToBuy(null, "12");
        Assert.assertEquals(viewStub.getErrorMessage(), "No products.");
    }

    /**
     *
     * Makes sure that a product was found that match the line info
     */
    @Test
    public void productFound(){
        Assert.assertTrue(presenter.addProductToBuy(new PharmaceuticalProductDAOMemory().find("Brufen Plus"), "5"));
    }

    /**
     *
     * Check non positive quantity
     */
    @Test
    public void negQuantity(){
        presenter.addProductToBuy(new PharmaceuticalProductDAOMemory().find("Brufen Plus"), "-6");
        Assert.assertEquals(viewStub.getErrorMessage(), "Quantity must be positive.");
        boolean f = presenter.addProductToBuy(new PharmaceuticalProductDAOMemory().find("Brufen Plus"), "0");
        Assert.assertFalse(f);
        Assert.assertEquals(viewStub.getErrorMessage(), "Quantity must be positive.");
    }

    /**
     *
     * Checks for empty quantity field
     */
    @Test
    public void emptyQuantity(){
        presenter.addProductToBuy(new PharmaceuticalProductDAOMemory().find("Brufen Plus"), "");
        Assert.assertEquals(viewStub.getErrorMessage(), "Quantity cannot be empty.");
    }

    /**
     *
     * Checks for invalid quantity format
     */
    @Test
    public void invalidQuantityFormat(){
        presenter.addProductToBuy(new PharmaceuticalProductDAOMemory().find("Brufen Plus"), "asf532");
        Assert.assertEquals(viewStub.getErrorMessage(), "Invalid quantity format.");
    }

    /**
     *
     * Check if the execution finishes the way it should
     */
    @Test
    public void finishTest(){
        presenter.addProductToBuy(new PharmaceuticalProductDAOMemory().find("Brufen Plus"), "3");
        Integer i = new PrescriptionDAOMemory().findAll().get(0).getId();
        String amount = presenter.finishExecution(presenter.getPrescription(i));
        Assert.assertNull(new PrescriptionDAOMemory().findPrescriptionById(i));
        Assert.assertNotNull(new PrescriptionExecutionDAOMemory().findByPrescriptionId(i));
        Assert.assertEquals(amount, "3.6");
    }


}
