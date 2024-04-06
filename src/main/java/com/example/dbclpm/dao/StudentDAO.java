package com.example.dbclpm.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.example.dbclpm.dao.impl.PoolConnection;

import Model.StudentModel;

public class StudentDAO {
	public ArrayList<StudentModel> getListStudentByIdTeacherTermSubjectClass(int teacher_id, int term_id, int subject_id, int class_id) throws SQLException{
		Connection conn = PoolConnection.getPoolConnection().getConnection();
		
		PreparedStatement ps = conn.prepareStatement("select s.*, p.*, c.name as class_name from student as s "
				+ "join point as p on p.student_id = s.id "
				+ "join class as c on c.id = p.class_id "
				+ "join term as t on t.id = c.term_id "
				+ "join subject as su on su.id = c.subject_id "
				+ "where  c.teacher_id = ? and t.id = ? and su.id = ? and c.id = ? "
				+ "group by s.id");
		
		ps.setInt(1, teacher_id);
		
		ps.setInt(2, term_id);
		
		ps.setInt(3, subject_id);
		
		ps.setInt(4, class_id);
		
		ResultSet res = ps.executeQuery();
		
		ArrayList<StudentModel> listStudent = new ArrayList<StudentModel>();
		while(res.next()) {
			StudentModel student = new StudentModel();
			
			student.setAddress(res.getString("address"));
			
			student.setEmail(res.getString("email"));
			
			student.setId(res.getInt("id"));
			
			student.setName(res.getString("name"));
			
			student.setPhone(res.getString("phone"));
			
			student.setBtl(res.getString("btl"));
			
			student.setTh(res.getString("th"));
			
			student.setCc(res.getString("cc"));
			
			student.setKtck(res.getString("ktck"));
			
			student.setKtgk(res.getString("ktgk"));
			
			Date date = res.getDate("date_of_birth");
			
			String date_of_birth;
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
			
			date_of_birth = sdf.format(date);
			
			student.setDate_of_birth(date_of_birth);
			
			student.setClass_name(res.getString("class_name"));
			
			listStudent.add(student);
			
		}
		
		conn.close();
		
		return listStudent;
	}
}
