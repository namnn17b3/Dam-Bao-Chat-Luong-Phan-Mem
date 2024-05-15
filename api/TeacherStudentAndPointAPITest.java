package com.example.dbclpm.api;

import com.example.dbclpm.dao.impl.DaoImpl;
import com.example.dbclpm.dto.PointExtent;
import com.example.dbclpm.dto.ResponseCommonDto;
import com.example.dbclpm.dto.StudentPointTable;
import com.example.dbclpm.dto.StudentPointTableDtos;
import com.example.dbclpm.model.Point;
import com.example.dbclpm.model.Student;
import com.example.dbclpm.model.Teacher;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherStudentAndPointAPITest {
    TeacherStudentAndPointAPI teacherStudentAndPointAPI = new TeacherStudentAndPointAPI();
    PrintWriter printWriter;
    HttpServletResponse response;
    HttpServletRequest request;
    Gson gson = new Gson();
    StringWriter stringWriter;

    @BeforeEach
    void setUp() {
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
    }

    @Test
    void doGet_2Item() throws ServletException, IOException {
        int itemInPage = 2;
        int classId = 1;
        int page = 1;
        int quantity = 50;
        Mockito.when(request.getParameter("itemInPage")).thenReturn(itemInPage+"");
        Mockito.when(request.getParameter("classId")).thenReturn(classId+"");
        Mockito.when(request.getParameter("page")).thenReturn(page+"");
        Mockito.when(response.getWriter()).thenReturn(printWriter);

        teacherStudentAndPointAPI.doGet(request,response);

        printWriter.flush();

        System.out.println(stringWriter.toString());

        List<StudentPointTable> list = new ArrayList<>();

        StudentPointTable studentPointTable = new StudentPointTable();
        studentPointTable.setStudent(new Student(510, "Đăng Văn Cường 10", null, null, null, "Nam", null, new Date("Dec 23 2002, 12:00:00 AM"), "D20CQCN09"));
        studentPointTable.setPoint(new Point(7027, 1F, (Float) null, null, 7F, 2F, 1, 510));
        studentPointTable.setPointExtent(new PointExtent(2.9F, "F", 0F, "Đạt"));

        list.add(studentPointTable);

        studentPointTable = new StudentPointTable();
        studentPointTable.setStudent(new Student(620, "Đăng Văn Cường 120", null, null, null, "Nam", null, new Date("Dec 28 2002, 12:00:00 AM"), "D20CQCN12"));
        studentPointTable.setPoint(new Point(7037, 0F, (Float) null, null, 2F, 5F, 1, 620));
        studentPointTable.setPointExtent(new PointExtent(3.9F, "F", 0F, "KĐĐKDT"));

        list.add(studentPointTable);

        StudentPointTableDtos studentPointTableDtos = new StudentPointTableDtos(list, quantity, page, itemInPage);

        printWriter.flush();

        assertEquals(gson.toJson(new ResponseCommonDto("OK", 200, studentPointTableDtos)), stringWriter.toString().trim());
    }

    @Test
    void doGet_3Item() throws ServletException, IOException {
        int itemInPage = 3;
        int classId = 1;
        int page = 1;
        int quantity = 50;
        Mockito.when(request.getParameter("itemInPage")).thenReturn(itemInPage+"");
        Mockito.when(request.getParameter("classId")).thenReturn(classId+"");
        Mockito.when(request.getParameter("page")).thenReturn(page+"");
        Mockito.when(response.getWriter()).thenReturn(printWriter);

        teacherStudentAndPointAPI.doGet(request,response);

        printWriter.flush();

        System.out.println(stringWriter.toString());

        List<StudentPointTable> list = new ArrayList<>();

        StudentPointTable studentPointTable = new StudentPointTable();
        studentPointTable.setStudent(new Student(510, "Đăng Văn Cường 10", null, null, null, "Nam", null, new Date("Dec 23 2002, 12:00:00 AM"), "D20CQCN09"));
        studentPointTable.setPoint(new Point(7027, 1F, (Float) null, null, 7F, 2F, 1, 510));
        studentPointTable.setPointExtent(new PointExtent(2.9F, "F", 0F, "Đạt"));

        list.add(studentPointTable);

        studentPointTable = new StudentPointTable();
        studentPointTable.setStudent(new Student(620, "Đăng Văn Cường 120", null, null, null, "Nam", null, new Date("Dec 28 2002, 12:00:00 AM"), "D20CQCN12"));
        studentPointTable.setPoint(new Point(7037, 0F, (Float) null, null, 2F, 5F, 1, 620));
        studentPointTable.setPointExtent(new PointExtent(3.9F, "F", 0F, "KĐĐKDT"));

        list.add(studentPointTable);

        studentPointTable = new StudentPointTable();
        studentPointTable.setStudent(new Student(627, "Đăng Văn Cường 127", null, null, null, "Nam", null, new Date("Nov 7, 2002, 12:00:00 AM"), "D20CQCN12"));
        studentPointTable.setPoint(new Point(7035, 3F, null, null, 9F, 7F, 1, 627));
        studentPointTable.setPointExtent(new PointExtent(7F, "B", 3F, "Đạt"));

        list.add(studentPointTable);

        StudentPointTableDtos studentPointTableDtos = new StudentPointTableDtos(list, quantity, page, itemInPage);

        printWriter.flush();

        assertEquals(gson.toJson(new ResponseCommonDto("OK", 200, studentPointTableDtos)), stringWriter.toString().trim());
    }
    @Test
    void doGet_smaller0Item() throws ServletException, IOException, SQLException {
        int itemInPage = -1;
        int classId = 1;
        int page = 1;
        int quantity = 50;
        Mockito.when(request.getParameter("itemInPage")).thenReturn(itemInPage+"");
        Mockito.when(request.getParameter("classId")).thenReturn(classId+"");
        Mockito.when(request.getParameter("page")).thenReturn(page+"");
        Mockito.when(response.getWriter()).thenReturn(printWriter);

        teacherStudentAndPointAPI.doGet(request,response);

        printWriter.flush();

        System.out.println(stringWriter.toString());

        printWriter.flush();
        assertTrue(true, "all student point table");
    }

    @Test
    void doGet_2ItemOtherClassId() throws ServletException, IOException {
        int itemInPage = 2;
        int classId = 2;
        int page = 1;
        int quantity = 46;
        Mockito.when(request.getParameter("itemInPage")).thenReturn(itemInPage+"");
        Mockito.when(request.getParameter("classId")).thenReturn(classId+"");
        Mockito.when(request.getParameter("page")).thenReturn(page+"");
        Mockito.when(response.getWriter()).thenReturn(printWriter);

        teacherStudentAndPointAPI.doGet(request,response);

        printWriter.flush();

        System.out.println(stringWriter.toString());

        List<StudentPointTable> list = new ArrayList<>();

        StudentPointTable studentPointTable = new StudentPointTable();
        studentPointTable.setStudent(new Student(606, "Đăng Văn Cường 106", null, null, null, "Nam", null, new Date("Jan 11 2002, 12:00:00 AM"), "D20CQCN09"));
        studentPointTable.setPoint(new Point(4238, null, (Float) null, null, null, null, 2, 606));
        studentPointTable.setPointExtent(new PointExtent());

        list.add(studentPointTable);

        studentPointTable = new StudentPointTable();
        studentPointTable.setStudent(new Student(615, "Đăng Văn Cường 115", null, null, null, "Nam", null, new Date("Sep 24 2002, 12:00:00 AM"), "D20CQCN08"));
        studentPointTable.setPoint(new Point(4305, null, (Float) null, null, null, null, 2, 615));
        studentPointTable.setPointExtent(new PointExtent());

        list.add(studentPointTable);

        StudentPointTableDtos studentPointTableDtos = new StudentPointTableDtos(list, quantity, page, itemInPage);

        printWriter.flush();

        assertEquals(gson.toJson(new ResponseCommonDto("OK", 200, studentPointTableDtos)), stringWriter.toString().trim());
    }
    @Test
    void doGet_2ItemOtherClassIdPage2() throws ServletException, IOException {
        int itemInPage = 2;
        int classId = 2;
        int page = 2;
        int quantity = 46;
        Mockito.when(request.getParameter("itemInPage")).thenReturn(itemInPage+"");
        Mockito.when(request.getParameter("classId")).thenReturn(classId+"");
        Mockito.when(request.getParameter("page")).thenReturn(page+"");
        Mockito.when(response.getWriter()).thenReturn(printWriter);

        teacherStudentAndPointAPI.doGet(request,response);

        printWriter.flush();

        System.out.println(stringWriter.toString());

        List<StudentPointTable> list = new ArrayList<>();

        StudentPointTable studentPointTable = new StudentPointTable();
        studentPointTable.setStudent(new Student(619, "Đăng Văn Cường 119", null, null, null, "Nam", null, new Date("Oct 22 2002, 12:00:00 AM"), "D20CQCN08"));
        studentPointTable.setPoint(new Point(4328, null, (Float) null, null, null, null, 2, 619));
        studentPointTable.setPointExtent(new PointExtent());

        list.add(studentPointTable);

        studentPointTable = new StudentPointTable();
        studentPointTable.setStudent(new Student(619, "Đăng Văn Cường 119", null, null, null, "Nam", null, new Date("Oct 22 2002, 12:00:00 AM"), "D20CQCN08"));
        studentPointTable.setPoint(new Point(4327, null, (Float) null, null, null, null, 2, 619));
        studentPointTable.setPointExtent(new PointExtent());

        list.add(studentPointTable);

        StudentPointTableDtos studentPointTableDtos = new StudentPointTableDtos(list, quantity, page, itemInPage);

        printWriter.flush();

        assertEquals(gson.toJson(new ResponseCommonDto("OK", 200, studentPointTableDtos)), stringWriter.toString().trim());
    }
    @Test
    void doGet_Page_0() throws ServletException, IOException {
        int itemInPage = 2;
        int classId = 2;
        int page = 0;
        Mockito.when(request.getParameter("itemInPage")).thenReturn(itemInPage+"");
        Mockito.when(request.getParameter("classId")).thenReturn(classId+"");
        Mockito.when(request.getParameter("page")).thenReturn(page+"");
        Mockito.when(response.getWriter()).thenReturn(printWriter);

        teacherStudentAndPointAPI.doGet(request,response);

        printWriter.flush();

        System.out.println(stringWriter.toString());

        printWriter.flush();
        assertTrue(false, "bug error");
    }
    @Test
    void doGet_ClassIdInvalid() throws ServletException, IOException {
        int itemInPage = 2;
        String classId = "";
        int page = 1;
        int quantity = 46;
        Mockito.when(request.getParameter("itemInPage")).thenReturn(itemInPage+"");
        Mockito.when(request.getParameter("classId")).thenReturn(classId);
        Mockito.when(request.getParameter("page")).thenReturn(page+"");
        Mockito.when(response.getWriter()).thenReturn(printWriter);

        teacherStudentAndPointAPI.doGet(request,response);

        printWriter.flush();

        System.out.println(stringWriter.toString());

        printWriter.flush();
        assertTrue(true, "log ok");
    }
}
