package com.example.software_eng_asoee_2024.view.ActiveSubstance.Edit;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.view.ActiveSubstance.Edit.ActiveSubstanceEditViewStub;
import com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Edit.ActiveSubstanceEditPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ActiveSubstanceEditPresenterTest {
    private ActiveSubstanceEditViewStub view;
    private ActiveSubstanceEditPresenter presenter;
    private List<ActiveSubstance> acList;
    @Before
    public void setUp() {
        view = new ActiveSubstanceEditViewStub();
        presenter = new ActiveSubstanceEditPresenter();
        ActiveSubstanceDAOMemory acm = new ActiveSubstanceDAOMemory();
        acm.save(new ActiveSubstance("A", 15d));
        acm.save(new ActiveSubstance("B", 1d));
        acm.save(new ActiveSubstance("C", 31d));
        acList = acm.findAll();
        presenter.setActiveSubstanceDAO(acm);
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
     * checks if the spinner is loaded properly
     */
    @Test
    public void checkSpinnerCreation() {
        presenter.createActiveSubstanceSpinner();
        Assert.assertEquals(view.acList, acList);
    }

    /**
     * Checks if the method sets the proper message per occasion
     */
    @Test
    public void checkEdit() {
        presenter.editActiveSubstance(null, "A", "10");
        Assert.assertEquals(view.msg, "None selected to be edited");
        presenter.editActiveSubstance(acList.get(0), "", "10");
        Assert.assertEquals(view.msg, "Not all fields are filled in");
        presenter.editActiveSubstance(acList.get(0), "A", "");
        Assert.assertEquals(view.msg, "Not all fields are filled in");
        presenter.editActiveSubstance(acList.get(0), "B", "10");
        Assert.assertEquals(view.msg, "Cant have two active substances with same name!");
        presenter.editActiveSubstance(acList.get(0), "D", "Hi");
        Assert.assertEquals(view.msg, "Expected Quantity Per Month should be a number");
        presenter.editActiveSubstance(acList.get(0), "D", "200");
        Assert.assertEquals(view.msg, "Done!");
        presenter.editActiveSubstance(acList.get(1), "B", "300");
        Assert.assertEquals(view.msg, "Done!");
    }
}
