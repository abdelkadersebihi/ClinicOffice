package models;

public class Medicine {

    private int medicineId;
    private String medicineName, medicineForm, medicineDose;

    public Medicine(int medicineId, String medicineName, String medicineForm, String medicineDose) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.medicineForm = medicineForm;
        this.medicineDose = medicineDose;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int id) {
        this.medicineId = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String name) {
        this.medicineName = name;
    }

    public String getMedicineForm() {
        return medicineForm;
    }

    public void setMedicineForm(String form) {
        this.medicineForm = form;
    }

    public String getMedicineDose() {
        return medicineDose;
    }

    public void setMedicineDose(String dose) {
        this.medicineDose = dose;
    }
}
