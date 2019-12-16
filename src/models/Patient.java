package models;

public class Patient {

    private int patientId;
    private String patientName, patientFamilyName, patientBirthday, patientDepartment;

    public Patient(int patientId, String patientName, String patientFamilyName, String patientBirthday, String patientDepartment) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientFamilyName = patientFamilyName;
        this.patientBirthday = patientBirthday;
        this.patientDepartment = patientDepartment;
    }

    public int getPatientIdId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String name) {
        this.patientName = name;
    }

    public String getPatientFamilyName() {
        return patientFamilyName;
    }

    public void setPatientFamilyName(String familyName) {
        this.patientFamilyName = familyName;
    }

    public String getPatientBirthday() {
        return patientBirthday;
    }

    public void setPatientBirthday(String birthday) {
        this.patientBirthday = birthday;
    }

    public String getPatientDepartment() {
        return patientDepartment;
    }

    public void setPatientDepartment(String department) {
        this.patientDepartment = department;
    }

    public void setPatientId(int id) {
        this.patientId = id;
    }
}
