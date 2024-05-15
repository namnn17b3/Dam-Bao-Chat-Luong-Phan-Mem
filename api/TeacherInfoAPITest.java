package com.example.dbclpm.api;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;

import com.example.dbclpm.dto.ResponseCommonDto;
import com.example.dbclpm.model.Teacher;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class TeacherInfoAPITest extends TeacherInfoAPI {

    //Tests
    @Test
    public void testTeacher() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setName("amogus");
        teacher.setEmail("anv@stu.ptit.edu.vn");

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);

        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        when(res.getWriter()).thenReturn(writer);

        doGet(req, res);

        Gson gson1 = new Gson();
        ResponseCommonDto dto;
        dto = gson1.fromJson(sw.toString(), ResponseCommonDto.class);

        System.out.println(sw.toString());

        JsonObject jsonObject= gson1.fromJson(sw.toString(), JsonObject.class);
        Teacher teacher2= gson1.fromJson(jsonObject.get("data"), Teacher.class);

        Assert.assertEquals(teacher2.getId(),teacher.getId());
        Assert.assertEquals(teacher2.getName(),teacher.getName());
        Assert.assertEquals(teacher2.getEmail(), teacher.getEmail());
    }

    @Test
    public void TestTeacherNull() throws Exception
    {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        Teacher teacher = null;

        when(req.getSession()).thenReturn(session);
        //when(session.getAttribute("teacher")).thenReturn(teacher);

        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        when(res.getWriter()).thenReturn(writer);

        doGet(req, res);

        Gson gson1 = new Gson();
        ResponseCommonDto dto;
        dto = gson1.fromJson(sw.toString(), ResponseCommonDto.class);

        System.out.println(sw.toString());

        JsonObject jsonObject= gson1.fromJson(sw.toString(), JsonObject.class);
        Teacher teacher2= gson1.fromJson(jsonObject.get("data"), Teacher.class);

        Assert.assertNull(teacher2);
      /*
      Assert.assertEquals(teacher2.getId(),teacher.getId());
      Assert.assertEquals(teacher2.getName(),teacher.getName());
      Assert.assertEquals(teacher2.getEmail(), teacher.getEmail());
       */
    }

    @Test
    public void TestTeacherNameNull() throws Exception
    {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setName(null);
        teacher.setEmail("anv@stu.ptit.edu.vn");

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);

        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        when(res.getWriter()).thenReturn(writer);

        doGet(req, res);

        Gson gson1 = new Gson();
        ResponseCommonDto dto;
        dto = gson1.fromJson(sw.toString(), ResponseCommonDto.class);

        System.out.println(sw.toString());

        JsonObject jsonObject= gson1.fromJson(sw.toString(), JsonObject.class);
        Teacher teacher2= gson1.fromJson(jsonObject.get("data"), Teacher.class);

        Assert.assertEquals(teacher2.getId(),teacher.getId());
        Assert.assertEquals(teacher2.getName(),teacher.getName());
        Assert.assertEquals(teacher2.getEmail(), teacher.getEmail());
    }

    @Test
    public void TestTeacherEmailNull() throws Exception
    {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setName("amogus");
        teacher.setEmail(null);

        when(req.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);

        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        when(res.getWriter()).thenReturn(writer);

        doGet(req, res);

        Gson gson1 = new Gson();
        ResponseCommonDto dto;
        dto = gson1.fromJson(sw.toString(), ResponseCommonDto.class);

        System.out.println(sw.toString());

        JsonObject jsonObject= gson1.fromJson(sw.toString(), JsonObject.class);
        Teacher teacher2= gson1.fromJson(jsonObject.get("data"), Teacher.class);

        Assert.assertEquals(teacher2.getId(),teacher.getId());
        Assert.assertEquals(teacher2.getName(),teacher.getName());
        Assert.assertEquals(teacher2.getEmail(), teacher.getEmail());
    }
}
