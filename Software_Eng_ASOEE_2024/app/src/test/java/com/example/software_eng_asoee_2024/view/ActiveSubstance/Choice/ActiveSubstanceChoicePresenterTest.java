package com.example.software_eng_asoee_2024.view.ActiveSubstance.Choice;

import com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Choice.ActiveSubstanceChoicePresenter;
import com.example.software_eng_asoee_2024.views.ActiveSubstanceEdit.Choice.ActiveSubstanceChoiceView;

import org.junit.*;

public class ActiveSubstanceChoicePresenterTest {
    private ActiveSubstanceChoicePresenter presenter;
    private ActiveSubstanceChoiceView view;
    @Before
    public void setUp() {
        view = new ActiveSubstanceChoiceViewStub();
        presenter = new ActiveSubstanceChoicePresenter();
    }

    @Test
    public void checkView() {
        presenter.setView(view);
        Assert.assertEquals(presenter.getView(), view);
    }
}
