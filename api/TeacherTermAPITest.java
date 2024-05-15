package com.example.dbclpm.api;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dbclpm.api.TeacherTermAPI;
import com.example.dbclpm.dao.Dao;
import com.example.dbclpm.dto.ResponseCommonDto;
import com.example.dbclpm.model.Teacher;
import com.example.dbclpm.model.Term;
import com.google.gson.Gson;

public class TeacherTermAPITest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private Dao dao;

    @Mock
    private PrintWriter printWriter;

    @InjectMocks
    private TeacherTermAPI teacherTermAPI;

    private static final Gson gson = new Gson();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    // Test cases when session attribute "teacher" is null
    @Test
    public void testDoGet_NoSession() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(null);
        when(response.getWriter()).thenReturn(printWriter);
        
        teacherTermAPI.doGet(request, response);
        
        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    // Test cases when parameter "lt" is null
    @Test
    public void testDoGet_LtNull_TermsFound() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);

        List<Term> terms = new ArrayList<>();
        terms.add(new Term());

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("lt")).thenReturn(null);
        when(dao.getTermsByTeacherId(teacher.getId(), 0)).thenReturn(terms);
        when(response.getWriter()).thenReturn(printWriter);

        teacherTermAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("OK", 200, terms)));
    }

    @Test
    public void testDoGet_LtNull_NoTermsFound() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);

        List<Term> terms = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("lt")).thenReturn(null);
        when(dao.getTermsByTeacherId(teacher.getId(), 0)).thenReturn(terms);
        when(response.getWriter()).thenReturn(printWriter);

        teacherTermAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("OK", 200, terms)));
    }

    // Test cases when parameter "lt" equals "1"
    @Test
    public void testDoGet_LtOne_TermsFound() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);

        List<Term> terms = new ArrayList<>();
        terms.add(new Term());

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("lt")).thenReturn("1");
        when(dao.getTermsByTeacherId(teacher.getId(), 1)).thenReturn(terms);
        when(response.getWriter()).thenReturn(printWriter);

        teacherTermAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("OK", 200, terms)));
    }

    @Test
    public void testDoGet_LtOne_NoTermsFound() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);

        List<Term> terms = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("lt")).thenReturn("1");
        when(dao.getTermsByTeacherId(teacher.getId(), 1)).thenReturn(terms);
        when(response.getWriter()).thenReturn(printWriter);

        teacherTermAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("OK", 200, terms)));
    }

    // Test cases when parameter "lt" has an invalid value
    @Test
    public void testDoGet_LtInvalid_TermsFound() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);

        List<Term> terms = new ArrayList<>();
        terms.add(new Term());

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("lt")).thenReturn("invalid");
        when(dao.getTermsByTeacherId(teacher.getId(), 0)).thenReturn(terms);
        when(response.getWriter()).thenReturn(printWriter);

        teacherTermAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("OK", 200, terms)));
    }

    @Test
    public void testDoGet_LtInvalid_NoTermsFound() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);

        List<Term> terms = new ArrayList<>();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("lt")).thenReturn("invalid");
        when(dao.getTermsByTeacherId(teacher.getId(), 0)).thenReturn(terms);
        when(response.getWriter()).thenReturn(printWriter);

        teacherTermAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("OK", 200, terms)));
    }

    // Test cases with various "lt" parameter values
    @Test
    public void testDoGet_LtNegative() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);

        List<Term> terms = new ArrayList<>();
        terms.add(new Term());

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("lt")).thenReturn("-1");
        when(dao.getTermsByTeacherId(teacher.getId(), 0)).thenReturn(terms);
        when(response.getWriter()).thenReturn(printWriter);

        teacherTermAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("OK", 200, terms)));
    }

    @Test
    public void testDoGet_LtZero() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);

        List<Term> terms = new ArrayList<>();
        terms.add(new Term());

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("lt")).thenReturn("0");
        when(dao.getTermsByTeacherId(teacher.getId(), 0)).thenReturn(terms);
        when(response.getWriter()).thenReturn(printWriter);

        teacherTermAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("OK", 200, terms)));
    }

    // Test cases with boundary values for "lt" parameter
    @Test
    public void testDoGet_LtMaxValue() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);

        List<Term> terms = new ArrayList<>();
        terms.add(new Term());

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("lt")).thenReturn(String.valueOf(Integer.MAX_VALUE));
        when(dao.getTermsByTeacherId(teacher.getId(), 0)).thenReturn(terms);
        when(response.getWriter()).thenReturn(printWriter);

        teacherTermAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("OK", 200, terms)));
    }
}