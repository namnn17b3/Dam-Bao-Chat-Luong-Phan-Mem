package com.example.dbclpm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.dbclpm.Model.SubjectModel;
import com.example.dbclpm.dao.impl.PoolConnection;

public class SubjectDAO {
	public ArrayList<SubjectModel> getListSubjectByIdTerm(int teacher_id, int term_id) throws SQLException{
		Connection conn = PoolConnection.getPoolConnection().getConnection();
		
		PreparedStatement ps = conn.prepareStatement("select * from subject as s "
				+ "join class as c on c.subject_id = s.id "
				+ "where c.teacher_id = ? and c.term_id = ?");
		
		ps.setInt(1, teacher_id);
		
		ps.setInt(2, term_id);
		
		ResultSet res = ps.executeQuery();
		
		ArrayList<SubjectModel> listSubject = new ArrayList<SubjectModel>();
		
		while(res.next()) {
			SubjectModel subjectModel = new SubjectModel();
			
			subjectModel.setId(res.getInt("id"));
			
			subjectModel.setName(res.getString("name"));
			
			subjectModel.setPercent_btl(res.getInt("percent_btl"));
			
			subjectModel.setPercent_cc(res.getInt("percent_cc"));
			
			subjectModel.setPercent_ktck(res.getInt("percent_ktck"));
			
			subjectModel.setPercent_ktgk(res.getInt("percent_ktgk"));
			
			subjectModel.setPercent_th(res.getInt("percent_th"));
			
			listSubject.add(subjectModel);
		}
		
		conn.close();
		
		return listSubject;
	}
}
