package com.example.software_eng_asoee_2024.dao;

import com.example.software_eng_asoee_2024.domain.ActiveSubstance;
import com.example.software_eng_asoee_2024.domain.Concentration;
import com.example.software_eng_asoee_2024.domain.Date;
import com.example.software_eng_asoee_2024.domain.Doctor;
import com.example.software_eng_asoee_2024.domain.Form;
import com.example.software_eng_asoee_2024.domain.MedicineType;
import com.example.software_eng_asoee_2024.domain.Patient;
import com.example.software_eng_asoee_2024.domain.Pharmacist;
import com.example.software_eng_asoee_2024.domain.PharmaceuticalProduct;
import com.example.software_eng_asoee_2024.domain.Prescription;
import com.example.software_eng_asoee_2024.domain.PrescriptionExecution;
import com.example.software_eng_asoee_2024.domain.PrescriptionLine;
import com.example.software_eng_asoee_2024.domain.Unit;
import com.example.software_eng_asoee_2024.memorydao.ActiveSubstanceDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.DoctorDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.MemoryInitializer;
import com.example.software_eng_asoee_2024.memorydao.PatientDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmacistDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PharmaceuticalProductDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.PrescriptionExecutionDAOMemory;
import com.example.software_eng_asoee_2024.memorydao.ReportObjectDAOMemory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DAOTest {
    private DoctorDAO doctorDAO;
    private PharmacistDAO pharmacistDAO;
    private PatientDAO patientDAO;
    private ActiveSubstanceDAO activeSubstanceDAO;
    private PharmaceuticalProductDAO productDAO;
    private PrescriptionDAO prescriptionDAO;
    private Prescription presc;
    private ReportObjectDAO reportDAO;
    private PrescriptionExecutionDAO prescriptionExecutionDAO;

    /**
     * Πρίν απο τα τεστ, δημιουργεί και αρχικοποιεί την μνήμη.
     */
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
        reportDAO = new ReportObjectDAOMemory();
        prescriptionExecutionDAO = new PrescriptionExecutionDAOMemory();


        presc = new Prescription("Whatever...", doctorDAO.find("m", "m"), patientDAO.find(123123123));
        PrescriptionLine line = new PrescriptionLine(Form.PILL, new Concentration(15.0, Unit.mg_per_g), "For 5 days, 2 pills per day", activeSubstanceDAO.find("Paracetamol"));
        presc.addLine(line);

    }

    /*
    DoctorDAO tests
     */
    /**
     * Για επιβεβαίωση οτι δεν θα επιστρέψει null, αν ψάξει υπάρχον γιατρό.
     */
    @Test
    public void findRegisteredDoctor(){
        Assert.assertNotNull(doctorDAO.find("m","m"));
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει null, αν ψάξει μη-υπάρχον γιατρό.
     */
    @Test
    public void findUnregisteredDoctor(){
        Assert.assertNull(doctorDAO.find("hgj","ghgh"));
        Assert.assertNull(doctorDAO.find("m","ghgh"));
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει true, αν επιστρέψει σωστή λίστα γιατρών.
     * (Επιβεβαιώνει με έλεγχο ύπαρξης συγκεκριμένου γιατρού,
     * που κανονικά πρέπει να υπάρχει στην λίστα)
     */
    @Test
    public void findAllDoctors(){
        Assert.assertTrue(doctorDAO.findAll().contains(doctorDAO.find("m","m")));
    }

    /**
     * Για επιβεβαίωση οτι θα αποθηκευτεί σωστά ο γιατρός.
     */
    @Test
    public void saveDoctor(){
        doctorDAO.save(new Doctor("d1", "p1", "whatever"));
        Assert.assertNotNull(doctorDAO.find("d1", "p1"));
    }

    /**
     * Για επιβεβαίωση οτι θα διαγραφεί σωστά ο γιατρός.
     */
    @Test
    public void deleteDoctor(){
        doctorDAO.delete(doctorDAO.find("d1", "p1"));
        Assert.assertNull(doctorDAO.find("d1", "p1"));
    }

    /*
    PharmacistDAO tests
     */
    /**
     * Για επιβεβαίωση οτι δεν θα επιστρέψει null, αν ψάξει υπάρχον φαρμακοποιό.
     */
    @Test
    public void findRegisteredPharmacist(){
        Assert.assertNotNull(pharmacistDAO.find("a", "p"));
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει null, αν ψάξει μη-υπάρχον φαρμακοποιό.
     */
    @Test
    public void findUnregisteredPharmacist(){
        Assert.assertNull(pharmacistDAO.find("hgj","ghgh"));
        Assert.assertNull(pharmacistDAO.find("a","ghgh"));
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει true, αν επιστρέψει σωστή λίστα φαρμακοποιών.
     * (Επιβεβαιώνει με έλεγχο ύπαρξης συγκεκριμένου φαρμακοποιού,
     * που κανονικά πρέπει να υπάρχει στην λίστα)
     */
    @Test
    public void findAllPharmacists(){
        Assert.assertTrue(pharmacistDAO.findAll().contains(pharmacistDAO.find("a", "p")));
    }

    /**
     * Για επιβεβαίωση οτι θα αποθηκευτεί σωστά ο φαρμακοποιός.
     */
    @Test
    public void savePharmacist(){
        pharmacistDAO.save(new Pharmacist("d1", "p1"));
        Assert.assertNotNull(pharmacistDAO.find("d1", "p1"));
    }

    /**
     * Για επιβεβαίωση οτι θα διαγραφεί σωστά ο φαρμακοποιός.
     */
    @Test
    public void deletePharmacist(){
        pharmacistDAO.delete(pharmacistDAO.find("d1", "p1"));
        Assert.assertNull(pharmacistDAO.find("d1", "p1"));
    }

    /*
    PatientDAO tests
     */
    /**
     * Για επιβεβαίωση οτι δεν θα επιστρέψει null, αν ψάξει υπάρχον ασθενή.
     */
    @Test
    public void findRegisteredPatient(){
        Assert.assertNotNull(patientDAO.find(123123123));
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει null, αν ψάξει μη-υπάρχον ασθενή.
     */
    @Test
    public void findUnregisteredPatient(){
        Assert.assertNull(patientDAO.find(123123125));
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει true, αν επιστρέψει σωστή λίστα ασθενών.
     * (Επιβεβαιώνει με έλεγχο ύπαρξης συγκεκριμένου ασθενή,
     * που κανονικά πρέπει να υπάρχει στην λίστα)
     */
    @Test
    public void findAllPatients(){
        Assert.assertTrue(patientDAO.findAll().contains(patientDAO.find(123123123)));
    }

    /**
     * Για επιβεβαίωση οτι θα αποθηκευτεί σωστά ο ασθενής.
     */
    @Test
    public void savePatient(){
        patientDAO.save(new Patient("super","mario",123123125));
        Assert.assertNotNull(patientDAO.find(123123125));
    }

    /**
     * Για επιβεβαίωση οτι θα διαγραφεί σωστά ο ασθενής.
     */
    @Test
    public void deletePatient(){
        patientDAO.delete(patientDAO.find(123123125));
        Assert.assertNull(patientDAO.find(123123125));
    }

    /*
    ActiveSubstanceDAO tests
     */
    /**
     * Για επιβεβαίωση οτι δεν θα επιστρέψει null, αν ψάξει υπάρχουσα ουσία.
     */
    @Test
    public void findRegisteredActiveSubstance(){
        Assert.assertNotNull(activeSubstanceDAO.find("Paracetamol"));
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει null, αν ψάξει μη-υπάρχουσα ουσία.
     */
    @Test
    public void findUnregisteredActiveSubstance(){
        Assert.assertNull(activeSubstanceDAO.find("Paracetamol_FAKE"));
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει true, αν επιστρέψει σωστή λίστα ουσιών.
     * (Επιβεβαιώνει με έλεγχο ύπαρξης συγκεκριμένης ουσίας,
     * που κανονικά πρέπει να υπάρχει στην λίστα)
     */
    @Test
    public void findAllActiveSubstances(){
        Assert.assertTrue(activeSubstanceDAO.findAll().contains(activeSubstanceDAO.find("Ibuprofen")));
    }

    /**
     * Για επιβεβαίωση οτι θα αποθηκευτεί σωστά η ουσία.
     */
    @Test
    public void saveActiveSubstance(){
        activeSubstanceDAO.save(new ActiveSubstance("SuperJuice", 30d));
        Assert.assertNotNull(activeSubstanceDAO.find("SuperJuice"));
    }

    /**
     * Για επιβεβαίωση οτι θα διαγραφεί σωστά η ουσία.
     */
    @Test
    public void deleteActiveSubstance(){
        activeSubstanceDAO.delete(activeSubstanceDAO.find("SuperJuice"));
        Assert.assertNull(activeSubstanceDAO.find("SuperJuice"));
    }

    /*
    PharmaceuticalProductDAO tests
     */
    /**
     * Για επιβεβαίωση οτι δεν θα επιστρέψει null, αν ψάξει υπάρχον προϊόν.
     */
    @Test
    public void findRegisteredPharmaceuticalProduct(){
        Assert.assertNotNull(productDAO.find("Brufen Plus"));
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει null, αν ψάξει μη-υπάρχον προϊόν.
     */
    @Test
    public void findUnregisteredPharmaceuticalProduct(){
        Assert.assertNull(productDAO.find("Brufen Plus_FAKE"));
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει true, αν επιστρέψει σωστή λίστα προϊόντων.
     * (Επιβεβαιώνει με έλεγχο ύπαρξης συγκεκριμένου προϊόντος,
     * που κανονικά πρέπει να υπάρχει στην λίστα)
     */
    @Test
    public void findAllPharmaceuticalProducts(){
        Assert.assertTrue(productDAO.findAll().contains(productDAO.find("Advil")));
    }

    /**
     * Για επιβεβαίωση οτι θα αποθηκευτεί σωστά το προϊόν.
     */
    @Test
    public void savePharmaceuticalProduct(){
        ArrayList<ActiveSubstance> as = new ArrayList<ActiveSubstance>();
        as.add(activeSubstanceDAO.find("Paracetamol"));
        List<Concentration> conc_list = new ArrayList<Concentration>();
        conc_list.add(new Concentration(3.2, Unit.mg_per_disk));
        productDAO.save(new PharmaceuticalProduct("Product1", 600, Form.PILL, MedicineType.GENERIC, as, conc_list,"8 pills in pack"));
        Assert.assertNotNull(productDAO.find("Product1"));
    }

    /**
     * Για επιβεβαίωση οτι θα διαγραφεί σωστά το προϊόν.
     */
    @Test
    public void deletePharmaceuticalProduct(){
        productDAO.delete(productDAO.find("Product1"));
        Assert.assertNull(productDAO.find("Product1"));
    }

    /*
    PrescriptionProductDAO tests
     */
    /**
     * Για επιβεβαίωση οτι δεν θα επιστρέψει null, αν ψάξει υπάρχουσα συνταγή.
     */
    @Test
    public void findRegisteredPrescription(){
        Assert.assertEquals((prescriptionDAO.findPrescriptionByPatient(patientDAO.find(123123123))).size(), 2);
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει την σωστή συνταγή βάσει id.
     */
    @Test
    public void findPrescriptionById(){
        Assert.assertEquals((prescriptionDAO.findPrescriptionByPatient(patientDAO.find(123123123))).get(0), prescriptionDAO.findPrescriptionById(prescriptionDAO.findPrescriptionByPatient(patientDAO.find(123123123)).get(0).getId()));
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει null, αν ψάξει μη-υπάρχουσα συνταγή.
     */
    @Test
    public void findUnregisteredPrescription(){
        Assert.assertEquals((prescriptionDAO.findPrescriptionByPatient(patientDAO.find(123123125))).size(), 0);
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει true, αν επιστρέψει σωστή λίστα συνταγών.
     * (Επιβεβαιώνει με έλεγχο ύπαρξης συγκεκριμένης συνταγής,
     * που κανονικά πρέπει να υπάρχει στην λίστα)
     */
    @Test
    public void findAllPrescriptions(){
        Assert.assertEquals(prescriptionDAO.findAll(), prescriptionDAO.findPrescriptionByPatient(patientDAO.find(123123123)));
    }

    /**
     * Για επιβεβαίωση οτι θα αποθηκευτεί σωστά η συνταγή.
     */
    @Test
    public void savePrescription(){
        prescriptionDAO.save(presc);
        Assert.assertEquals((prescriptionDAO.findPrescriptionByPatient(patientDAO.find(123123123))).size(), 3);
    }

    /**
     * Για επιβεβαίωση οτι θα διαγραφεί σωστά η συνταγή.
     */
    @Test
    public void deletePrescription(){
        prescriptionDAO.save(presc);
        prescriptionDAO.delete(presc);
        Assert.assertEquals((prescriptionDAO.findPrescriptionByPatient(patientDAO.find(123123123))).size(), 2);
    }

    /*
    ReportObjectDAO tests
    */
    /**
     * Για επιβεβαίωση οτι θα επιστρέψει τον σωστό μήνα.
     */
    @Test
    public void clearDataMonth(){
        reportDAO.update(new Doctor("name", "sur", "whatever"), patientDAO.find(123123123), activeSubstanceDAO.find("Paracetamol"), new Date(reportDAO.getYear(), 7,3), 123.0);
        Assert.assertEquals(reportDAO.getMonth(), (Integer)7);
    }

    /**
     * Για επιβεβαίωση οτι θα επιστρέψει τον σωστό χρόνο.
     */
    @Test
    public void clearDataYear(){
        reportDAO.update(new Doctor("name", "sur", "whatever"), patientDAO.find(123123123), activeSubstanceDAO.find("Paracetamol"), new Date(5000, reportDAO.getMonth(), 3), 123.0);
        Assert.assertEquals(reportDAO.getYear(), (Integer)5000);
    }

    /**
     * Για επιβεβαίωση οτι θα προστεθεί νέα τριάδα στο map (διαφορετικός γιατρός).
     */
    @Test
    public void differentDoctor(){
        reportDAO.update(new Doctor("name", "sur", "whatever"), patientDAO.find(123123123), activeSubstanceDAO.find("Paracetamol"), new Date(), 0.5);
        Assert.assertEquals(reportDAO.getMap().size(), 3);
    }

    /**
     * Για επιβεβαίωση οτι θα προστεθεί νέα τριάδα στο map (διαφορετικός ασθενής).
     */
    @Test
    public void differentPatient(){
        reportDAO.update(doctorDAO.find("m", "m"), new Patient("name", "sur", 777), activeSubstanceDAO.find("Paracetamol"), new Date(), 0.5);
        Assert.assertEquals(reportDAO.getMap().size(), 3);
    }

    /**
     * Για επιβεβαίωση οτι θα προστεθεί νέα τριάδα στο map (διαφορετική ουσία).
     */
    @Test
    public void differentActiveSub(){
        reportDAO.update(doctorDAO.find("m", "m"), patientDAO.find(123123123), new ActiveSubstance("new", 20.0), new Date(), 0.5);
        Assert.assertEquals(reportDAO.getMap().size(), 3);
    }

    /**
     * Για επιβεβαίωση οτι απλά γίνεται ανανέωση συγκεκριμένης τριάδας στο map.
     */
    @Test
    public void sameTrupleValues(){
        reportDAO.update(doctorDAO.find("m", "m"), patientDAO.find(123123123), activeSubstanceDAO.find("Paracetamol"), new Date(), 0.5);
        Assert.assertEquals(reportDAO.getMap().size(), 2);
    }

    /**
     * Για επιβεβαίωση οτι απλά γίνεται ανανέωση συγκεκριμένης τριάδας στο map.
     */
    @Test
    public void oldUnlawful(){
        reportDAO.update(doctorDAO.find("m", "m"), patientDAO.find(123123123), activeSubstanceDAO.find("Paracetamol"), new Date(), 10.0);
        reportDAO.update(doctorDAO.find("m", "m"), patientDAO.find(123123123), activeSubstanceDAO.find("Paracetamol"), new Date(), 1.0);
        Assert.assertEquals(reportDAO.getUnlawfulDoctors().size(), 1);
        reportDAO.update(doctorDAO.find("m", "m"), patientDAO.find(123123123), activeSubstanceDAO.find("Ibuprofen"), new Date(), 7.0);
        Assert.assertEquals(reportDAO.getUnlawfulDoctors().size(), 1);
    }

    /**
     * Για επιβεβαίωση οτι απλά γίνεται ανανέωση στο map, με νέα τριάδα.
     */
    @Test
    public void newUnlawful(){
        reportDAO.update(doctorDAO.find("m", "m"), patientDAO.find(123123123), activeSubstanceDAO.find("Paracetamol"), new Date(), 10.0);
        reportDAO.update(new Doctor(), patientDAO.find(123123123), activeSubstanceDAO.find("Paracetamol"), new Date(), 30.0);
        Assert.assertEquals(reportDAO.getUnlawfulDoctors().size(), 2);
    }

    /**
     * Για επιβεβαίωση οτι για μη-παράνομη τιμή, δεν υπάρχει στην λίστα ο γιατρός (όταν περάσει ο μήνας).
     */
    @Test
    public void noUnlawful(){
        reportDAO.update(doctorDAO.find("m", "m"), patientDAO.find(123123123), activeSubstanceDAO.find("Paracetamol"), new Date(), 0.5);
        Assert.assertEquals(reportDAO.getUnlawfulDoctors().size(), 0);
    }

    /**
     *  Make sure that ExecutionObjects are saved and deleted correctly
     */
    @Test
    public void testSaveDelete(){
        PrescriptionExecution pexe = new PrescriptionExecution(pharmacistDAO.find("d","ch"), presc);
        prescriptionExecutionDAO.save(pexe);
        Assert.assertEquals(prescriptionExecutionDAO.findAll().size(), 1);
        prescriptionExecutionDAO.delete(pexe);
        Assert.assertEquals(prescriptionExecutionDAO.findAll().size(), 0);
    }

    /**
     *  Check if objects are found by prescription id
     */
    @Test
    public void testPrescExeById(){
        PrescriptionExecution pexe = new PrescriptionExecution(pharmacistDAO.find("d","ch"), presc);
        prescriptionExecutionDAO.save(pexe);
        Assert.assertEquals(pexe, prescriptionExecutionDAO.findByPrescriptionId(pexe.getPrescription().getId()));
        Assert.assertNull(prescriptionExecutionDAO.findByPrescriptionId(20));
    }

}