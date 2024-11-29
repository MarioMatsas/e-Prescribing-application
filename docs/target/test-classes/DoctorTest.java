import org.junit.Test;
import org.junit.Assert;

public class DoctorTest {
    @Test
    public void testSpecialty(){
        Doctor doc = new Doctor("fn", "ln", "Cardiologist");
        doc.setSpecialty("Dermatologist");
        Assert.assertEquals("Dermatologist", doc.getSpecialty());
    }
}
