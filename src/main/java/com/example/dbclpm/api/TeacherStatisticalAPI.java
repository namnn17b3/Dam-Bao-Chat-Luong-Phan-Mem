package com.example.dbclpm.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dbclpm.dao.Dao;
import com.example.dbclpm.dao.impl.DaoImpl;
import com.example.dbclpm.dto.ResponseCommonDto;
import com.example.dbclpm.dto.StatisticalStudentDto;
import com.example.dbclpm.model.Term;
import com.google.gson.Gson;

@WebServlet("/api/teacher/statistical")
public class TeacherStatisticalAPI extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private static final Dao dao = new DaoImpl();
    private static final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int termId = 0;
        int subjectId = 0;
        PrintWriter pw = resp.getWriter();
        
        try {
            termId = Integer.parseInt(req.getParameter("termId").trim());
            subjectId = Integer.parseInt(req.getParameter("subjectId").trim());
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setStatus(400);
            pw.println(gson.toJson(new ResponseCommonDto(ex.getMessage(), 400, null)));
            return;
        }
        
        Term term = dao.getTermById(termId);
        long now = System.currentTimeMillis();
        if (term == null || term.getEndDate().getTime() >= now) {
            resp.setStatus(400);
            pw.println(gson.toJson(new ResponseCommonDto("Invalid Term Time", 400, null)));
            return;
        }
        
        List<StatisticalStudentDto> statisticalStudentDtos = dao.getStatisticalStudentDtos(termId, subjectId);
        pw.println(gson.toJson(new ResponseCommonDto("OK", 200, statisticalStudentDtos)));
    }
}
