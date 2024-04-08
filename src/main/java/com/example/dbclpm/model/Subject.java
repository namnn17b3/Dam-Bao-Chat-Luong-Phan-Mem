package com.example.dbclpm.model;

public class Subject {
    private int id;
    private String name;
    private int numberOfCredits;
    private int percentCC;
    private int percentBTL;
    private int percentTH;
    private int percentKTGK;
    private int percentKTCK;
    
    public Subject() {}

    public Subject(int id, String name, int numberOfCredits, int percentCC, int percentBTL, int percentTH, int percentKTGK,
            int percentKTCK) {
        super();
        this.id = id;
        this.name = name;
        this.numberOfCredits = numberOfCredits;
        this.percentCC = percentCC;
        this.percentBTL = percentBTL;
        this.percentTH = percentTH;
        this.percentKTGK = percentKTGK;
        this.percentKTCK = percentKTCK;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public int getPercentCC() {
        return percentCC;
    }

    public void setPercentCC(int percentCC) {
        this.percentCC = percentCC;
    }

    public int getPercentBTL() {
        return percentBTL;
    }

    public void setPercentBTL(int percentBTL) {
        this.percentBTL = percentBTL;
    }
    
    public int getPercentTH() {
        return percentTH;
    }

    public void setPercentTH(int percentTH) {
        this.percentTH = percentTH;
    }

    public int getPercentKTGK() {
        return percentKTGK;
    }

    public void setPercentKTGK(int percentKTGK) {
        this.percentKTGK = percentKTGK;
    }

    public int getPercentKTCK() {
        return percentKTCK;
    }

    public void setPercentKTCK(int percentKTCK) {
        this.percentKTCK = percentKTCK;
    }

    @Override
    public String toString() {
        return "Subject [id=" + id + ", name=" + name + ", numberOfCredits=" + numberOfCredits + ", percentCC="
                + percentCC + ", percentBTL=" + percentBTL + ", percentTH=" + percentTH + ", percentKTGK=" + percentKTGK
                + ", percentKTCK=" + percentKTCK + "]";
    }
}
