package com.example.dbclpm.dto;

import com.example.dbclpm.model.Point;
import com.example.dbclpm.model.Student;

public class StudentPointTable {
    private Student student;
    private Point point;
    private PointExtent pointExtent;
    
    public StudentPointTable() {}

    public StudentPointTable(Student student, Point point, PointExtent pointExtent) {
        super();
        this.student = student;
        this.point = point;
        this.pointExtent = pointExtent;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public PointExtent getPointExtent() {
        return pointExtent;
    }

    public void setPointExtent(PointExtent pointExtent) {
        this.pointExtent = pointExtent;
    }

    @Override
    public String toString() {
        return "StudentPointTable [student=" + student + ", point=" + point + ", pointExtent=" + pointExtent + "]";
    }
}
