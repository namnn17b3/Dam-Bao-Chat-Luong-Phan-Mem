package com.example.dbclpm.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dbclpm.dto.ResponseCommonDto;
import com.google.gson.Gson;

@WebServlet("/api/teacher/logout")
public class TeacherLogoutAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();
        PrintWriter pw = resp.getWriter();
        ResponseCommonDto responseCommonDto = new ResponseCommonDto();
        responseCommonDto.setData(null);
        responseCommonDto.setMessage("OK");
        responseCommonDto.setStatus(400);
        pw.println(gson.toJson(responseCommonDto));
    }
}
