package com.example.dbclpm.dto;

public class SavePointResponseDto {
    private Float cc;
    private Float btl;
    private Float th;
    private Float ktgk;
    private Float ktck;
    private Float scoreByNumber;
    private String scoreByWord;
    private Float scorePerFourRank;
    private String note;
    
    public SavePointResponseDto() {}

    public SavePointResponseDto(Float cc, Float btl, Float th, Float ktgk, Float ktck, Float scoreByNumber,
            String scoreByWord, Float scorePerFourRank, String note) {
        super();
        this.cc = cc;
        this.btl = btl;
        this.th = th;
        this.ktgk = ktgk;
        this.ktck = ktck;
        this.scoreByNumber = scoreByNumber;
        this.scoreByWord = scoreByWord;
        this.scorePerFourRank = scorePerFourRank;
        this.note = note;
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

    public Float getTh() {
        return th;
    }

    public void setTh(Float th) {
        this.th = th;
    }

    public Float getKtgk() {
        return ktgk;
    }

    public void setKtgk(Float ktgk) {
        this.ktgk = ktgk;
    }

    public Float getKtck() {
        return ktck;
    }

    public void setKtck(Float ktck) {
        this.ktck = ktck;
    }

    public Float getScoreByNumber() {
        return scoreByNumber;
    }

    public void setScoreByNumber(Float scoreByNumber) {
        this.scoreByNumber = scoreByNumber;
    }

    public String getScoreByWord() {
        return scoreByWord;
    }

    public void setScoreByWord(String scoreByWord) {
        this.scoreByWord = scoreByWord;
    }

    public Float getScorePerFourRank() {
        return scorePerFourRank;
    }

    public void setScorePerFourRank(Float scorePerFourRank) {
        this.scorePerFourRank = scorePerFourRank;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "SavePointResponseDto [cc=" + cc + ", btl=" + btl + ", th=" + th + ", ktgk=" + ktgk + ", ktck=" + ktck
                + ", scoreByNumber=" + scoreByNumber + ", scoreByWord=" + scoreByWord + ", scorePerFourRank="
                + scorePerFourRank + ", note=" + note + "]";
    }
}
