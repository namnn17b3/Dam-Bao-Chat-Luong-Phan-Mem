package com.example.dbclpm.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dbclpm.dao.Dao;
import com.example.dbclpm.dao.impl.DaoImpl;
import com.example.dbclpm.dto.ResponseCommonDto;
import com.example.dbclpm.dto.SavePointResponseDto;
import com.example.dbclpm.model.Clazz;
import com.example.dbclpm.model.Subject;
import com.example.dbclpm.model.Teacher;
import com.example.dbclpm.model.Term;
import com.example.dbclpm.utils.PointUtils;
import com.google.gson.Gson;
import java.net.URLDecoder;

import dev.mccue.guava.base.Splitter;

@WebServlet("/api/teacher/save-point")
public class TeacherSavePointAPI extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private static final Dao dao = new DaoImpl();
    private static final Gson gson = new Gson();
    private static final BigDecimal zero = new BigDecimal("0");
    private static final BigDecimal nagtiveOne = new BigDecimal("-1");
    private static final BigDecimal ten = new BigDecimal("10");
    
    private BigDecimal validate(BigDecimal x, Map<String, String> parameterMap, HttpServletResponse resp, String tag, int percent) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        try {
            x = new BigDecimal(parameterMap.get(tag));
        } catch (Exception ex) {
            ex.printStackTrace();
            if (percent == 0) return null;
            resp.setStatus(400);
            pw.println(gson.toJson(new ResponseCommonDto(tag+": "+ex.getMessage(), 400, null)));
            return nagtiveOne;
        }
        if (x.compareTo(zero) < 0) {
            resp.setStatus(400);
            pw.println(gson.toJson(new ResponseCommonDto(tag+": Không được âm", 400, null)));
            return nagtiveOne;
        }
        if (x.compareTo(ten) > 0) {
            resp.setStatus(400);
            pw.println(gson.toJson(new ResponseCommonDto(tag+": Không được lớn hơn 10", 400, null)));
            return nagtiveOne;
        }
        
        return x.setScale(1, RoundingMode.HALF_UP);
    }
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        PrintWriter pw = resp.getWriter();
        int pointId = 0;
        
        BigDecimal cc = null;
        BigDecimal btl = null;
        BigDecimal th = null;
        BigDecimal ktgk = null;
        BigDecimal ktck = null;
        
        Map<String, String> parameterMap = getParameterMap(req);
        
        try {
            pointId = Integer.parseInt(parameterMap.get("pointId"));
        } catch (Exception ex) {
            ex.printStackTrace();
            resp.setStatus(400);
            pw.println(gson.toJson(new ResponseCommonDto(ex.getMessage(), 400, null)));
            return;
        }
        
        Term term = dao.getTermByPointId(pointId);
        long now = System.currentTimeMillis();
        if (term == null || !(term.getStartDate().getTime() <= now && now <= term.getEndDate().getTime())) {
            resp.setStatus(400);
            pw.println(gson.toJson(new ResponseCommonDto("Invalid Term Time", 400, null)));
            return;
        }
        
        Clazz clazz = dao.getClassByPointId(pointId);
        if (clazz.getTeacherId() != teacher.getId()) {
            resp.setStatus(400);
            pw.println(gson.toJson(new ResponseCommonDto("Point is not match with current teacher", 400, null)));
            return;
        }
        
        Subject subject = dao.getSubjectByPointId(pointId);

        cc = validate(cc, parameterMap, resp, "cc", subject.getPercentCC());
        if (cc != null && cc.compareTo(zero) < 0) return;
        btl = validate(btl, parameterMap, resp, "btl", subject.getPercentBTL());
        if (btl != null && btl.compareTo(zero) < 0) return;
        th = validate(th, parameterMap, resp, "th", subject.getPercentTH());
        if (th != null && th.compareTo(zero) < 0) return;
        ktgk = validate(ktgk, parameterMap, resp, "ktgk", subject.getPercentKTGK());
        if (ktgk != null && ktgk.compareTo(zero) < 0) return;
        ktck = validate(ktck, parameterMap, resp, "ktck", subject.getPercentKTCK());
        if (ktck != null && ktck.compareTo(zero) < 0) return;
        
        dao.savePoint(
            pointId,
            cc != null && subject.getPercentCC() > 0 ? cc.floatValue() : null,
            btl != null && subject.getPercentBTL() > 0 ? btl.floatValue() : null,
            th != null && subject.getPercentTH() > 0 ? th.floatValue() : null,
            ktgk != null && subject.getPercentKTGK() > 0 ? ktgk.floatValue() : null,
            ktck != null && subject.getPercentKTCK() > 0 ? ktck.floatValue() : null
        );
        
        BigDecimal averagePoint = PointUtils.calcAveragePoint(
            subject,
            cc != null && subject.getPercentCC() > 0 ? cc : zero,
            btl != null && subject.getPercentBTL() > 0 ? btl : zero,
            th != null && subject.getPercentTH() > 0 ? th : zero,
            ktgk != null && subject.getPercentKTGK() > 0 ? ktgk : zero,
            ktck != null && subject.getPercentKTCK() > 0 ? ktck : zero
        );
        
        String note = PointUtils.genNote(cc, btl, th, ktgk, ktck);
        String scoreByWord = PointUtils.genScorebyWord(averagePoint);
        Float scorePerFourRank = PointUtils.genScorePerFourRank(scoreByWord);
        
        SavePointResponseDto savePointResponseDto = new SavePointResponseDto(
            cc != null && subject.getPercentCC() > 0 ? cc.floatValue() : null,
            btl != null && subject.getPercentBTL() > 0 ? btl.floatValue() : null,
            th != null && subject.getPercentTH() > 0 ? th.floatValue() : null,
            ktgk != null && subject.getPercentKTGK() > 0 ? ktgk.floatValue() : null,
            ktck != null && subject.getPercentKTCK() > 0 ? ktck.floatValue() : null,
            averagePoint.floatValue(),
            scoreByWord,
            scorePerFourRank,
            note
        );
        pw.println(gson.toJson(new ResponseCommonDto("OK", 200, savePointResponseDto)));
    }
    
    private Map<String, String> getParameterMap(HttpServletRequest request) {
        BufferedReader br = null;
        Map<String, String> dataMap = null;
        Map<String, String> resMap = null;

        try {
            InputStreamReader reader = new InputStreamReader(
                    request.getInputStream());
            br = new BufferedReader(reader);

            String data = br.readLine();

            dataMap = Splitter.on('&')
                    .trimResults()
                    .withKeyValueSeparator(
                            Splitter.on('=')
                            .limit(2)
                            .trimResults())
                    .split(data);
            resMap = new HashMap<>();
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                String decodedValue = URLDecoder.decode(entry.getValue(), "UTF-8");
                resMap.put(entry.getKey().trim(), decodedValue.trim());
            }
            return resMap;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return resMap;
    }
}
