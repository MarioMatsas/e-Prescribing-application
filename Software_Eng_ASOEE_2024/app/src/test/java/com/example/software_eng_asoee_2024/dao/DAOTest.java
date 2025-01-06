package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.MedicineType;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.PrescriptionLine;
import com.example.software_eng_asoee_2024.domain.Unit;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.DoctorDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.MemoryInitializer;
import com.example.software_eng_asoee_2024.memorydao.PatientDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmacistDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class DAOTest {
    private DoctorDAO doctorDAO;
    private PharmacistDAO pharmacistDAO;
    private PatientDAO patientDAO;
    private ActivaSubstanceDAO activeSubstanceDAO;
    private PharmaceuticalProductDAO productDAO;
    private PrescriptionDAO prescriptionDAO;
    private Prescription presc;

    @Before
    public void setUp() {
        Initializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();

        doctorDAO = new DoctorDAOMemory();
        pharmacistDAO = new PharmacistDAOMemory();
        patientDAO = new PatientDAOMemory();
        activeSubstanceDAO = new ActiveSubstanceDAOMemory();
        productDAO = new PharmaceuticalProductDAOMemory();
        prescriptionDAO = new PrescriptionDAOMemory();

        presc = new Prescription("Whatever...", doctorDAO.find("m", "m"), patientDAO.find(123123123));
        PrescriptionLine line = new PrescriptionLine(Form.PILL, new Concentration(15, Unit.mg_per_g), "For 5 days, 2 pills per day", activeSubstanceDAO.find("Paracetamol"));
        presc.addLine(line);
    }

    /*
    DoctorDAO tests
     */
    @Test
    public void findRegisteredDoctor(){
        Assert.assertNotNull(doctorDAO.find("m","m"));
    }

    @Test
    public void findUnregisteredDoctor(){
        Assert.assertNull(doctorDAO.find("hgj","ghgh"));
        Assert.assertNull(doctorDAO.find("m","ghgh"));
    }

    @Test
    public void findAllDoctors(){
        Assert.assertTrue(doctorDAO.findAll().contains(doctorDAO.find("m","m")));
    }

    @Test
    public void saveDoctor(){
        doctorDAO.save(new Doctor("d1", "p1", "whatever"));
        Assert.assertNotNull(doctorDAO.find("d1", "p1"));
    }

    @Test
    public void deleteDoctor(){
        doctorDAO.delete(doctorDAO.find("d1", "p1"));
        Assert.assertNull(doctorDAO.find("d1", "p1"));
    }

    /*
    PharmacistDAO tests
     */
    @Test
    public void findRegisteredPharmacist(){
        Assert.assertNotNull(pharmacistDAO.find("a", "p"));
    }

    @Test
    public void findUnregisteredPharmacist(){
        Assert.assertNull(pharmacistDAO.find("hgj","ghgh"));
        Assert.assertNull(pharmacistDAO.find("a","ghgh"));
    }

    @Test
    public void findAllPharmacists(){
        Assert.assertTrue(pharmacistDAO.findAll().contains(pharmacistDAO.find("a", "p")));
    }

    @Test
    public void savePharmacist(){
        pharmacistDAO.save(new Pharmacist("d1", "p1"));
        Assert.assertNotNull(pharmacistDAO.find("d1", "p1"));
    }

    @Test
    public void deletePharmacist(){
        pharmacistDAO.delete(pharmacistDAO.find("d1", "p1"));
        Assert.assertNull(pharmacistDAO.find("d1", "p1"));
    }

    /*
    PatientDAO tests
     */
    @Test
    public void findRegisteredPatient(){
        Assert.assertNotNull(patientDAO.find(123123123));
    }

    @Test
    public void findUnregisteredPatient(){
        Assert.assertNull(patientDAO.find(123123125));
    }

    @Test
    public void findAllPatients(){
        Assert.assertTrue(patientDAO.findAll().contains(patientDAO.find(123123123)));
    }

    @Test
    public void savePatient(){
        patientDAO.save(new Patient("super","mario",123123125));
        Assert.assertNotNull(patientDAO.find(123123125));
    }

    @Test
    public void deletePatient(){
        patientDAO.delete(patientDAO.find(123123125));
        Assert.assertNull(patientDAO.find(123123125));
    }

    /*
    ActiveSubstanceDAO tests
     */
    @Test
    public void findRegisteredActiveSubstance(){
        Assert.assertNotNull(activeSubstanceDAO.find("Paracetamol"));
    }

    @Test
    public void findUnregisteredActiveSubstance(){
        Assert.assertNull(activeSubstanceDAO.find("Paracetamol_FAKE"));
    }

    @Test
    public void findAllActiveSubstances(){
        Assert.assertTrue(activeSubstanceDAO.findAll().contains(activeSubstanceDAO.find("Ibuprofen")));
    }

    @Test
    public void saveActiveSubstance(){
        activeSubstanceDAO.save(new ActiveSubstance("SuperJuice", 30d));
        Assert.assertNotNull(activeSubstanceDAO.find("SuperJuice"));
    }

    @Test
    public void deleteActiveSubstance(){
        activeSubstanceDAO.delete(activeSubstanceDAO.find("SuperJuice"));
        Assert.assertNull(activeSubstanceDAO.find("SuperJuice"));
    }

    /*
    PharmaceuticalProductDAO tests
     */
    @Test
    public void findRegisteredPharmaceuticalProduct(){
        Assert.assertNotNull(productDAO.find("Brufen Plus"));
    }

    @Test
    public void findUnregisteredPharmaceuticalProduct(){
        Assert.assertNull(productDAO.find("Brufen Plus_FAKE"));
    }

    @Test
    public void findAllPharmaceuticalProducts(){
        Assert.assertTrue(productDAO.findAll().contains(productDAO.find("Advil")));
    }

    @Test
    public void savePharmaceuticalProduct(){
        ArrayList<ActiveSubstance> as = new ArrayList<ActiveSubstance>();
        as.add(activeSubstanceDAO.find("Paracetamol"));
        productDAO.save(new PharmaceuticalProduct("Product1", 600, Form.PILL, MedicineType.GENERIC, as, "8 pills in pack"));
        Assert.assertNotNull(productDAO.find("Product1"));
    }

    @Test
    public void deletePharmaceuticalProduct(){
        productDAO.delete(productDAO.find("Product1"));
        Assert.assertNull(productDAO.find("Product1"));
    }

    /*
    PrescriptionProductDAO tests
     */
    @Test
    public void findRegisteredPrescription(){
        Assert.assertEquals((prescriptionDAO.findPrescriptionByPatient(patientDAO.find(123123123))).size(), 1);
    }

    @Test
    public void findPrescriptionById(){
        Assert.assertEquals((prescriptionDAO.findPrescriptionByPatient(patientDAO.find(123123123))).get(0), prescriptionDAO.findPrescriptionById(prescriptionDAO.findPrescriptionByPatient(patientDAO.find(123123123)).get(0).getId()));
    }

    @Test
    public void findUnregisteredPrescription(){
        Assert.assertEquals((prescriptionDAO.findPrescriptionByPatient(patientDAO.find(123123125))).size(), 0);
    }

    @Test
    public void findAllPrescriptions(){
        Assert.assertEquals(prescriptionDAO.findAll(), prescriptionDAO.findPrescriptionByPatient(patientDAO.find(123123123)));
    }

    @Test
    public void savePrescription(){
        prescriptionDAO.save(presc);
        Assert.assertEquals((prescriptionDAO.findPrescriptionByPatient(patientDAO.find(123123123))).size(), 2);
    }

    @Test
    public void deletePrescription(){
        prescriptionDAO.delete(presc);
        Assert.assertEquals((prescriptionDAO.findPrescriptionByPatient(patientDAO.find(123123123))).size(), 1);
    }

}
