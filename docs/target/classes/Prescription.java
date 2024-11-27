import java.util.ArrayList;
import java.util.List;

public class Prescription {
    private List<PrescriptionLine> prescriptionLines;
    public final String diagnosis; // TODO change visability in diagram
    private Status status;
    public final Date date;

    public Prescription(String diagnosis, Status status, Date date) {
        this.diagnosis = diagnosis;
        this.status = status;
        this.date = date;
        this.prescriptionLines = new ArrayList<>();
    }

    public String getDiagnosis() {
        return this.diagnosis;
    }


    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return this.date;
    }

    public void addLine(PrescriptionLine line) {
        this.prescriptionLines.add(line);
    }

    /*Get doctor info */
}
