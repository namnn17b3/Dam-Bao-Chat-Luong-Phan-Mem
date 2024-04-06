package com.example.dbclpm.api.statistic;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dbclpm.dao.StatisticDao;
import com.example.dbclpm.dao.impl.StatisticDaoImpl;
import com.example.dbclpm.model.Subject;
import com.example.dbclpm.model.Term;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/api/statistic/subjects")
public class ApiGetSubjects extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int teacherId = (int) req.getSession().getAttribute("teacherId");
        // test
//		int teacherId = 1;
        
        int termId = Integer.parseInt(req.getParameter("termId"));
        StatisticDao statisticDao = new StatisticDaoImpl();

        List<Subject> subjectList = statisticDao.getSubject(teacherId, termId);
        
        // Chuyển đổi danh sách môn học thành JSON
        ObjectMapper mapper = new ObjectMapper();
        String jsonSubjects = mapper.writeValueAsString(subjectList);
        

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(jsonSubjects);
	}
}
