package com.example.dbclpm.api;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dbclpm.dao.Dao;
import com.example.dbclpm.dao.impl.DaoImpl;
import com.example.dbclpm.dto.ResponseCommonDto;
import com.example.dbclpm.model.Clazz;
import com.example.dbclpm.model.Teacher;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TeacherClassAPITest {

    private TeacherClassAPI teacherClassAPI;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    Gson gson = new Gson();

    @BeforeEach
    public void setUp() {
        teacherClassAPI = new TeacherClassAPI();
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        session = Mockito.mock(HttpSession.class);
        // Inject the mocked dao into the TeacherClassAPI instance
    }

    @Test
    public void testDoGet() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);

        when(request.getParameter("termId")).thenReturn("1");
        when(request.getParameter("subjectId")).thenReturn("2");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);

        List<Clazz> clazzList = new ArrayList<>();
        Clazz clazz = new Clazz();
        clazz.setId(16);
        clazz.setName("CN16");
        clazz.setRoomName("RN16");
        clazz.setTeacherId(1);
        clazz.setSubjectId(2);
        clazz.setTermId(1);
        clazzList.add(clazz);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        teacherClassAPI.doGet(request, response);

        printWriter.flush();

        String result = stringWriter.toString();
        System.out.println(result);

        // Assertions to validate the response
        assertEquals(gson.toJson(new ResponseCommonDto("OK", 200, clazzList)), result.trim());
    }

    @Test
    public void testDoGet_InvalidParameters() throws Exception {
        when(request.getParameter("termId")).thenReturn("invalid");
        when(request.getParameter("subjectId")).thenReturn("invalid");
        when(request.getSession()).thenReturn(session);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        teacherClassAPI.doGet(request, response);

        printWriter.flush();

        String result = stringWriter.toString();
        System.out.println(result);

        // Assertions to validate the response
        assertEquals("{\"message\":\"For input string: \\\"invalid\\\"\",\"status\":400}", result.trim());
    }
    @Test
    public void testDoGet_NullAttribute() throws Exception {
        when(request.getParameter("termId")).thenReturn("invalid");
        when(request.getParameter("subjectId")).thenReturn("invalid");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(null);


        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        teacherClassAPI.doGet(request, response);

        printWriter.flush();

        String result = stringWriter.toString();
        System.out.println(result);

        // Assertions to validate the response
        assertEquals("{\"message\":\"For input string: \\\"invalid\\\"\",\"status\":400}", result.trim());
    }
    @Test
    public void testDoGetWrongIdTerm() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);

        when(request.getParameter("termId")).thenReturn("2");
        when(request.getParameter("subjectId")).thenReturn("2");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);

        List<Clazz> clazzList = new ArrayList<>();
        Clazz clazz = new Clazz();
        clazz.setId(16);
        clazz.setName("CN16");
        clazz.setRoomName("RN16");
        clazz.setTeacherId(1);
        clazz.setSubjectId(2);
        clazz.setTermId(1);
        clazzList.add(clazz);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        teacherClassAPI.doGet(request, response);

        printWriter.flush();

        String result = stringWriter.toString();
        System.out.println(result);

        // Assertions to validate the response
        assertEquals(gson.toJson(new ResponseCommonDto("OK", 200, clazzList)), result.trim());
    }

    @Test
    public void testDoGetWrongIdSubject() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);

        when(request.getParameter("termId")).thenReturn("1");
        when(request.getParameter("subjectId")).thenReturn("1");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);

        List<Clazz> clazzList = new ArrayList<>();
        Clazz clazz = new Clazz();
        clazz.setId(16);
        clazz.setName("CN16");
        clazz.setRoomName("RN16");
        clazz.setTeacherId(1);
        clazz.setSubjectId(2);
        clazz.setTermId(1);
        clazzList.add(clazz);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        teacherClassAPI.doGet(request, response);

        printWriter.flush();

        String result = stringWriter.toString();
        System.out.println(result);

        // Assertions to validate the response
        assertEquals(gson.toJson(new ResponseCommonDto("OK", 200, clazzList)), result.trim());
    }

    @Test
    public void testDoGetWrongNameClass() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);

        when(request.getParameter("termId")).thenReturn("1");
        when(request.getParameter("subjectId")).thenReturn("2");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);

        List<Clazz> clazzList = new ArrayList<>();
        Clazz clazz = new Clazz();
        clazz.setId(16);
        clazz.setName("CN15");
        clazz.setRoomName("RN16");
        clazz.setTeacherId(1);
        clazz.setSubjectId(2);
        clazz.setTermId(1);
        clazzList.add(clazz);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        teacherClassAPI.doGet(request, response);

        printWriter.flush();

        String result = stringWriter.toString();
        System.out.println(result);

        // Assertions to validate the response
        assertEquals(gson.toJson(new ResponseCommonDto("OK", 200, clazzList)), result.trim());
    }
    @Test
    public void testDoGetWrongNameRoom() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);

        when(request.getParameter("termId")).thenReturn("1");
        when(request.getParameter("subjectId")).thenReturn("2");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);

        List<Clazz> clazzList = new ArrayList<>();
        Clazz clazz = new Clazz();
        clazz.setId(16);
        clazz.setName("CN16");
        clazz.setRoomName("RN15");
        clazz.setTeacherId(1);
        clazz.setSubjectId(2);
        clazz.setTermId(1);
        clazzList.add(clazz);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        teacherClassAPI.doGet(request, response);

        printWriter.flush();

        String result = stringWriter.toString();
        System.out.println(result);

        // Assertions to validate the response
        assertEquals(gson.toJson(new ResponseCommonDto("OK", 200, clazzList)), result.trim());
    }
    @Test
    public void testDoGetWrongMess() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);

        when(request.getParameter("termId")).thenReturn("1");
        when(request.getParameter("subjectId")).thenReturn("2");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);

        List<Clazz> clazzList = new ArrayList<>();
        Clazz clazz = new Clazz();
        clazz.setId(16);
        clazz.setName("CN16");
        clazz.setRoomName("RN16");
        clazz.setTeacherId(1);
        clazz.setSubjectId(2);
        clazz.setTermId(1);
        clazzList.add(clazz);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        teacherClassAPI.doGet(request, response);

        printWriter.flush();

        String result = stringWriter.toString();
        System.out.println(result);

        // Assertions to validate the response
        assertEquals(gson.toJson(new ResponseCommonDto("", 200, clazzList)), result.trim());
    }
    @Test
    public void testDoGetWrongStatus() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);

        when(request.getParameter("termId")).thenReturn("1");
        when(request.getParameter("subjectId")).thenReturn("2");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);

        List<Clazz> clazzList = new ArrayList<>();
        Clazz clazz = new Clazz();
        clazz.setId(16);
        clazz.setName("CN16");
        clazz.setRoomName("RN16");
        clazz.setTeacherId(1);
        clazz.setSubjectId(2);
        clazz.setTermId(1);
        clazzList.add(clazz);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        teacherClassAPI.doGet(request, response);

        printWriter.flush();

        String result = stringWriter.toString();
        System.out.println(result);

        // Assertions to validate the response
        assertEquals(gson.toJson(new ResponseCommonDto("OK", 400, clazzList)), result.trim());
    }
    @Test
    public void testDoGetWrongList() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);

        when(request.getParameter("termId")).thenReturn("1");
        when(request.getParameter("subjectId")).thenReturn("2");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);

        List<Clazz> clazzList = new ArrayList<>();
        Clazz clazz = new Clazz();
        clazz.setId(16);
        clazz.setName("CN16");
        clazz.setRoomName("RN16");
        clazz.setTeacherId(1);
        clazz.setSubjectId(2);
        clazz.setTermId(1);
        clazzList.add(clazz);
        clazzList.add(clazz);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        teacherClassAPI.doGet(request, response);

        printWriter.flush();

        String result = stringWriter.toString();
        System.out.println(result);

        // Assertions to validate the response
        assertEquals(gson.toJson(new ResponseCommonDto("OK", 200, clazzList)), result.trim());
    }
}
