package com.example.dbclpm.dao;



import java.util.List;
import java.util.Map;

import com.example.dbclpm.model.Point;
import com.example.dbclpm.model.Subject;
import com.example.dbclpm.model.Term;


public interface StatisticDao {
	
	List<Term> getTerms();
	List<Subject> getSubject(int teacherId, int termId);
	List<Point> getPoints(int teacherId, int subjectId, int ternId);
//	Map<Integer, String> getSubject(int teacherId, int termId);
	
}
