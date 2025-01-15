package com.example.software_eng_asoee_2024.view.ActiveSubstance.Creation;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Creation.ActiveSubstanceCreationPresenter;
import com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Creation.ActiveSubstanceCreationView;

import org.junit.*;

public class ActiveSubstanceCreatePresenterTest {
    private ActiveSubstanceCreateViewStub view;
    private ActiveSubstanceCreationPresenter presenter;
    @Before
    public void setUp() {
        view = new ActiveSubstanceCreateViewStub();
        presenter = new ActiveSubstanceCreationPresenter();
        ActiveSubstanceDAOMemory acMem = new ActiveSubstanceDAOMemory();

        for(ActiveSubstance ac : acMem.findAll())
            acMem.delete(ac);

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
        presenter.createActiveSubstance("A", "10");
        Assert.assertEquals(view.msg, "Done!");
        presenter.createActiveSubstance("A", "15");
        Assert.assertEquals(view.msg, "Cant have two active substances with same name!");
        presenter.createActiveSubstance("B", "Hi");
        Assert.assertEquals(view.msg, "Expected Quantity Per Month should be a number");
        presenter.createActiveSubstance("B", "30");
        Assert.assertEquals(view.msg, "Done!");
    }
}
