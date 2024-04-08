package com.example.dbclpm.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.example.dbclpm.model.Subject;

public class PointUtils {
    private static final BigDecimal zero = new BigDecimal("0");
    private static final BigDecimal oneHundred = new BigDecimal("100");
    
    public static BigDecimal calcAveragePoint(Subject subject, BigDecimal cc, BigDecimal btl, BigDecimal th, BigDecimal ktgk, BigDecimal ktck) {
        BigDecimal averagePoint = zero;
        averagePoint = averagePoint.add(cc.multiply(new BigDecimal(subject.getPercentCC())).divide(oneHundred));
        averagePoint = averagePoint.add(btl.multiply(new BigDecimal(subject.getPercentBTL())).divide(oneHundred));
        averagePoint = averagePoint.add(th.multiply(new BigDecimal(subject.getPercentTH())).divide(oneHundred));
        averagePoint = averagePoint.add(ktgk.multiply(new BigDecimal(subject.getPercentKTGK())).divide(oneHundred));
        averagePoint = averagePoint.add(ktck.multiply(new BigDecimal(subject.getPercentKTCK())).divide(oneHundred));
        return averagePoint.setScale(1, RoundingMode.HALF_UP);
    }
    
    public static String genNote(BigDecimal cc, BigDecimal btl, BigDecimal th, BigDecimal ktgk, BigDecimal ktck) {
        String note = null;
        if (
            (cc != null && cc.compareTo(zero) == 0) ||
            (btl != null && btl.compareTo(zero) == 0) ||
            (th != null && th.compareTo(zero) == 0) ||
            (ktgk != null && ktgk.compareTo(zero) == 0) || 
            (ktck != null && ktck.compareTo(zero) == 0)
        ) {
            note = "KĐĐKDT";
        }
        return note;
    }
    
    public static String genScorebyWord(BigDecimal averagePoint) {
        if (averagePoint.compareTo(new BigDecimal("4.0")) < 0) {
            return "F";
        }
        if (averagePoint.compareTo(new BigDecimal("4.0")) >= 0 && averagePoint.compareTo(new BigDecimal("4.9")) <= 0) {
            return "D";
        }
        if (averagePoint.compareTo(new BigDecimal("5.0")) >= 0 && averagePoint.compareTo(new BigDecimal("5.4")) <= 0) {
            return "D+";
        }
        if (averagePoint.compareTo(new BigDecimal("5.5")) >= 0 && averagePoint.compareTo(new BigDecimal("6.4")) <= 0) {
            return "C";
        }
        if (averagePoint.compareTo(new BigDecimal("6.5")) >= 0 && averagePoint.compareTo(new BigDecimal("6.9")) <= 0) {
            return "C+";
        }
        if (averagePoint.compareTo(new BigDecimal("7.0")) >= 0 && averagePoint.compareTo(new BigDecimal("7.9")) <= 0) {
            return "B";
        }
        if (averagePoint.compareTo(new BigDecimal("8.0")) >= 0 && averagePoint.compareTo(new BigDecimal("8.4")) <= 0) {
            return "B+";
        }
        if (averagePoint.compareTo(new BigDecimal("8.5")) >= 0 && averagePoint.compareTo(new BigDecimal("8.9")) <= 0) {
            return "A";
        }
        if (averagePoint.compareTo(new BigDecimal("9.0")) >= 0 && averagePoint.compareTo(new BigDecimal("10.0")) <= 0) {
            return "A+";
        }
        
        return "";
    }
    
    public static Float genScorePerFourRank(String scorebyWord) {
        if (scorebyWord == "F") return 0.0f;
        if (scorebyWord == "D") return 1.0f;
        if (scorebyWord == "D+") return 1.5f;
        if (scorebyWord == "C") return 2.0f;
        if (scorebyWord == "C+") return 2.5f;
        if (scorebyWord == "B") return 3.0f;
        if (scorebyWord == "B+") return 3.5f;
        if (scorebyWord == "A") return 3.7f;
        if (scorebyWord == "A+") return 4.0f;
        return null;
    }
}
