package com.example.easy_attendence;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Student{

    private String name;
    private String roll;
    private String department;
    private String mobile;
    private String email;

    public Student()
    {

    }
    public Student(String name, String roll,String department,String mobile,String email) {


        this.name = name;
        this.roll = roll;
        this.department=department;
        this.email=email;
        this.mobile=mobile;

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

    public String getMobile()
    {
        return mobile;
    }
    public void setMobile(String mobile){
        this.mobile=mobile;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email=email;
    }


    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
