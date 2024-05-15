package com.example.dbclpm.dao;

import java.sql.Connection;
import java.util.List;

import com.example.dbclpm.model.Teacher;
import com.example.dbclpm.model.Term;
import com.example.dbclpm.dto.StatisticalStudentDto;
import com.example.dbclpm.dto.StudentPointTableDtos;
import com.example.dbclpm.model.Clazz;
import com.example.dbclpm.model.Point;
import com.example.dbclpm.model.Student;
import com.example.dbclpm.model.Subject;

public interface Dao {
    public Teacher getTeacherByEmail(String email);
    public List<Term> getTermsByTeacherId(int teacherId, int lt);
    public List<Subject> getSubjectsByTeacherIdAndTermId(int teacherId, int termId);
    public List<Clazz> getClazzsByTeacherIdAndTermIdAndSubjectId(int teacherId, int termId, int subjectId);
    public int getQuantityStudentPointTableDtos(int classId);
    public StudentPointTableDtos getStudentPointTableDtos(int classId, int page, int itemInPage);
    public Subject getSubjectById(int subjectId);
    public void savePoint(int pointId, Float cc, Float btl, Float th, Float ktgk, Float ktck);
    public Subject getSubjectByClassId(int classId);
    public Subject getSubjectByPointId(int pointId);
    public List<StatisticalStudentDto> getStatisticalStudentDtos(int termId, int subjectId);
    public Term getTermByPointId(int pointId);
    public Term getTermById(int termId);
    public Clazz getClassByPointId(int pointId);
    public Term getTermByClassId(int classId);
    public Teacher getTeacherByClassId(int classId);
    public Student getStudentById(int studentId);
    public void savePointByClassIdAndStudentId(Connection conn, int classId, int studentId, Float cc, Float btl, Float th, Float ktgk, Float ktck) throws Exception;
    public Point getPointByCLassIdAndStudentId(int classId, int studentId);
}
