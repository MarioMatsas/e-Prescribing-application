package com.example.software_eng_asoee_2024.domain;
import org.junit.Test;
import org.junit.Assert;

public class DoctorTest {
    /**
     * Για επιβεβαίωση οτι θα οριστεί σωστά ακόμη και η ειδικότητα του γιατρού.
     */
    @Test
    public void testSpecialty(){
        Doctor doc = new Doctor("fn", "ln", "Cardiologist");
        doc.setSpecialty("Dermatologist");
        Assert.assertEquals("Dermatologist", doc.getSpecialty());
    }
}
