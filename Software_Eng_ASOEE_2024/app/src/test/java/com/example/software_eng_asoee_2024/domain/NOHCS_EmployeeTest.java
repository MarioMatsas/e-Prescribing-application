package com.example.software_eng_asoee_2024.domain;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Assert;
// TODO maybe needs getters to be added to check constructor
public class NOHCS_EmployeeTest {
    @Test
    public void testId(){
        Integer last = NOHCS_Employee.getLastEmpid();
        NOHCS_Employee emp_jordan = new NOHCS_Employee("Michael", "Jordan");
        assertEquals((Integer)(last+1), emp_jordan.getEmpid());
        assertEquals((Integer)(last+1), NOHCS_Employee.getLastEmpid());
        NOHCS_Employee emp_lebron = new NOHCS_Employee("LeBron", "James");
        assertEquals((Integer)(last+2), emp_lebron.getEmpid());
        assertEquals((Integer)(last+2), NOHCS_Employee.getLastEmpid());
    }
}
