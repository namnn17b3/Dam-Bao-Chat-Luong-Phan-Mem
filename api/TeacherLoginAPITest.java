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

public class TeacherLoginAPITest extends TeacherLoginAPI {
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Test
	    public void doPostTest_ValidEmailPassword() throws Exception
	    {
	        HttpServletRequest req = mock(HttpServletRequest.class);
	        HttpServletResponse res = mock(HttpServletResponse.class);
	        HttpSession session = mock(HttpSession.class);
	        StringWriter sw= new StringWriter();
	
	        when(req.getParameter("email")).thenReturn("anv@stu.ptit.edu.vn");
	        when(req.getParameter("password")). thenReturn("12345678");
	        when(req.getSession()).thenReturn(session);
	        when(res.getWriter()).thenReturn(new PrintWriter(sw));
	
	        doPost(req, res);
	
	        Gson gson1= new Gson();
	        ResponseCommonDto responseCommonDto=gson1.fromJson(sw.toString(), ResponseCommonDto.class);
	
	        Assert.assertNotNull(responseCommonDto);
	        Assert.assertEquals(responseCommonDto.getStatus(), 200);
	    }
	
	    @Test
	    public void doPostTest_WrongEmail() throws Exception
	    {
	        HttpServletRequest req = mock(HttpServletRequest.class);
	        HttpServletResponse res = mock(HttpServletResponse.class);
	        HttpSession session = mock(HttpSession.class);
	        StringWriter sw= new StringWriter();
	
	
	        when(req.getParameter("email")).thenReturn("anv@stu.ptit.edu");
	        when(req.getParameter("password")). thenReturn("12345678");
	        when(req.getSession()).thenReturn(session);
	        when(res.getWriter()).thenReturn(new PrintWriter(sw));
	
	        doPost(req, res);
	
	        Gson gson1= new Gson();
	        ResponseCommonDto responseCommonDto=gson1.fromJson(sw.toString(), ResponseCommonDto.class);
	
	        Assert.assertNotNull(responseCommonDto);
	        Assert.assertEquals(responseCommonDto.getStatus(), 400);
	    }
	
	    @Test
	    public void doPostTest_WrongPassword() throws Exception
	    {
	        HttpServletRequest req = mock(HttpServletRequest.class);
	        HttpServletResponse res = mock(HttpServletResponse.class);
	        HttpSession session = mock(HttpSession.class);
	        StringWriter sw= new StringWriter();
	
	
	        when(req.getParameter("email")).thenReturn("anv@stu.ptit.edu.vn");
	        when(req.getParameter("password")). thenReturn("1234567890");
	        when(req.getSession()).thenReturn(session);
	        when(res.getWriter()).thenReturn(new PrintWriter(sw));
	
	        doPost(req, res);
	
	        Gson gson1= new Gson();
	        ResponseCommonDto responseCommonDto=gson1.fromJson(sw.toString(), ResponseCommonDto.class);
	
	        Assert.assertNotNull(responseCommonDto);
	        Assert.assertEquals(responseCommonDto.getStatus(), 400);
	    }
	
	    @Test
	    public void doPostTest_WrongEmailPassword() throws Exception
	    {
	        HttpServletRequest req = mock(HttpServletRequest.class);
	        HttpServletResponse res = mock(HttpServletResponse.class);
	        HttpSession session = mock(HttpSession.class);
	        StringWriter sw= new StringWriter();
	
	
	        when(req.getParameter("email")).thenReturn("anv@stu.ptit.edu");
	        when(req.getParameter("password")). thenReturn("1234567890");
	        when(req.getSession()).thenReturn(session);
	        when(res.getWriter()).thenReturn(new PrintWriter(sw));
	
	        doPost(req, res);
	
	        Gson gson1= new Gson();
	        ResponseCommonDto responseCommonDto=gson1.fromJson(sw.toString(), ResponseCommonDto.class);
	
	        Assert.assertNotNull(responseCommonDto);
	        Assert.assertEquals(responseCommonDto.getStatus(), 400);
	    }
	
	    @Test
	    public void doPostTest_EmailNull() throws Exception
	    {
	        HttpServletRequest req = mock(HttpServletRequest.class);
	        HttpServletResponse res = mock(HttpServletResponse.class);
	        HttpSession session = mock(HttpSession.class);
	        StringWriter sw= new StringWriter();
	
	        when(req.getParameter("password")). thenReturn("12345678");
	        when(req.getSession()).thenReturn(session);
	        when(res.getWriter()).thenReturn(new PrintWriter(sw));
	
	        doPost(req, res);
	
	        Gson gson1= new Gson();
	        ResponseCommonDto responseCommonDto=gson1.fromJson(sw.toString(), ResponseCommonDto.class);
	
	        Assert.assertNotNull(responseCommonDto);
	        Assert.assertEquals(responseCommonDto.getStatus(), 400);
	    }
	
