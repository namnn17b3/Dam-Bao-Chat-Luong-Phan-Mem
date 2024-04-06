package com.example.dbclpm.model;

public class Class {
	private int id;
	private String name;
	private String room_name;
	private int teacher_id;
	private int subject_id;
	private int term_id;
	
	
	public Class() {
	}
	
	public Class(int id,  String name, String room_name, int teacher_id, int subject_id, int term_id) {
		super();
		this.id = id;
		this.name = name;
		this.room_name = room_name;
		this.teacher_id = teacher_id;
		this.subject_id = subject_id;
		this.term_id = term_id;
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
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public int getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	public int getSubject_id() {
		return subject_id;
	}
	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}
	public int getTerm_id() {
		return term_id;
	}
	public void setTerm_id(int term_id) {
		this.term_id = term_id;
	}
	
}
