/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlsinhvien;

/**
 *
 * @author cao
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Check {
     public Check() {
    }
    public static boolean checkName(String n) {
        if (n == null || n.length() > 50 || n.length() < 5) {
            return false;
        } else {
            String strPattern = "[0-9\\.*+?^$@#|-]";
            Pattern p;
            Matcher m;
            int flag = Pattern.CASE_INSENSITIVE;
            p = Pattern.compile(strPattern, flag);
            m = p.matcher(n);
            return !m.find();

        }
   }
    public static boolean checkID(String n) {
        if (n == null || n.length() > 10 || n.length() < 2) {
            return false;
        } else {
            String strPattern = "[^a-z0-9]";
            Pattern p;
            Matcher m;
            int flag = Pattern.CASE_INSENSITIVE;
            p = Pattern.compile(strPattern, flag);
            m = p.matcher(n);
            return !m.find();

        }
    }
public static String regexDDMMYYYY = "^(0[1-9]|[12][0-9]|3[01])[- /.]"
                                   + "(0[1-9]|1[012])[- /.](19|20)\\d\\d$";
final static String DATE_FORMAT = "dd/MM/yyyy";
    public static boolean checkDate(String date) {
        try {
            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
