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

import com.example.dbclpm.api.TeacherSubjectAPI;
import com.example.dbclpm.dao.Dao;
import com.example.dbclpm.dto.ResponseCommonDto;
import com.example.dbclpm.model.Subject;
import com.example.dbclpm.model.Teacher;
import com.google.gson.Gson;

public class TeacherSubjectAPITest {

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
    private TeacherSubjectAPI teacherSubjectAPI;

    private static final Gson gson = new Gson();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoGet_NoSession() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(null);

        when(response.getWriter()).thenReturn(printWriter);

        teacherSubjectAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("teacher is null", 400, null)));
    }

    @Test
    public void testDoGet_InvalidTermId() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("termId")).thenReturn("invalid");

        when(response.getWriter()).thenReturn(printWriter);

        teacherSubjectAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("For input string: \"invalid\"", 400, null)));
    }

    @Test
    public void testDoGet_TermIdNull() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("termId")).thenReturn(null);

        when(response.getWriter()).thenReturn(printWriter);

        teacherSubjectAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("null", 400, null)));
    }

    @Test
    public void testDoGet_TermIdEmpty() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("termId")).thenReturn("");

        when(response.getWriter()).thenReturn(printWriter);

        teacherSubjectAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("For input string: \"\"", 400, null)));
    }

    @Test
    public void testDoGet_ValidRequestNoSubjects() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("termId")).thenReturn("1");

        List<Subject> subjects = new ArrayList<>();
        when(dao.getSubjectsByTeacherIdAndTermId(1, 1)).thenReturn(subjects);

        when(response.getWriter()).thenReturn(printWriter);

        teacherSubjectAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("OK", 200, subjects)));
    }

    @Test
    public void testDoGet_ValidRequestWithSubjects() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("termId")).thenReturn("1");

        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject());
        when(dao.getSubjectsByTeacherIdAndTermId(1, 1)).thenReturn(subjects);

        when(response.getWriter()).thenReturn(printWriter);

        teacherSubjectAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("OK", 200, subjects)));
    }

    @Test
    public void testDoGet_ExceptionDuringDaoCall() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("termId")).thenReturn("1");

        when(dao.getSubjectsByTeacherIdAndTermId(1, 1)).thenThrow(new RuntimeException("DAO exception"));

        when(response.getWriter()).thenReturn(printWriter);

        teacherSubjectAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("DAO exception", 500, null)));
    }

    @Test
    public void testDoGet_TeacherWithoutSubjects() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("termId")).thenReturn("1");

        List<Subject> subjects = new ArrayList<>();
        when(dao.getSubjectsByTeacherIdAndTermId(1, 1)).thenReturn(subjects);

        when(response.getWriter()).thenReturn(printWriter);

        teacherSubjectAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("OK", 200, subjects)));
    }

    @Test
    public void testDoGet_TeacherWithMultipleSubjects() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("termId")).thenReturn("1");

        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject());
        subjects.add(new Subject());
        when(dao.getSubjectsByTeacherIdAndTermId(1, 1)).thenReturn(subjects);

        when(response.getWriter()).thenReturn(printWriter);

        teacherSubjectAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("OK", 200, subjects)));
    }

    @Test
    public void testDoGet_InvalidTeacherId() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(null);

        when(response.getWriter()).thenReturn(printWriter);

        teacherSubjectAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("teacher is null", 400, null)));
    }

    @Test
    public void testDoGet_EmptyTeacherId() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(0);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("termId")).thenReturn("1");

        List<Subject> subjects = new ArrayList<>();
        when(dao.getSubjectsByTeacherIdAndTermId(0, 1)).thenReturn(subjects);

        when(response.getWriter()).thenReturn(printWriter);

        teacherSubjectAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("OK", 200, subjects)));
    }

    @Test
    public void testDoGet_ValidRequestEmptySubjects() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("termId")).thenReturn("1");

        List<Subject> subjects = new ArrayList<>();
        when(dao.getSubjectsByTeacherIdAndTermId(1, 1)).thenReturn(subjects);

        when(response.getWriter()).thenReturn(printWriter);

        teacherSubjectAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("OK", 200, subjects)));
    }

    @Test
    public void testDoGet_NullTeacher() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(null);
        when(request.getParameter("termId")).thenReturn("1");

        when(response.getWriter()).thenReturn(printWriter);

        teacherSubjectAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("teacher is null", 400, null)));
    }

    @Test
    public void testDoGet_TermIdNotFound() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("termId")).thenReturn("999"); // Assuming 999 does not exist

        List<Subject> subjects = new ArrayList<>();
        when(dao.getSubjectsByTeacherIdAndTermId(1, 999)).thenReturn(subjects);

        when(response.getWriter()).thenReturn(printWriter);

        teacherSubjectAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("OK", 200, subjects)));
    }

    @Test
    public void testDoGet_MultipleTeachersSameTerm() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("termId")).thenReturn("1");

        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject());
        when(dao.getSubjectsByTeacherIdAndTermId(1, 1)).thenReturn(subjects);

        when(response.getWriter()).thenReturn(printWriter);

        teacherSubjectAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("OK", 200, subjects)));
    }

    @Test
    public void testDoGet_TermIdLeadingSpaces() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("termId")).thenReturn(" 1");

        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject());
        when(dao.getSubjectsByTeacherIdAndTermId(1, 1)).thenReturn(subjects);

        when(response.getWriter()).thenReturn(printWriter);

        teacherSubjectAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("OK", 200, subjects)));
    }

    @Test
    public void testDoGet_TermIdTrailingSpaces() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("termId")).thenReturn("1 ");

        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject());
        when(dao.getSubjectsByTeacherIdAndTermId(1, 1)).thenReturn(subjects);

        when(response.getWriter()).thenReturn(printWriter);

        teacherSubjectAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("OK", 200, subjects)));
    }

    @Test
    public void testDoGet_TermIdLeadingAndTrailingSpaces() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("termId")).thenReturn(" 1 ");

        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject());
        when(dao.getSubjectsByTeacherIdAndTermId(1, 1)).thenReturn(subjects);

        when(response.getWriter()).thenReturn(printWriter);

        teacherSubjectAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("OK", 200, subjects)));
    }

    @Test
    public void testDoGet_EmptySubjectsResponse() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("termId")).thenReturn("1");

        List<Subject> subjects = new ArrayList<>();
        when(dao.getSubjectsByTeacherIdAndTermId(1, 1)).thenReturn(subjects);

        when(response.getWriter()).thenReturn(printWriter);

        teacherSubjectAPI.doGet(request, response);

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("OK", 200, subjects)));
    }
}

