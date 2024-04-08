package com.example.dbclpm.dto;

public class StatisticalStudentDto {
    private int accept;
    private int reject;
    private int classId;
    private String className;
    
    public StatisticalStudentDto() {}

    public StatisticalStudentDto(int accept, int reject, int classId, String className) {
        super();
        this.accept = accept;
        this.reject = reject;
        this.classId = classId;
        this.className = className;
    }

    public int getAccept() {
        return accept;
    }

    public void setAccept(int accept) {
        this.accept = accept;
    }

    public int getReject() {
        return reject;
    }

    public void setReject(int reject) {
        this.reject = reject;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "StatisticalStudentDto [accept=" + accept + ", reject=" + reject + ", classId=" + classId
                + ", className=" + className + "]";
    }
}
