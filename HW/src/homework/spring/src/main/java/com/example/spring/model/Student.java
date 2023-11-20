package com.example.spring.model;

public class Student extends Member{

    private String clazz;
    private String admissionYearMonth;

    public Student(String id, String name, String gender, String clazz, String admissionYearMonth) {
        super(id, name, gender);
        this.clazz = clazz;
        this.admissionYearMonth = admissionYearMonth;
    }

    @Override
    public String toString() {
        return "Student{" +
                "clazz='" + clazz + '\'' +
                ", admissionYearMonth='" + admissionYearMonth + '\'' +
                '}';
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getAdmissionYearMonth() {
        return admissionYearMonth;
    }

    public void setAdmissionYearMonth(String admissionYearMonth) {
        this.admissionYearMonth = admissionYearMonth;
    }
}
