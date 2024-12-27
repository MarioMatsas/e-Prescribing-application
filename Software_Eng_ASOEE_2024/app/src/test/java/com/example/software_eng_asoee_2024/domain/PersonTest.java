package com.example.software_eng_asoee_2024.domain;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;

public class PersonTest {
    Person person;

    @Before
    public void setUp(){
        person = new Person("FirstName", "LastName");
    }

    @Test

    public void testSetGets(){
        person.setFirstName("First");
        Assert.assertEquals("First", person.getFirstName());

        person.setLastName("Last");
        Assert.assertEquals("Last", person.getLastName());
    }

}
