package com.example.dbclpm.api;

import com.example.dbclpm.dto.ResponseCommonDto;
import com.example.dbclpm.dto.StatisticalStudentDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherStatisticalAPITest {

    private TeacherStatisticalAPI teacherStatisticalAPI = new TeacherStatisticalAPI();
    private HttpServletRequest req;
    private HttpServletResponse resp;
    Gson gson = new Gson();

    @BeforeEach

    public void setUp() throws Exception {
        req = Mockito.mock(HttpServletRequest.class);
        resp = Mockito.mock(HttpServletResponse.class);
    }


    @Test
    public void testDoGet() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(printWriter);
        Mockito.when(req.getParameter("termId")).thenReturn("1");
        Mockito.when(req.getParameter("subjectId")).thenReturn("1");
        teacherStatisticalAPI.doGet(req, resp);
        printWriter.flush();
        System.out.println(stringWriter.toString());
        List<StatisticalStudentDto> list = new ArrayList<>();
        list.add(new StatisticalStudentDto(41, 9, 1, "CN1"));
        list.add(new StatisticalStudentDto(32, 7, 4, "CN4"));
        list.add(new StatisticalStudentDto(40, 10, 7, "CN7"));
        list.add(new StatisticalStudentDto(44, 10, 10, "CN10"));
        list.add(new StatisticalStudentDto(42, 4, 13, "CN13"));

        assertEquals(gson.toJson(new ResponseCommonDto("OK", 200, list)), stringWriter.toString().trim());
    }

    @Test
    public void testDoGet_InvalidTermId() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(printWriter);
        Mockito.when(req.getParameter("termId")).thenReturn("Invalid");
        Mockito.when(req.getParameter("subjectId")).thenReturn("1");
        teacherStatisticalAPI.doGet(req, resp);
        printWriter.flush();
        System.out.println(stringWriter.toString());

        assertEquals(gson.toJson(new ResponseCommonDto("For input string: \"Invalid\"", 400, null)), stringWriter.toString().trim());
    }
    @Test
    public void testDoGet_subjectInvalid() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(printWriter);
        Mockito.when(req.getParameter("termId")).thenReturn("1");
        Mockito.when(req.getParameter("subjectId")).thenReturn("Invalid");
        teacherStatisticalAPI.doGet(req, resp);
        printWriter.flush();
        System.out.println(stringWriter.toString());

        assertEquals(gson.toJson(new ResponseCommonDto("For input string: \"Invalid\"", 400, null)), stringWriter.toString().trim());
    }

    @Test
    public void testDoGet_TermIdNotExist() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(printWriter);
        Mockito.when(req.getParameter("termId")).thenReturn("-1");
        Mockito.when(req.getParameter("subjectId")).thenReturn("1");
        teacherStatisticalAPI.doGet(req, resp);
        printWriter.flush();
        System.out.println(stringWriter.toString());

        assertEquals(gson.toJson(new ResponseCommonDto("Invalid Term Time", 400, null)), stringWriter.toString().trim());
    }

    @Test
    public void testDoGet_SucjectIdNotExist() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(printWriter);
        Mockito.when(req.getParameter("termId")).thenReturn("1");
        Mockito.when(req.getParameter("subjectId")).thenReturn("-1");
        teacherStatisticalAPI.doGet(req, resp);
        printWriter.flush();
        System.out.println(stringWriter.toString());

        assertEquals(gson.toJson(new ResponseCommonDto("Subject is not exist", 400, null)), stringWriter.toString().trim());
    }
    @Test
    public void testDoGet_OtherTermId() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(printWriter);
        Mockito.when(req.getParameter("termId")).thenReturn("2");
        Mockito.when(req.getParameter("subjectId")).thenReturn("1");
        teacherStatisticalAPI.doGet(req, resp);
        printWriter.flush();
        System.out.println(stringWriter.toString());

        assertEquals(gson.toJson(new ResponseCommonDto("Invalid Term Time", 400, null)), stringWriter.toString().trim());
    }
    @Test
    public void testDoGet_OtherSubjectId() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(printWriter);
        Mockito.when(req.getParameter("termId")).thenReturn("1");
        Mockito.when(req.getParameter("subjectId")).thenReturn("2");
        teacherStatisticalAPI.doGet(req, resp);
        printWriter.flush();
        System.out.println(stringWriter.toString());
        List<StatisticalStudentDto> list = new ArrayList<>();
        list.add(new StatisticalStudentDto(39, 7, 16, "CN16"));
        list.add(new StatisticalStudentDto(26, 10, 19, "CN19"));
        list.add(new StatisticalStudentDto(41, 6, 22, "CN22"));
        list.add(new StatisticalStudentDto(30, 13, 25, "CN25"));
        list.add(new StatisticalStudentDto(37, 10, 28, "CN28"));

        assertEquals(gson.toJson(new ResponseCommonDto("OK", 200, list)), stringWriter.toString().trim());
    }
    @Test
    public void testDoGet_TermIdNull() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(printWriter);
        Mockito.when(req.getParameter("termId")).thenReturn("");
        Mockito.when(req.getParameter("subjectId")).thenReturn("1");
        teacherStatisticalAPI.doGet(req, resp);
        printWriter.flush();
        System.out.println(stringWriter.toString());

        assertEquals(gson.toJson(new ResponseCommonDto("Subject is not exist", 400, null)), stringWriter.toString().trim());
    }
    @Test
    public void testDoGet_SucjectIdNull() throws Exception {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(printWriter);
        Mockito.when(req.getParameter("termId")).thenReturn("1");
        Mockito.when(req.getParameter("subjectId")).thenReturn("");
        teacherStatisticalAPI.doGet(req, resp);
        printWriter.flush();
        System.out.println(stringWriter.toString());

        assertEquals(gson.toJson(new ResponseCommonDto("For input string: \"\"", 400, null)), stringWriter.toString().trim());
    }
}

