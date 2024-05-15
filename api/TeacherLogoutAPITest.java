package com.example.dbclpm.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.dbclpm.dto.ResponseCommonDto;
import com.google.gson.Gson;


public class TeacherLogoutAPITest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    PrintWriter writer;

    @InjectMocks
    TeacherLogoutAPI teacherLogoutAPI;

    private final Gson gson = new Gson();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    //Khi có session, servlet sẽ hủy phiên làm việc đó.
    @Test
    public void testDoGet_ValidSession() throws Exception {
        // Arrange
        when(request.getSession()).thenReturn(session);
        PrintWriter printWriter = Mockito.mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);

        // Act
        teacherLogoutAPI.doGet(request, response);

        // Assert
        verify(session).invalidate();
        
    }
    
    //servlet trả về giá trị đúng: status 400, message "OK", và không có dữ liệu
    @Test
    public void testDoGet_ValidResponse() throws Exception {
        // Arrange
        when(request.getSession()).thenReturn(session);
        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);
        
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        // Act
        teacherLogoutAPI.doGet(request, response);
        
        // Assert
        verify(printWriter).println(captor.capture());
        String jsonString = captor.getValue();
        ResponseCommonDto dto = gson.fromJson(jsonString, ResponseCommonDto.class);
        assertEquals(400, dto.getStatus());
        assertNull(dto.getData());
        assertEquals("OK", dto.getMessage());
    }
    
    //Khi không có session
    @Test
    public void testDoGet_NoSession() throws Exception {
        
        HttpSession session = Mockito.mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("teacher")).thenReturn(null);
        PrintWriter printWriter = Mockito.mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);

     // Act
        teacherLogoutAPI.doGet(request, response);

        // Assert
        verify(response).setStatus(400);
        
    }

  
   



}