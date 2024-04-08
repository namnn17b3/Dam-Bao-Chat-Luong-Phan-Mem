package com.example.dbclpm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.dbclpm.dao.impl.PoolConnection;
import com.google.gson.Gson;

public class PointDAO {
	public String importScore(float cc, float btl, float th, float ktgk, float ktck, int class_id, int student_id) {
		if(cc<0.0 || btl<0.0 || th<0.0 || ktgk<0.0 || ktck<0.0 || cc>10.0 || btl>10.0 || th>10.0 || ktgk>10.0 || ktck>10.0) {
			Gson gson = new Gson();
			return gson.toJson("diem khong hop le");
		}
		try {
			Connection conn = PoolConnection.getPoolConnection().getConnection();
			
			PreparedStatement ps = conn.prepareStatement("update point set cc = ?, btl = ?, th = ?, ktgk = ?, ktck = ? where class_id = ? and student_id = ?");
			
			ps.setFloat(1, cc);
			
			ps.setFloat(2, btl);
			
			ps.setFloat(3, th);
			
			ps.setFloat(4, ktgk);
			
			ps.setFloat(5, ktck);
			
			ps.setInt(6, class_id);
			
			ps.setInt(7, student_id);
			
			int res = ps.executeUpdate();
			
			if(res>0) {
				return "true";
			}
			else {
				return "false";
			}
		}
		catch(SQLException e) {
			return e.getMessage();		}
	}
}
