package com.example.software_eng_asoee_2024.view.NOHCS;

import com.example.software_eng_asoee_2024.dao.Initializer;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.MemoryInitializer;
import com.example.software_eng_asoee_2024.views.NOHCS.NOHCSSelectionPresenter;
import com.example.software_eng_asoee_2024.views.NOHCS.NOHCSSelectionView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NOHCSSelectionPresenterTest {
    NOHCSSelectionView viewStub;

    NOHCSSelectionPresenter presenter;

    @Before
    public void setup(){
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        viewStub = new NOHCSSelectionViewStub();
        presenter = new NOHCSSelectionPresenter();
        presenter.setView(viewStub);
    }

    /**
     * Test view getter
     *
     */
    @Test
    public void getView(){
        Assert.assertEquals(viewStub, presenter.getView());
    }
}
