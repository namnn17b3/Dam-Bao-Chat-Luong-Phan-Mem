package com.example.dbclpm.model;

public class Clazz {
    private int id;
    private String name;
    private String roomName;
    private int teacherId;
    private int subjectId;
    private int termId;
    
    public Clazz() {}

    public Clazz(int id, String name, String roomName, int teacherId, int subjectId, int termId) {
        super();
        this.id = id;
        this.name = name;
        this.roomName = roomName;
        this.teacherId = teacherId;
        this.subjectId = subjectId;
        this.termId = termId;
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    @Override
    public String toString() {
        return "Clazz [id=" + id + ", name=" + name + ", roomName=" + roomName + ", teacherId=" + teacherId
                + ", subjectId=" + subjectId + ", termId=" + termId + "]";
    }
}
