package com.example.dbclpm.model;

public class Point {
	private int id;
	private float cc;
	private float btl;
	private float ktgk;
	private float ktck;
	private float th;
	private int class_id;
	private int student_id;
	
	public Point() {
		
	}
	
	

	public Point(int id, float cc, float btl, float ktgk, float ktck, float th, int class_id, int student_id) {
		super();
		this.id = id;
		this.cc = cc;
		this.btl = btl;
		this.ktgk = ktgk;
		this.ktck = ktck;
		this.th = th;
		this.class_id = class_id;
		this.student_id = student_id;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getCc() {
		return cc;
	}

	public void setCc(float cc) {
		this.cc = cc;
	}

	public float getBtl() {
		return btl;
	}

	public void setBtl(float btl) {
		this.btl = btl;
	}

	public float getKtgk() {
		return ktgk;
	}

	public void setKtgk(float ktgk) {
		this.ktgk = ktgk;
	}

	public float getKtck() {
		return ktck;
	}

	public void setKtck(float ktck) {
		this.ktck = ktck;
	}
	
	
	public float getTh() {
		return th;
	}



	public void setTh(float th) {
		this.th = th;
	}



	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	
	
	
}
