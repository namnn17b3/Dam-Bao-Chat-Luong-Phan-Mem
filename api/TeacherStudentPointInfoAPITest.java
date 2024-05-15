package com.example.dbclpm.api;

import static org.junit.Assert.assertNull;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dbclpm.api.TeacherStudentPointInfoAPI;
import com.example.dbclpm.dao.Dao;
import com.example.dbclpm.dto.ResponseCommonDto;
import com.example.dbclpm.model.Point;
import com.example.dbclpm.model.Student;
import com.example.dbclpm.model.Subject;
import com.example.dbclpm.model.Teacher;
import com.example.dbclpm.model.Term;
import com.google.gson.Gson;

public class TeacherStudentPointInfoAPITest {

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
    private TeacherStudentPointInfoAPI teacherStudentPointInfoAPI;

    private static final Gson gson = new Gson();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoGet_NoSession() throws Exception {
        // Mock the session and request
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(null);

        // Mock the PrintWriter to prevent NullPointerException
        when(response.getWriter()).thenReturn(printWriter);

        // Execute the method
        teacherStudentPointInfoAPI.doGet(request, response);

        // Verify interactions
        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("classId invalid integer", 400, null)));
    }

    @Test
    public void testDoGet_InvalidClassId() throws Exception {
        // Mock the session and request
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("classId")).thenReturn("invalid");
        when(request.getParameter("studentId")).thenReturn("1");

        // Mock the PrintWriter to prevent NullPointerException
        when(response.getWriter()).thenReturn(printWriter);

        // Execute the method
        teacherStudentPointInfoAPI.doGet(request, response);

        // Verify interactions
        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("classId invalid integer", 400, null)));
    }

    @Test
    public void testDoGet_TermNotMatch() throws Exception {
        // Mock the session and request
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("classId")).thenReturn("1");
        when(request.getParameter("studentId")).thenReturn("1");

        // Mock the DAO responses
        Term term = new Term();
        term.setStartDate(new Date(System.currentTimeMillis() + 10000)); // Future date
        term.setEndDate(new Date(System.currentTimeMillis() + 20000)); // Future date
        when(dao.getTermByClassId(1)).thenReturn(term);

        // Mock the PrintWriter to prevent NullPointerException
        when(response.getWriter()).thenReturn(printWriter);

        // Execute the method
        teacherStudentPointInfoAPI.doGet(request, response);

        // Verify interactions
        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("Term is not match", 400, null)));
    }

    @Test
    public void testDoGet_TeacherNotMatch() throws Exception {
        // Mock the session and request
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("classId")).thenReturn("1");
        when(request.getParameter("studentId")).thenReturn("1");

        // Mock the DAO responses
        Term term = new Term();
        term.setStartDate(new Date(System.currentTimeMillis() - 10000)); // Past date
        term.setEndDate(new Date(System.currentTimeMillis() + 10000)); // Future date
        when(dao.getTermByClassId(1)).thenReturn(term);

        Teacher anotherTeacher = new Teacher();
        anotherTeacher.setId(2); // Different ID
        when(dao.getTeacherByClassId(1)).thenReturn(anotherTeacher);

        // Mock the PrintWriter to prevent NullPointerException
        when(response.getWriter()).thenReturn(printWriter);

        // Execute the method
        teacherStudentPointInfoAPI.doGet(request, response);

        // Verify interactions
        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("Teacher is not match", 400, null)));
    }

    @Test
    public void testDoGet_StudentNotInClass() throws Exception {
        // Mock the session and request
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("classId")).thenReturn("1");
        when(request.getParameter("studentId")).thenReturn("1");

        // Mock the DAO responses
        Term term = new Term();
        term.setStartDate(new Date(System.currentTimeMillis() - 10000)); // Past date
        term.setEndDate(new Date(System.currentTimeMillis() + 10000)); // Future date
        when(dao.getTermByClassId(1)).thenReturn(term);

        when(dao.getTeacherByClassId(1)).thenReturn(teacher);
        when(dao.getPointByCLassIdAndStudentId(1, 1)).thenReturn(null); // Student not in class

        // Mock the PrintWriter to prevent NullPointerException
        when(response.getWriter()).thenReturn(printWriter);

        // Execute the method
        teacherStudentPointInfoAPI.doGet(request, response);

        // Verify interactions
        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        verify(printWriter).println(gson.toJson(new ResponseCommonDto("Student not in current class CN1", 400, null)));
    }

    @Test
    public void testDoGet_ValidRequest() throws Exception {
        // Mock the session and request
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("classId")).thenReturn("1");
        when(request.getParameter("studentId")).thenReturn("1");

        // Mock the DAO responses
        Term term = new Term();
        term.setStartDate(new Date(System.currentTimeMillis() - 10000)); // Past date
        term.setEndDate(new Date(System.currentTimeMillis() + 10000)); // Future date
        when(dao.getTermByClassId(1)).thenReturn(term);

        when(dao.getTeacherByClassId(1)).thenReturn(teacher);

        Point point = new Point();
        when(dao.getPointByCLassIdAndStudentId(1, 1)).thenReturn(point);

        Student student = new Student();
        when(dao.getStudentById(1)).thenReturn(student);

        Subject subject = new Subject();
        when(dao.getSubjectByClassId(1)).thenReturn(subject);

        // Mock the PrintWriter to prevent NullPointerException
        when(response.getWriter()).thenReturn(printWriter);

        // Execute the method
        teacherStudentPointInfoAPI.doGet(request, response);

        // Verify interactions
        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(anyString());
    }

    @Test
    public void testDoGet_StudentDetailsSanitized() throws Exception {
        // Mock the session and request
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("classId")).thenReturn("1");
        when(request.getParameter("studentId")).thenReturn("1");

        // Mock the DAO responses
        Term term = new Term();
        term.setStartDate(new Date(System.currentTimeMillis() - 10000)); // Past date
        term.setEndDate(new Date(System.currentTimeMillis() + 10000)); // Future date
        when(dao.getTermByClassId(1)).thenReturn(term);

        when(dao.getTeacherByClassId(1)).thenReturn(teacher);

        Point point = new Point();
        when(dao.getPointByCLassIdAndStudentId(1, 1)).thenReturn(point);

        Student student = new Student();
        student.setAddress("123 Main St");
        student.setEmail("student@example.com");
        student.setPassword("password");
        student.setPhone("123-456-7890");
        when(dao.getStudentById(1)).thenReturn(student);

        Subject subject = new Subject();
        when(dao.getSubjectByClassId(1)).thenReturn(subject);

        // Mock the PrintWriter to prevent NullPointerException
        when(response.getWriter()).thenReturn(printWriter);

        // Execute the method
        teacherStudentPointInfoAPI.doGet(request, response);

        // Verify that student details are sanitized
        assertNull(student.getAddress());
        assertNull(student.getEmail());
        assertNull(student.getPassword());
        assertNull(student.getPhone());

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(anyString());
    }

    @Test
    public void testDoGet_PointCalculations() throws Exception {
        // Mock the session and request
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("classId")).thenReturn("1");
        when(request.getParameter("studentId")).thenReturn("1");

        // Mock the DAO responses
        Term term = new Term();
        term.setStartDate(new Date(System.currentTimeMillis() - 10000)); // Past date
        term.setEndDate(new Date(System.currentTimeMillis() + 10000)); // Future date
        when(dao.getTermByClassId(1)).thenReturn(term);

        when(dao.getTeacherByClassId(1)).thenReturn(teacher);

        Point point = new Point();
        point.setCc(Float.valueOf("10"));
        point.setBtl(Float.valueOf("10"));
        point.setTh(Float.valueOf("10"));
        point.setKtgk(Float.valueOf("10"));
        point.setKtck(Float.valueOf("10"));
        when(dao.getPointByCLassIdAndStudentId(1, 1)).thenReturn(point);

        Student student = new Student();
        when(dao.getStudentById(1)).thenReturn(student);

        Subject subject = new Subject();
        subject.setPercentCC(10);
        subject.setPercentBTL(20);
        subject.setPercentTH(10);
        subject.setPercentKTGK(30);
        subject.setPercentKTCK(30);
        when(dao.getSubjectByClassId(1)).thenReturn(subject);

        // Mock the PrintWriter to prevent NullPointerException
        when(response.getWriter()).thenReturn(printWriter);

        // Execute the method
        teacherStudentPointInfoAPI.doGet(request, response);

        // Verify interactions
        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(anyString());
    }

    @Test
    public void testDoGet_EmptyPointCalculations() throws Exception {
        // Mock the session and request
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("classId")).thenReturn("1");
        when(request.getParameter("studentId")).thenReturn("1");

        // Mock the DAO responses
        Term term = new Term();
        term.setStartDate(new Date(System.currentTimeMillis() - 10000)); // Past date
        term.setEndDate(new Date(System.currentTimeMillis() + 10000)); // Future date
        when(dao.getTermByClassId(1)).thenReturn(term);

        when(dao.getTeacherByClassId(1)).thenReturn(teacher);

        Point point = new Point();
        when(dao.getPointByCLassIdAndStudentId(1, 1)).thenReturn(point);

        Student student = new Student();
        when(dao.getStudentById(1)).thenReturn(student);

        Subject subject = new Subject();
        when(dao.getSubjectByClassId(1)).thenReturn(subject);

        // Mock the PrintWriter to prevent NullPointerException
        when(response.getWriter()).thenReturn(printWriter);

        // Execute the method
        teacherStudentPointInfoAPI.doGet(request, response);

        // Verify interactions
        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(anyString());
    }

    @Test
    public void testDoGet_NullPercentages() throws Exception {
        // Mock the session and request
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(request.getParameter("classId")).thenReturn("1");
        when(request.getParameter("studentId")).thenReturn("1");

        // Mock the DAO responses
        Term term = new Term();
        term.setStartDate(new Date(System.currentTimeMillis() - 10000)); // Past date
        term.setEndDate(new Date(System.currentTimeMillis() + 10000)); // Future date
        when(dao.getTermByClassId(1)).thenReturn(term);

        when(dao.getTeacherByClassId(1)).thenReturn(teacher);

        Point point = new Point();
        point.setCc(Float.valueOf("10"));
        point.setBtl(Float.valueOf("10"));
        point.setTh(Float.valueOf("10"));
        point.setKtgk(Float.valueOf("10"));
        point.setKtck(Float.valueOf("10"));
        when(dao.getPointByCLassIdAndStudentId(1, 1)).thenReturn(point);

        Student student = new Student();
        when(dao.getStudentById(1)).thenReturn(student);

        Subject subject = new Subject();
        when(dao.getSubjectByClassId(1)).thenReturn(subject);

        // Mock the PrintWriter to prevent NullPointerException
        when(response.getWriter()).thenReturn(printWriter);

        // Execute the method
        teacherStudentPointInfoAPI.doGet(request, response);

        // Verify interactions
        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println(anyString());
    }
}
