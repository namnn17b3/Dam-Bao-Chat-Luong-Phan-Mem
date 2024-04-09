package com.example.dbclpm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

@WebServlet(urlPatterns = "/test")
@MultipartConfig
public class TestController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("test"));
        req.getRequestDispatcher("/views/test.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set character encoding to UTF-8
        req.setCharacterEncoding("UTF-8");

        // Get the uploaded file
        Part filePart = req.getPart("excelFile"); // "excelFile" is the name of the input type="file"

        // Convert InputStream of uploaded file to Workbook using Apache POI
        Workbook workbook = WorkbookFactory.create(filePart.getInputStream());

        String resString = "";
        // Process the workbook as needed, for example, read data from sheets
        Sheet sheet = workbook.getSheetAt(0); // Assuming the first sheet
        for (Row row : sheet) {
            for (Cell cell : row) {
                resString += cell.toString();
                // Process each cell, for example, print cell value
                System.out.println(cell.toString());
            }
            System.out.println();
        }

        // Close the workbook
        workbook.close();
        
        String name = req.getParameter("name");
        System.out.println("name: " + name);

        // Respond to the client
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<h1 style='color: red'>"+resString+"</h1>");
    }
}
