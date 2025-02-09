package com.example.software_eng_asoee_2024.domain;

import org.junit.*;

public class PrescriptionTest {
    static Doctor doc;
    static Patient pat;
    static Prescription presc;

    /**
     * Πριν τα τέστ, αρχικοποιούνται τα πεδία.
     */
    @Before
    public void init() {
        doc = new Doctor("John", "Doe", "Cardiology");
        pat = new Patient("Tom", "Hobs", 123123123);
        presc = new Prescription("Wolff-Parkinson-White", doc, pat);
    }

    /**
     * Για επιβεβαίωση οτι δούλεψε σωστά το init().
     */
    @Test
    public void testInit() {
        Assert.assertEquals(presc.getDate().getDay(), (new Date()).getDay());
        Assert.assertEquals(presc.getPatient(), pat);
        Assert.assertEquals(presc.getDiagnosis(), "Wolff-Parkinson-White");
        Assert.assertEquals(presc.getStatus(), Status.PENDING);
        Assert.assertEquals(presc.getDoctorInfo(), "Doctor Info: Name: " + doc.getFirstName() + " | Surname: " + doc.getLastName() + " | Specialty: " + doc.getSpecialty());
    }

    /**
     * Για επιβεβαίωση οτι δούλεψε σωστά ο κατασκευαστής.
     */
    @Test
    public void testConstructors() {
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            this.presc = new Prescription("No", null, pat);
        });
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            this.presc = new Prescription("No", doc, null);
        });
    }

    /**
     * Για επιβεβαίωση οτι ορίσθηκε σωστά η κατάσταση της συνταγής.
     */
    @Test
    public void testSetter() {
        presc.setStatus(Status.COMPLETED);
        Assert.assertEquals(presc.getStatus(), Status.COMPLETED);
    }


    /**
     * Για επιβεβαίωση οτι δουλεύει σωστά το addLine().
     */
    @Test
    public void testLines() {
        Assert.assertEquals(presc.getPrescriptionLines().size(), 0);
        PrescriptionLine line = new PrescriptionLine(Form.CREAM, new Concentration(10.0, Unit.mg_per_g), "For 10 days", new ActiveSubstance("Paracetamol", 20d));
        presc.addLine(line);
        Assert.assertEquals(presc.getPrescriptionLines().size(), 1);
        Assert.assertEquals(presc.getPrescriptionLines().get(0), line);
    }

    /**
     * Για επιβεβαίωση οτι επιστρέφεται το σωστό string.
     */
    @Test
    public void testToString(){
        PrescriptionLine line = new PrescriptionLine(Form.CREAM, new Concentration(10.0, Unit.mg_per_g), "For 10 days", new ActiveSubstance("Paracetamol", 20d));
        presc.addLine(line);
        Assert.assertEquals(presc.toString(), "John Doe: Wolff-Parkinson-White · Paracetamol , ");
    }
}