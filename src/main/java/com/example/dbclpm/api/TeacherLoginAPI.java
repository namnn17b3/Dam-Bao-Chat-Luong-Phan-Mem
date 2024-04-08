package com.example.dbclpm.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.example.dbclpm.dao.Dao;
import com.example.dbclpm.dao.impl.DaoImpl;
import com.example.dbclpm.dto.ResponseCommonDto;
import com.example.dbclpm.model.Teacher;
import com.google.gson.Gson;

@WebServlet("/api/teacher/login")
public class TeacherLoginAPI extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Dao dao = new DaoImpl();
    private static final Gson gson = new Gson();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        ResponseCommonDto responseCommonDto = new ResponseCommonDto();
        PrintWriter pw = resp.getWriter();
        HttpSession session = req.getSession();
        Teacher teacher = dao.getTeacherByEmail(email);
        
        if (Objects.isNull(teacher)) {
            responseCommonDto.setData(null);
            responseCommonDto.setMessage("Email is not exist!");
            responseCommonDto.setStatus(400);
            resp.setStatus(400);
            pw.println(gson.toJson(responseCommonDto));
            return;
        }
        
        if (!BCrypt.checkpw(password, teacher.getPassword())) {
            responseCommonDto.setData(null);
            responseCommonDto.setMessage("Password is incorrect!");
            responseCommonDto.setStatus(400);
            resp.setStatus(400);
            pw.println(gson.toJson(responseCommonDto));
            return;
        }
        
        session.setMaxInactiveInterval(24*60*60); // 1 day
        session.setAttribute("teacher", teacher);
        responseCommonDto.setData(null);
        responseCommonDto.setMessage("");
        responseCommonDto.setStatus(200);
        pw.println(gson.toJson(responseCommonDto));
    }
}
