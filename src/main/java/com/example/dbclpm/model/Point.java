package com.example.dbclpm.model;

public class Point {
    private int id;
    private Float cc;
    private Float btl;
    private Float th;
    private Float ktgk;
    private Float ktck;
    private int classId;
    private int studentId;
    
    public Point() {}
    
    public Point(int id, Float cc, Float btl, Float th, Float ktgk, Float ktck, int classId, int studentId) {
        super();
        this.id = id;
        this.cc = cc;
        this.btl = btl;
        this.th = th;
        this.ktgk = ktgk;
        this.ktck = ktck;
        this.classId = classId;
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getCc() {
        return cc;
    }

    public void setCc(Float cc) {
        this.cc = cc;
    }

    public Float getBtl() {
        return btl;
    }

    public void setBtl(Float btl) {
        this.btl = btl;
    }

    public Float getKtgk() {
        return ktgk;
    }
    
    public void setKtgk(Float ktgk) {
        this.ktgk = ktgk;
    }

    public void setTh(Float th) {
        this.th = th;
    }

    public Float getTh() {
        return th;
    }

    public Float getKtck() {
        return ktck;
    }

    public void setKtck(Float ktck) {
        this.ktck = ktck;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Point [id=" + id + ", cc=" + cc + ", btl=" + btl + ", ktgk=" + ktgk + ", ktck=" + ktck + ", classId="
                + classId + ", studentId=" + studentId + "]";
    }
}
