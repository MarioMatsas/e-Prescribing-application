package com.example.software_eng_asoee_2024.domain;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Assert;
// TODO maybe needs getters to be added to check constructor
public class NOHCS_EmployeeTest {
    @Test
    public void testId(){
        assertEquals(NOHCS_Employee.getLastEmpid(), (Integer)0);
        NOHCS_Employee emp_jordan = new NOHCS_Employee("Michael", "Jordan");
        assertEquals(emp_jordan.getEmpid(), (Integer)1);
        assertEquals(NOHCS_Employee.getLastEmpid(), (Integer)1);
        NOHCS_Employee emp_lebron = new NOHCS_Employee("LeBron", "James");
        assertEquals(emp_lebron.getEmpid(), (Integer)2);
        assertEquals(NOHCS_Employee.getLastEmpid(), (Integer)2);
    }
}