	    @Test
	    public void doPostTest_PasswordNull() throws Exception
	    {
	        HttpServletRequest req = mock(HttpServletRequest.class);
	        HttpServletResponse res = mock(HttpServletResponse.class);
	        HttpSession session = mock(HttpSession.class);
	        StringWriter sw= new StringWriter();
	
	        when(req.getParameter("email")).thenReturn("anv@stu.ptit.edu.vn");
	        when(req.getSession()).thenReturn(session);
	        when(res.getWriter()).thenReturn(new PrintWriter(sw));
	
	        doPost(req, res);
	
	        Gson gson1= new Gson();
	        ResponseCommonDto responseCommonDto=gson1.fromJson(sw.toString(), ResponseCommonDto.class);
	
	        Assert.assertNotNull(responseCommonDto);
	        Assert.assertEquals(responseCommonDto.getStatus(), 400);
	    }
	
	    @Test
	    public void doPostTest_NoSession() throws Exception
	    {
	        HttpServletRequest req = mock(HttpServletRequest.class);
	        HttpServletResponse res = mock(HttpServletResponse.class);
	        StringWriter sw= new StringWriter();
	
	        when(req.getParameter("email")).thenReturn("anv@stu.ptit.edu.vn");
	        when(req.getParameter("password")). thenReturn("12345678");
	        when(res.getWriter()).thenReturn(new PrintWriter(sw));
	
	        doPost(req, res);
	
	        Gson gson1= new Gson();
	        ResponseCommonDto responseCommonDto=gson1.fromJson(sw.toString(), ResponseCommonDto.class);
	
	        Assert.assertNotNull(responseCommonDto);
	        Assert.assertEquals(responseCommonDto.getStatus(), 400);
	    }
	
	    @Test
	    public void doPostTest_EmailPasswordNull() throws Exception
	    {
	        HttpServletRequest req = mock(HttpServletRequest.class);
	        HttpServletResponse res = mock(HttpServletResponse.class);
	        HttpSession session = mock(HttpSession.class);
	        StringWriter sw= new StringWriter();
	
	        when(req.getSession()).thenReturn(session);
	        when(res.getWriter()).thenReturn(new PrintWriter(sw));
	
	        doPost(req, res);
	
	        Gson gson1= new Gson();
	        ResponseCommonDto responseCommonDto=gson1.fromJson(sw.toString(), ResponseCommonDto.class);
	
	        Assert.assertNotNull(responseCommonDto);
	        Assert.assertEquals(responseCommonDto.getStatus(), 200);
	    }
	

	@Test
	public void doPostTest_NoTeacherFound() throws Exception
	{
	    HttpServletRequest req = mock(HttpServletRequest.class);
	    HttpServletResponse res = mock(HttpServletResponse.class);
	    HttpSession session = mock(HttpSession.class);
	    StringWriter sw= new StringWriter();
	
	    when(req.getParameter("email")).thenReturn("anv@stu.ptit.edu.vn");
	    when(req.getParameter("password")). thenReturn("12345678");
	    when(req.getSession()).thenReturn(session);
	    when(res.getWriter()).thenReturn(new PrintWriter(sw));
	    when(dao.getTeacherByEmail(toString())).thenReturn(null);
	    
	    doPost(req, res);
	
	    Gson gson1= new Gson();
	    ResponseCommonDto responseCommonDto=gson1.fromJson(sw.toString(), ResponseCommonDto.class);
	
	    Assert.assertNotNull(responseCommonDto);
	    Assert.assertEquals(responseCommonDto.getStatus(), 400);
	}
	
	@Test
	public void doPostTest_WrongTeacherPassword() throws Exception
	{
	    HttpServletRequest req = mock(HttpServletRequest.class);
	    HttpServletResponse res = mock(HttpServletResponse.class);
	    HttpSession session = mock(HttpSession.class);
	    StringWriter sw= new StringWriter();
	    Teacher teacher = new Teacher();
	    teacher.setPassword("wrong password");
	
	    when(req.getParameter("email")).thenReturn("anv@stu.ptit.edu.vn");
	    when(req.getParameter("password")). thenReturn("12345678");
	    when(req.getSession()).thenReturn(session);
	    when(res.getWriter()).thenReturn(new PrintWriter(sw));
	    when(dao.getTeacherByEmail(toString())).thenReturn(teacher);
	
	    doPost(req, res);
	
	    Gson gson1= new Gson();
	    ResponseCommonDto responseCommonDto=gson1.fromJson(sw.toString(), ResponseCommonDto.class);
	
	    Assert.assertNotNull(responseCommonDto);
	    Assert.assertEquals(responseCommonDto.getStatus(), 400);
	}
}
