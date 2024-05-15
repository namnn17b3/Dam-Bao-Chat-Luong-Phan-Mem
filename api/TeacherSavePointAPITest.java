package com.example.dbclpm.api;

import static org.mockito.Mockito.doNothing;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.dbclpm.dao.Dao;
import com.example.dbclpm.model.Clazz;
import com.example.dbclpm.model.Subject;
import com.example.dbclpm.model.Teacher;
import com.example.dbclpm.model.Term;
import com.google.gson.Gson;


public class TeacherSavePointAPITest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;
    @Mock
    PrintWriter writer;

    @Mock
    Dao dao;

    @InjectMocks
    TeacherSavePointAPI teacherSavePointAPI;

    private final Gson gson = new Gson();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoPut_NoSession() throws Exception {
        // Arrange
        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(null);
        PrintWriter printWriter = Mockito.mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);

        // Act
        teacherSavePointAPI.doPut(request, response);

        // Assert
        verify(response).setStatus(400);
        
    }
    @Test
    public void testDoPut_NoTeacherInSession() throws Exception {
        // Arrange
        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(null);
        PrintWriter printWriter = Mockito.mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);

        // Act
        teacherSavePointAPI.doPut(request, response);

        // Assert
        verify(response).setStatus(400);
    }

    @Test
    public void testDoPut_TeacherWithoutAccess() throws Exception {
        // Arrange
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        Term term = new Term();
        term.setId(1);
        when(dao.getTermByPointId(anyInt())).thenReturn(term);
        Clazz clazz = new Clazz();
        clazz.setTeacherId(2); // Lớp không phải của giáo viên này
        when(dao.getClassByPointId(anyInt())).thenReturn(clazz);
        PrintWriter printWriter = Mockito.mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);

        // Act
        teacherSavePointAPI.doPut(request, response);

        // Assert
        verify(response).setStatus(400);
    }

    @Test
    public void testDoPut_InvalidTermTime() throws Exception {
        // Arrange
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(new Teacher());
        when(dao.getTermByPointId(anyInt())).thenReturn(null);
        when(response.getWriter()).thenReturn(writer);
        // Act
        teacherSavePointAPI.doPut(request, response);

        // Assert
        verify(response).setStatus(400);
    }

    @Test
    public void testDoPut_NotMatchPointAndTeacher() throws Exception {
        // Arrange
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        Term term = new Term();
        term.setId(1);
        when(dao.getTermByPointId(anyInt())).thenReturn(term);
        Clazz clazz = new Clazz();
        clazz.setTeacherId(2);
        when(dao.getClassByPointId(anyInt())).thenReturn(clazz);
        when(response.getWriter()).thenReturn(writer);
        // Act
        teacherSavePointAPI.doPut(request, response);

        // Assert
        verify(response).setStatus(400);
    }

    @Test
    public void testDoPut_InvalidPoint() throws Exception {
        // Arrange
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        Term term = new Term();
        term.setId(1);
        when(dao.getTermByPointId(anyInt())).thenReturn(term);
        Clazz clazz = new Clazz();
        clazz.setTeacherId(1);
        when(dao.getClassByPointId(anyInt())).thenReturn(clazz);
        when(request.getParameter("cc")).thenReturn("11");
        when(response.getWriter()).thenReturn(writer);
        // Act
        teacherSavePointAPI.doPut(request, response);

        // Assert
        verify(response).setStatus(400);
    }

   
    @Test
    public void testDoPut_SuccessfulUpdate() throws Exception {
        // Arrange
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        Term term = new Term();
        term.setId(1);
        when(dao.getTermByPointId(anyInt())).thenReturn(term);
        Clazz clazz = new Clazz();
        clazz.setTeacherId(1);
        when(dao.getClassByPointId(anyInt())).thenReturn(clazz);
        when(request.getParameter("cc")).thenReturn("9");
        when(request.getParameter("btl")).thenReturn("8");
        when(request.getParameter("th")).thenReturn("7");
        when(request.getParameter("ktgk")).thenReturn("8");
        when(request.getParameter("ktck")).thenReturn("9");
        when(dao.getSubjectByPointId(anyInt())).thenReturn(new Subject());
        PrintWriter printWriter = Mockito.mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        doNothing().when(dao).savePoint(anyInt(), anyFloat(), anyFloat(), anyFloat(), anyFloat(), anyFloat());

        // Act
        teacherSavePointAPI.doPut(request, response);

        // Assert
        verify(response).setStatus(200);
    }
    
    @Test
    public void testDoPut_NoSubjectFound() throws Exception {
        // Arrange
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        Term term = new Term();
        term.setId(1);
        when(dao.getTermByPointId(anyInt())).thenReturn(term);
        Clazz clazz = new Clazz();
        clazz.setTeacherId(1);
        when(dao.getClassByPointId(anyInt())).thenReturn(clazz);
        when(request.getParameter("cc")).thenReturn("9");
        when(request.getParameter("btl")).thenReturn("8");
        when(request.getParameter("th")).thenReturn("7");
        when(request.getParameter("ktgk")).thenReturn("8");
        when(request.getParameter("ktck")).thenReturn("9");
        when(dao.getSubjectByPointId(anyInt())).thenReturn(null); // Không có môn học được tìm thấy
        PrintWriter printWriter = Mockito.mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);

        // Act
        teacherSavePointAPI.doPut(request, response);

        // Assert
        verify(response).setStatus(400);
    }

    @Test
    public void testDoPut_FailedToSavePoint() throws Exception {
        // Arrange
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        Term term = new Term();
        term.setId(1);
        when(dao.getTermByPointId(anyInt())).thenReturn(term);
        Clazz clazz = new Clazz();
        clazz.setTeacherId(1);
        when(dao.getClassByPointId(anyInt())).thenReturn(clazz);
        when(request.getParameter("cc")).thenReturn("9");
        when(request.getParameter("btl")).thenReturn("8");
        when(request.getParameter("th")).thenReturn("7");
        when(request.getParameter("ktgk")).thenReturn("8");
        when(request.getParameter("ktck")).thenReturn("9");
        when(dao.getSubjectByPointId(anyInt())).thenReturn(new Subject());
        PrintWriter printWriter = Mockito.mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        // Mock phương thức lưu điểm trong dao để trả về false
        doThrow(new RuntimeException("Failed to save point")).when(dao).savePoint(anyInt(), anyFloat(), anyFloat(), anyFloat(), anyFloat(), anyFloat());

        // Act
        teacherSavePointAPI.doPut(request, response);

        // Assert
        verify(response).setStatus(500);
    }


    @Test
    public void testDoPut_NoClassFound() throws Exception {
        // Arrange
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        Term term = new Term();
        term.setId(1);
        when(dao.getTermByPointId(anyInt())).thenReturn(term);
        when(dao.getClassByPointId(anyInt())).thenReturn(null); // Không có thông tin về lớp học được tìm thấy
        PrintWriter printWriter = Mockito.mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);

        // Act
        teacherSavePointAPI.doPut(request, response);

        // Assert
        verify(response).setStatus(400);
    }
    
    @Test
    public void testDoPut_NoTermFound() throws Exception {
        // Arrange
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(teacher);
        when(dao.getTermByPointId(anyInt())).thenReturn(null); // Không có thông tin về kỳ học được tìm thấy
        PrintWriter printWriter = Mockito.mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);

        // Act
        teacherSavePointAPI.doPut(request, response);

        // Assert
        verify(response).setStatus(400);
    }


}