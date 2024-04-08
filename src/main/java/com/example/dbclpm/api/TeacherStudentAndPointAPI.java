package com.example.dbclpm.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dbclpm.dao.Dao;
import com.example.dbclpm.dao.impl.DaoImpl;
import com.example.dbclpm.dto.PointExtent;
import com.example.dbclpm.dto.ResponseCommonDto;
import com.example.dbclpm.dto.StudentPointTable;
import com.example.dbclpm.dto.StudentPointTableDtos;
import com.example.dbclpm.model.Point;
import com.example.dbclpm.model.Subject;
import com.example.dbclpm.utils.PointUtils;
import com.google.gson.Gson;

@WebServlet("/api/teacher/student-point-table")
public class TeacherStudentAndPointAPI extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Dao dao = new DaoImpl();
    private static final Gson gson = new Gson();
    private static final BigDecimal zero = new BigDecimal("0");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        int classId = 0;
        int page = 0;
        int itemInPage = 0;
        
        try {
            itemInPage = Integer.parseInt(req.getParameter("itemInPage").trim());
        } catch (Exception ex) {}
        
        try {
            classId = Integer.parseInt(req.getParameter("classId").trim());
            page = Integer.parseInt(req.getParameter("page").trim());
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setStatus(400);
            pw.println(gson.toJson(new ResponseCommonDto(ex.getMessage(), 400, null)));
            return;
        }
        
        Subject subject = dao.getSubjectByClassId(classId);
        
        StudentPointTableDtos studentPointDtos = dao.getStudentPointTableDtos(classId, page, itemInPage);
        for (StudentPointTable studentPointTable : studentPointDtos.getStudentPointTables()) {
            Point point = studentPointTable.getPoint();
            BigDecimal cc = point.getCc() != null ? new BigDecimal(point.getCc()) : null;
            BigDecimal btl = point.getBtl() != null ? new BigDecimal(point.getBtl()) : null;
            BigDecimal th = point.getTh() != null ? new BigDecimal(point.getTh()) : null;
            BigDecimal ktgk = point.getKtgk() != null ? new BigDecimal(point.getKtgk()) : null;
            BigDecimal ktck = point.getKtck() != null ? new BigDecimal(point.getKtck()) : null;
            
            BigDecimal averagePoint = null;
            Float scoreByNumber = null;
            String scoreByWord = null;
            Float scorePerFourRank = null;
            String note = null;
            
            if (!(cc == null && btl == null && th == null && ktgk == null && ktck == null)) {
                averagePoint = PointUtils.calcAveragePoint(
                    subject,
                    cc != null && subject.getPercentCC() > 0 ? cc : zero,
                    btl != null && subject.getPercentBTL() > 0 ? btl : zero,
                    th != null && subject.getPercentTH() > 0 ? th : zero,
                    ktgk != null && subject.getPercentKTGK() > 0 ? ktgk : zero,
                    ktck != null && subject.getPercentKTCK() > 0 ? ktck : zero
                );
                scoreByNumber = averagePoint.floatValue();
                scoreByWord = PointUtils.genScorebyWord(averagePoint);
                scorePerFourRank = PointUtils.genScorePerFourRank(scoreByWord);
                note = PointUtils.genNote(cc, btl, th, ktgk, ktck);
            }
            
            PointExtent pointExtent = new PointExtent(scoreByNumber, scoreByWord, scorePerFourRank, note);
            studentPointTable.setPointExtent(pointExtent);
        }
        
        pw.println(gson.toJson(new ResponseCommonDto("OK", 200, studentPointDtos)));
    }
}
