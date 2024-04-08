package com.example.dbclpm.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dbclpm.dao.Dao;
import com.example.dbclpm.dao.impl.DaoImpl;
import com.example.dbclpm.dto.ResponseCommonDto;
import com.example.dbclpm.model.Clazz;
import com.example.dbclpm.model.Teacher;
import com.google.gson.Gson;

@WebServlet("/api/teacher/class")
public class TeacherClassAPI extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private static final Dao dao = new DaoImpl();
    private static final Gson gson = new Gson();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        
        int termId = 0;
        int subjectId = 0;
        
        try {
            termId = Integer.parseInt(req.getParameter("termId").trim());
            subjectId = Integer.parseInt(req.getParameter("subjectId").trim());
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setStatus(400);
            pw.println(gson.toJson(new ResponseCommonDto(ex.getMessage(), 400, null)));
            return;
        }
        
        List<Clazz> list = dao.getClazzsByTeacherIdAndTermIdAndSubjectId(teacher.getId(), termId, subjectId);
        pw.println(gson.toJson(new ResponseCommonDto("OK", 200, list)));
    }
}
