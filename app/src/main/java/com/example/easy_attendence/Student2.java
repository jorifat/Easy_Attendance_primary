package com.example.easy_attendence;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Student2 implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name;
    private String roll;
    private String department;
    private boolean isSelected;


    public Student2() {

    }

    public Student2(String name, String roll, String department) {

        this.name = name;
        this.roll = roll;
        this.department=department;

    }
    public Student2(String name, String roll, String department,boolean isSelected) {

        this.name = name;
        this.roll = roll;
        this.department=department;
        this.isSelected=isSelected;

    }


    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}


