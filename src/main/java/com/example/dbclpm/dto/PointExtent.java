package com.example.dbclpm.dto;

public class PointExtent {
    private Float scoreByNumber;
    private String scoreByWord;
    private Float scorePerFourRank;
    private String note;
    
    public PointExtent() {}

    public PointExtent(Float scoreByNumber, String scoreByWord, Float scorePerFourRank, String note) {
        super();
        this.scoreByNumber = scoreByNumber;
        this.scoreByWord = scoreByWord;
        this.scorePerFourRank = scorePerFourRank;
        this.note = note;
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
        return "PointExtent [scoreByNumber=" + scoreByNumber + ", scoreByWord=" + scoreByWord + ", scorePerFourRank="
                + scorePerFourRank + ", note=" + note + "]";
    }
}
