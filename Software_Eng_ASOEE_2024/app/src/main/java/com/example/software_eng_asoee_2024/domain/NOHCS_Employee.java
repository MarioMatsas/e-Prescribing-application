package com.example.software_eng_asoee_2024.domain;/* DONE  */

import java.io.Serializable;

public class NOHCS_Employee extends Person implements Serializable {

    private static Integer LastEmpId = 0;
    private final Integer empId;

    public NOHCS_Employee(String fn, String ln) {
        super(fn, ln);
        this.empId = LastEmpId + 1;
        LastEmpId = empId;
    }

    public Integer getEmpid() {
        return this.empId;
    }

    public static Integer getLastEmpid() {
        return LastEmpId;
    }
}
