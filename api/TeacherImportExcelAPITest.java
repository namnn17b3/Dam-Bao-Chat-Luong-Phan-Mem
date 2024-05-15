package com.example.dbclpm.api;

import com.example.dbclpm.model.Teacher;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class TeacherImportExcelAPITest {
    HttpServletRequest req;
    HttpServletResponse resp;
    TeacherImportExcelAPI teacherImportExcelAPI = new TeacherImportExcelAPI();
    Part filePart;
    HttpSession session;

    @BeforeEach
    void setUp() {
        req = Mockito.mock(HttpServletRequest.class);
        resp = Mockito.mock(HttpServletResponse.class);
        filePart = Mockito.mock(Part.class);
        session = Mockito.mock(HttpSession.class);
    }

    InputStream createExcel(Object[][] data) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        // Tạo một sheet mới trong workbook
        Sheet sheet = workbook.createSheet("Sheet1");

        // Tạo dữ liệu mẫu và điền vào sheet

        int rowNum = 0;
        for (Object[] rowData : data) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : rowData) {
                Cell cell = row.createCell(colNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // Ghi workbook vào một file Excel
        try (FileOutputStream outputFileStream = new FileOutputStream("test.xlsx")) {
            workbook.write(outputFileStream);
            workbook.write(outputStream);
            workbook.close();
            System.out.println("Excel file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] b = outputStream.toByteArray();
        InputStream inputStream = new ByteArrayInputStream(b);
        return inputStream;
    }
    @Test
    void doPost() throws ServletException, IOException, InvalidFormatException {
        Teacher teacher = new Teacher(1, "name", "email", "pass");
        Mockito.when(req.getAttribute("teacher")).thenReturn(teacher);
        Object[][] data = {
                {"MSV", "D1", "D2", "D3", "D4", "D5"},
                {"SV7", 10, 9, 8, 7, 6},
                {"SV32", 9, 8, 7, 6, 5},
                {"SV30", 8, 7, 6, 5, 4}
        };
        InputStream inputStream = createExcel(data);
        Mockito.when(req.getPart("excelFile")).thenReturn(filePart);
        Mockito.when(filePart.getInputStream()).thenReturn(inputStream);
        Mockito.when(filePart.getName()).thenReturn("excelFile");
        Mockito.when(filePart.getSize()).thenReturn((long) 0);
        Mockito.when(filePart.getName()).thenReturn("nam");
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(printWriter);
        Mockito.when(req.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("teacher")).thenReturn(new Teacher(1, "name", "email", "pass"));
        Mockito.when(req.getParameter("classId")).thenReturn("2");
        teacherImportExcelAPI.doPost(req,resp);
        printWriter.flush();
        System.out.println(stringWriter.toString());
        assertEquals("{\"message\":\"OK\",\"status\":200}", stringWriter.toString().trim());
    }

    @Test
    void doPostInvalidTeacherId() throws ServletException, IOException, InvalidFormatException {
        Teacher teacher = new Teacher(-1, "name", "email", "pass");
        Mockito.when(req.getAttribute("teacher")).thenReturn(teacher);
        Object[][] data = {
                {"MSV", "D1", "D2", "D3", "D4", "D5"},
                {"SV7", 10, 9, 8, 7, 6},
                {"SV32", 9, 8, 7, 6, 5},
                {"SV30", 8, 7, 6, 5, 4}
        };
        InputStream inputStream = createExcel(data);
        Mockito.when(req.getPart("excelFile")).thenReturn(filePart);
        Mockito.when(filePart.getInputStream()).thenReturn(inputStream);
        Mockito.when(filePart.getName()).thenReturn("excelFile");
        Mockito.when(filePart.getSize()).thenReturn((long) 0);
        Mockito.when(filePart.getName()).thenReturn("nam");
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(printWriter);
        Mockito.when(req.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("teacher")).thenReturn(new Teacher(-1, "name", "email", "pass"));
        Mockito.when(req.getParameter("classId")).thenReturn("2");
        teacherImportExcelAPI.doPost(req,resp);
        printWriter.flush();
        System.out.println(stringWriter.toString());
        assertEquals("{\"message\":\"Teacher is not match\",\"status\":400}", stringWriter.toString().trim());
    }

    @Test
    void doPostInvalidClassId() throws ServletException, IOException, InvalidFormatException {
        Object[][] data = {
                {"MSV", "D1", "D2", "D3", "D4", "D5"},
                {"SV7", 10, 9, 8, 7, 6},
                {"SV32", 9, 8, 7, 6, 5},
                {"SV30", 8, 7, 6, 5, 4}
        };
        InputStream inputStream = createExcel(data);
        Mockito.when(req.getPart("excelFile")).thenReturn(filePart);
        Mockito.when(filePart.getInputStream()).thenReturn(inputStream);
        Mockito.when(filePart.getName()).thenReturn("excelFile");
        Mockito.when(filePart.getSize()).thenReturn((long) 0);
        Mockito.when(filePart.getName()).thenReturn("nam");
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(printWriter);
        Mockito.when(req.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("teacher")).thenReturn(new Teacher(1, "name", "email", "pass"));
        Mockito.when(req.getParameter("classId")).thenReturn("");
        teacherImportExcelAPI.doPost(req,resp);
        printWriter.flush();
        System.out.println(stringWriter.toString());
        assertEquals("{\"message\":\"classId invalid integer\",\"status\":400}", stringWriter.toString().trim());
    }
    @Test
    void doPostClassIdNotEsixt() throws ServletException, IOException, InvalidFormatException {
        Object[][] data = {
                {"MSV", "D1", "D2", "D3", "D4", "D5"},
                {"SV7", 10, 9, 8, 7, 6},
                {"SV32", 9, 8, 7, 6, 5},
                {"SV30", 8, 7, 6, 5, 4}
        };
        InputStream inputStream = createExcel(data);
        Mockito.when(req.getPart("excelFile")).thenReturn(filePart);
        Mockito.when(filePart.getInputStream()).thenReturn(inputStream);
        Mockito.when(filePart.getName()).thenReturn("excelFile");
        Mockito.when(filePart.getSize()).thenReturn((long) 0);
        Mockito.when(filePart.getName()).thenReturn("nam");
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(printWriter);
        Mockito.when(req.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("teacher")).thenReturn(new Teacher(1, "name", "email", "pass"));
        Mockito.when(req.getParameter("classId")).thenReturn("999");
        teacherImportExcelAPI.doPost(req,resp);
        printWriter.flush();
        System.out.println(stringWriter.toString());
        assertEquals("{\"message\":\"classId invalid integer\",\"status\":400}", stringWriter.toString().trim());
    }
    @Test
    void doPostInvalidStudentCode() throws ServletException, IOException, InvalidFormatException {
        Object[][] data = {
                {"MSV", "D1", "D2", "D3", "D4", "D5"},
                {"SV", 10, 9, 8, 7, 6},
                {"SV32", 9, 8, 7, 6, 5},
                {"SV30", 8, 7, 6, 5, 4}
        };
        InputStream inputStream = createExcel(data);
        Mockito.when(req.getPart("excelFile")).thenReturn(filePart);
        Mockito.when(filePart.getInputStream()).thenReturn(inputStream);
        Mockito.when(filePart.getName()).thenReturn("excelFile");
        Mockito.when(filePart.getSize()).thenReturn((long) 0);
        Mockito.when(filePart.getName()).thenReturn("nam");
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(printWriter);
        Mockito.when(req.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("teacher")).thenReturn(new Teacher(1, "name", "email", "pass"));
        Mockito.when(req.getParameter("classId")).thenReturn("2");
        teacherImportExcelAPI.doPost(req,resp);
        printWriter.flush();
        System.out.println(stringWriter.toString());
        assertEquals("{\"message\":\"student code error\",\"status\":400}", stringWriter.toString().trim());
    }
    @Test
    void doPostInvalidPointInt() throws ServletException, IOException, InvalidFormatException {
        Object[][] data = {
                {"MSV", "D1", "D2", "D3", "D4", "D5"},
                {"SV7", 10, 9, 8, 7, "6"},
                {"SV32", 9, 8, 7, 6, 5},
                {"SV30", 8, 7, 6, 5, 4}
        };
        InputStream inputStream = createExcel(data);
        Mockito.when(req.getPart("excelFile")).thenReturn(filePart);
        Mockito.when(filePart.getInputStream()).thenReturn(inputStream);
        Mockito.when(filePart.getName()).thenReturn("excelFile");
        Mockito.when(filePart.getSize()).thenReturn((long) 0);
        Mockito.when(filePart.getName()).thenReturn("nam");
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(printWriter);
        Mockito.when(req.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("teacher")).thenReturn(new Teacher(1, "name", "email", "pass"));
        Mockito.when(req.getParameter("classId")).thenReturn("2");
        teacherImportExcelAPI.doPost(req,resp);
        printWriter.flush();
        System.out.println(stringWriter.toString());
        assertEquals("{\"message\":\"OK\",\"status\":200}", stringWriter.toString().trim());
    }
    @Test
    void doPostPointSmallerThanZero() throws ServletException, IOException, InvalidFormatException {
        Object[][] data = {
                {"MSV", "D1", "D2", "D3", "D4", "D5"},
                {"SV7", 10, 9, 8, 7, -1},
                {"SV32", 9, 8, 7, 6, 5},
                {"SV30", 8, 7, 6, 5, 4}
        };
        InputStream inputStream = createExcel(data);
        Mockito.when(req.getPart("excelFile")).thenReturn(filePart);
        Mockito.when(filePart.getInputStream()).thenReturn(inputStream);
        Mockito.when(filePart.getName()).thenReturn("excelFile");
        Mockito.when(filePart.getSize()).thenReturn((long) 0);
        Mockito.when(filePart.getName()).thenReturn("nam");
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(printWriter);
        Mockito.when(req.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("teacher")).thenReturn(new Teacher(1, "name", "email", "pass"));
        Mockito.when(req.getParameter("classId")).thenReturn("2");
        teacherImportExcelAPI.doPost(req,resp);
        printWriter.flush();
        System.out.println(stringWriter.toString());
        assertEquals("{\"message\":\"Điểm không được nhỏ hơn 0\",\"status\":400}", stringWriter.toString().trim());
    }
    @Test
    void doPostStudentNotInClass() throws ServletException, IOException, InvalidFormatException {
        Object[][] data = {
                {"MSV", "D1", "D2", "D3", "D4", "D5"},
                {"SV99", 10, 9, 8, 7, 6},
                {"SV32", 9, 8, 7, 6, 5},
                {"SV30", 8, 7, 6, 5, 4}
        };
        InputStream inputStream = createExcel(data);
        Mockito.when(req.getPart("excelFile")).thenReturn(filePart);
        Mockito.when(filePart.getInputStream()).thenReturn(inputStream);
        Mockito.when(filePart.getName()).thenReturn("excelFile");
        Mockito.when(filePart.getSize()).thenReturn((long) 0);
        Mockito.when(filePart.getName()).thenReturn("nam");
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(printWriter);
        Mockito.when(req.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("teacher")).thenReturn(new Teacher(1, "name", "email", "pass"));
        Mockito.when(req.getParameter("classId")).thenReturn("2");
        teacherImportExcelAPI.doPost(req,resp);
        printWriter.flush();
        System.out.println(stringWriter.toString());
        assertEquals("{\"message\":\"Student not in current class  CN2\",\"status\":400}", stringWriter.toString().trim());
    }
    @Test
    void doPostInvalidStudentCode2() throws ServletException, IOException, InvalidFormatException {
        Object[][] data = {
                {"MSV", "D1", "D2", "D3", "D4", "D5"},
                {"", 10, 9, 8, 7, 6},
                {"SV32", 9, 8, 7, 6, 5},
                {"SV30", 8, 7, 6, 5, 4}
        };
        InputStream inputStream = createExcel(data);
        Mockito.when(req.getPart("excelFile")).thenReturn(filePart);
        Mockito.when(filePart.getInputStream()).thenReturn(inputStream);
        Mockito.when(filePart.getName()).thenReturn("excelFile");
        Mockito.when(filePart.getSize()).thenReturn((long) 0);
        Mockito.when(filePart.getName()).thenReturn("nam");
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(printWriter);
        Mockito.when(req.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("teacher")).thenReturn(new Teacher(1, "name", "email", "pass"));
        Mockito.when(req.getParameter("classId")).thenReturn("");
        teacherImportExcelAPI.doPost(req,resp);
        printWriter.flush();
        System.out.println(stringWriter.toString());
        assertEquals("{\"message\":\"studendCode invalid\",\"status\":400}", stringWriter.toString().trim());
    }
    @Test
    void doPostPointLargerThanTen() throws ServletException, IOException, InvalidFormatException {
        Object[][] data = {
                {"MSV", "D1", "D2", "D3", "D4", "D5"},
                {"SV7", 11, 9, 8, 7, 6},
                {"SV32", 9, 8, 7, 6, 5},
                {"SV30", 8, 7, 6, 5, 4}
        };
        InputStream inputStream = createExcel(data);
        Mockito.when(req.getPart("excelFile")).thenReturn(filePart);
        Mockito.when(filePart.getInputStream()).thenReturn(inputStream);
        Mockito.when(filePart.getName()).thenReturn("excelFile");
        Mockito.when(filePart.getSize()).thenReturn((long) 0);
        Mockito.when(filePart.getName()).thenReturn("nam");
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        Mockito.when(resp.getWriter()).thenReturn(printWriter);
        Mockito.when(req.getSession()).thenReturn(session);
        Mockito.when(session.getAttribute("teacher")).thenReturn(new Teacher(1, "name", "email", "pass"));
        Mockito.when(req.getParameter("classId")).thenReturn("2");
        teacherImportExcelAPI.doPost(req,resp);
        printWriter.flush();
        System.out.println(stringWriter.toString());
        assertEquals("{\"message\":\"Điểm không được lớn hơn 10\",\"status\":400}", stringWriter.toString().trim());
    }
}