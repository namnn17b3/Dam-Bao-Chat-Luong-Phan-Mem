package com.example.dbclpm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.dbclpm.Model.ClassModel;
import com.example.dbclpm.dao.impl.PoolConnection;

public class ClassDAO {
	public ArrayList<ClassModel> getListClassByIdTeacherTermSubject(int teacher_id, int term_id, int subject_id) throws SQLException{
		Connection conn = PoolConnection.getPoolConnection().getConnection();
		
		PreparedStatement ps = conn.prepareStatement("select c.*, t.name as teacher_name from class as c "
				+ "join teacher as t on t.id = c.teacher_id "
				+ "where c.teacher_id = ? and c.term_id = ? and c.subject_id = ?");
		
		ps.setInt(1, teacher_id);
		
		ps.setInt(2, term_id);
		
		ps.setInt(3, subject_id);
		
		ResultSet res = ps.executeQuery();
		
		ArrayList<ClassModel> listClass = new ArrayList<ClassModel>();
		while(res.next()) {
			ClassModel classModel = new ClassModel();
			
			classModel.setId(res.getInt("id"));
			
			classModel.setName(res.getString("name"));
			
			classModel.setRoom_name(res.getString("room_name"));
			
			classModel.setTeacher_name(res.getString("teacher_name"));
			
			listClass.add(classModel);
		}
		
		conn.close();
		
		return listClass;
	}
}
