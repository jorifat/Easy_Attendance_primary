package com.example.easy_attendence;

public class Course {
    private String course_name;
    private String code;
    private String department;
    private String teacher;

    public Course()
    {

    }

    public Course(String course_name, String code,String department, String teacher) {
        this.course_name = course_name;
        this.code = code;
        this.department=department;
        this.teacher = teacher;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
