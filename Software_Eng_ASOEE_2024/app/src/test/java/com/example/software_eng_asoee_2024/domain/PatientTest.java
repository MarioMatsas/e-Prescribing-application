package com.example.software_eng_asoee_2024.domain;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Assert;

public class PatientTest {

    /**
     * Για επιβεβαίωση οτι θα οριστεί σωστά ακόμη και ΑΜΚΑ του ασθενή.
     */
    @Test
    public void getSSNTest(){
        Patient person = new Patient("FirstName", "LastName", 123456789);
        Integer num = 123456789;
        Assert.assertEquals(num, person.getSSN());

        Patient p = new Patient();
        Assert.assertEquals(p.getSSN(), (Integer)0);
    }

}
