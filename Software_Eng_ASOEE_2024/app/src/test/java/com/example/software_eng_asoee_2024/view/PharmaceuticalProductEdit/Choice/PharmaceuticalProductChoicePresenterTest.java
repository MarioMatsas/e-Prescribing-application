package com.example.software_eng_asoee_2024.view.PharmaceuticalProductEdit.Choice;

import com.example.software_eng_asoee_2024.views.PharmaceuticalProductEdit.Choice.PharmaceuticalProductChoicePresenter;

import org.junit.*;

public class PharmaceuticalProductChoicePresenterTest {
    private PharmaceuticalProductChoicePresenter presenter;
    private PharmaceuticalProductChoiceViewStub view;
    @Before
    public void setUp() {
        view = new PharmaceuticalProductChoiceViewStub();
        presenter = new PharmaceuticalProductChoicePresenter();
    }

    @Test
    public void checkView() {
        presenter.setView(view);
        Assert.assertEquals(presenter.getView(), view);
    }
}
