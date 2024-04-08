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
import com.example.dbclpm.model.Teacher;
import com.example.dbclpm.model.Term;
import com.google.gson.Gson;

@WebServlet("/api/teacher/term")
public class TeacherTermAPI extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Dao dao = new DaoImpl();
    private static final Gson gson = new Gson();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        
        List<Term> list = null;
        String lt = req.getParameter("lt");
        if (lt == null) {
            list = dao.getTermsByTeacherId(teacher.getId(), 0);
        } else if (lt.equals("1")) {
            list = dao.getTermsByTeacherId(teacher.getId(), 1);
        } else {
            list = dao.getTermsByTeacherId(teacher.getId(), 0);
        }
        pw.println(gson.toJson(new ResponseCommonDto("OK", 200, list)));
    }
}
