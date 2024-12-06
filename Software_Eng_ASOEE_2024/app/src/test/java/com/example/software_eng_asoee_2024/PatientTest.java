package com.example.software_eng_asoee_2024;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Assert;

public class PatientTest {
    
    @Test
    public void getSSNTest(){
        Patient person = new Patient("FirstName", "LastName", 123456789);
        Integer num = 123456789;
        Assert.assertEquals(num, person.getSSN());
    }

}
