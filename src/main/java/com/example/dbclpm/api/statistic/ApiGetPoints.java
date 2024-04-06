package com.example.dbclpm.api.statistic;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dbclpm.dao.StatisticDao;
import com.example.dbclpm.dao.impl.StatisticDaoImpl;
import com.example.dbclpm.model.Point;
import com.example.dbclpm.model.Term;
import com.fasterxml.jackson.databind.ObjectMapper;
@WebServlet("/api/statistic/points")
public class ApiGetPoints extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int subjectId = Integer.parseInt(req.getParameter("subjectId"));
		int termId = Integer.parseInt(req.getParameter("termId"));
//        int teacherId = (int) req.getSession().getAttribute("teacherId");
	 	int teacherId = 1;
	 	StatisticDao statisticDao = new StatisticDaoImpl();
        List<Point> studentScores = statisticDao.getPoints(teacherId, subjectId, termId);

        // Chuyển đổi danh sách điểm số thành JSON
        ObjectMapper mapper = new ObjectMapper();
        String jsonScores = mapper.writeValueAsString(studentScores);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonScores);
    }
}
