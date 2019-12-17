package models;

public class Patient {

    private String patientName, patientFamilyName, patientBirthday, patientDepartment;

    public Patient(String patientName, String patientFamilyName, String patientBirthday, String patientDepartment) {
        this.patientName = patientName;
        this.patientFamilyName = patientFamilyName;
        this.patientBirthday = patientBirthday;
        this.patientDepartment = patientDepartment;
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
}
