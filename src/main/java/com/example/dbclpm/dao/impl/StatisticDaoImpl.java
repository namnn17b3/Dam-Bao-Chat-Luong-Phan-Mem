package com.example.dbclpm.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.swing.JOptionPane;

import org.apache.catalina.core.FrameworkListener;

import com.example.dbclpm.dao.StatisticDao;
import com.example.dbclpm.model.Point;
import com.example.dbclpm.model.Subject;
import com.example.dbclpm.model.Term;


public class StatisticDaoImpl implements StatisticDao{
	
	@Override
	public List<Term> getTerms() {
	    List<Term> terms = new ArrayList<>();
	    String sql = "SELECT * FROM term";

	    try (Connection connection = DataBaseConnect.getConnection();
	         PreparedStatement statement = connection.prepareStatement(sql);
	         ResultSet rs = statement.executeQuery()) {

	        while (rs.next()) {
	            Term term = new Term();
	            term.setId(rs.getInt("id"));
	            term.setName(rs.getString("name"));
	            term.setStart_date(rs.getDate("start_date"));
	            term.setEnd_date(rs.getDate("end_date"));
	            terms.add(term);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return terms;
	}

	

	@Override
	public List<Subject> getSubject(int teacherId, int termId) {
		List<Subject> subjectList = new ArrayList<>();
		String sql = "SELECT distinct b.* FROM class a " +
				"JOIN subject b ON a.subject_id = b.id " +
				"WHERE a.teacher_id = ? AND a.term_id = ? group by a.subject_id";
		 try (Connection connection = DataBaseConnect.getConnection();
		    		PreparedStatement statement = connection.prepareStatement(sql)) {
		        statement.setInt(1, teacherId);
		        statement.setInt(2, termId);
		        try (ResultSet rs = statement.executeQuery()) {
		            while (rs.next()) {
		                Subject subject = new Subject();
		                subject.setId(rs.getInt("id"));
		                subject.setName(rs.getString("name"));
		                subject.setPercent_btl(rs.getInt("percent_btl"));
		                subject.setPercent_ktgk(rs.getInt("percent_ktgk"));
		                subject.setPercent_ktck(rs.getInt("percent_ktck"));
		                subject.setPercent_cc(rs.getInt("percent_cc"));
		                subject.setPercent_th(rs.getInt("percent_th"));
		                subjectList.add(subject);
		            }
		            
		        }
		    } catch (SQLException e) {
		         e.printStackTrace();
		    }
		    return subjectList;
	}

	@Override
	public List<Point> getPoints(int teacherId, int subjectId, int termId) {
		List<Point> pointList = new ArrayList<>();
		String sql = "SELECT p.* FROM point p " +
				"JOIN class c ON p.class_id = c.id " +
				"JOIN student s ON p.student_id = s.id " +
				"WHERE c.id IN (SELECT id FROM class WHERE teacher_id = ?) "+
				"And c.subject_id = ? "+
				"And c.term_id = ? ";
		 try (Connection connection = DataBaseConnect.getConnection();
		    		PreparedStatement statement = connection.prepareStatement(sql)) {
		        statement.setInt(1, teacherId);
		        statement.setInt(2, subjectId);
		        statement.setInt(3, termId);
		        try (ResultSet rs = statement.executeQuery()) {
		            while (rs.next()) {
		                Point point = new Point();
		                point.setId(rs.getInt("id"));
		                point.setCc(rs.getFloat("cc"));
		                point.setKtgk(rs.getFloat("ktgk"));
		                point.setKtck(rs.getFloat("ktck"));
		                point.setBtl(rs.getFloat("btl"));
		                point.setTh(rs.getFloat("th"));
		                point.setClass_id(rs.getInt("class_id"));
		                point.setStudent_id(rs.getInt("student_id"));
		                
		                pointList.add(point);
		            }
		            
		        }
		    } catch (SQLException e) {
//		    	 JOptionPane.showMessageDialog(null, "“Đã có \r\n"
//		    	 		+ "lỗi xảy ra vui lòng thử lại sau " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		         e.printStackTrace();
		    }
		    return pointList;
	}

	



	
//	@Override
//	public Map<Integer, String> getSubject(int teacherId, int termId) {
//	    Map<Integer, String> subjectMap = new HashMap<>();
//	    String sql = "SELECT a.id, b.name FROM class a " +
//	                 "JOIN subject b ON a.subject_id = b.id " +
//	                 "WHERE a.teacher_id = ? AND a.term_id = ?";
//	    try (Connection connection = DataBaseConnect.getConnection();
//	    		PreparedStatement statement = connection.prepareStatement(sql)) {
//	        statement.setInt(1, teacherId);
//	        statement.setInt(2, termId);
//	        try (ResultSet rs = statement.executeQuery()) {
//	            while (rs.next()) {
//	                int id = rs.getInt("id");
//	                String name = rs.getString("name");
//	                subjectMap.put(id, name);
//	            }
//	        }
//	    } catch (SQLException e) {
////	    	 JOptionPane.showMessageDialog(null, "“Đã có \r\n"
////	    	 		+ "lỗi xảy ra vui lòng thử lại sau " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//	         e.printStackTrace();
//	    }
//	    return subjectMap;
//	}


	
}
