package models;

public class Prescription {
    private int prescriptionId;
    private String prescriptionName, prescriptionFamilyName, prescriptionCreationDate;

    public Prescription(int prescriptionId, String prescriptionName, String prescriptionFamilyName, String prescriptionCreationDate) {
        this.prescriptionId = prescriptionId;
        this.prescriptionName = prescriptionName;
        this.prescriptionFamilyName = prescriptionFamilyName;
        this.prescriptionCreationDate = prescriptionCreationDate;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int id) {
        this.prescriptionId = id;
    }

    public String getPrescriptionName() {
        return prescriptionName;
    }

    public void setPrescriptionName(String name) {
        this.prescriptionName = name;
    }

    public String getPrescriptionFamilyName() {
        return prescriptionFamilyName;
    }

    public void setPrescriptionFamilyName(String familyName) {
        this.prescriptionFamilyName = familyName;
    }

    public String getPrescriptionCreationDate() {
        return prescriptionCreationDate;
    }

    public void setPrescriptionCreationDate(String creationDate) {
        this.prescriptionCreationDate = creationDate;
    }
}
