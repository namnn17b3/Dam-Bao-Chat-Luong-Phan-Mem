package com.example.dbclpm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.dbclpm.dao.impl.PoolConnection;

import Model.TermModel;

public class TermDAO {
	public ArrayList<TermModel> getListTerm() throws SQLException {
		Connection conn = (Connection) PoolConnection.getPoolConnection().getConnection();
		
		PreparedStatement statement = conn.prepareStatement("select * from term where start_date >= now() and end_date >=now()");
		
		ResultSet res = statement.executeQuery();
		
		ArrayList<TermModel> listTerm = new ArrayList<TermModel>();
		
		while(res.next()) {
			TermModel term = new TermModel();
			
			term.setId(res.getInt("id"));
			
			term.setName(res.getString("name"));
			
			listTerm.add(term);
		}
		
        if (res != null) {
            res.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (conn != null) {
            conn.close();
        }
		
		return listTerm;
	}
}
