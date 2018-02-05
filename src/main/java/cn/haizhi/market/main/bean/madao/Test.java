package cn.haizhi.market.main.bean.madao;

import cn.haizhi.market.other.util.DateFormatUtil;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Test {
    public static void main(String[] args){
        Date date = new Date();
        Date date1 = new Date(date.getTime() + 1000);
        Date date2 = new Date(date.getTime() - 1000);
        System.out.println(date.before(date1));
        System.out.println(date.before(date2));
        System.out.println(date.getTime());
        Calendar calendar = new GregorianCalendar();
        calendar.set(2018, 0, 18, 0, 0, 0);
        Calendar calendar1 = new GregorianCalendar();
        calendar1.set(2018, 0, 19, 0, 0, 1);
        date = new Date(calendar.getTimeInMillis());
        date1 = new Date(calendar1.getTimeInMillis());
        System.out.println(date + "--------------" + date1);
        System.out.println(date1.getTime()- date.getTime());
        if(date1.getTime()-date.getTime()>86400000){
            System.out.println("ok");
        }
        try {
            date = calendar.getTime();
            System.out.println(calendar.getTimeInMillis());
            Date dateTest = DateFormatUtil.StringToDate("20180118");
            System.out.println(date.getTime() + "-------------" + dateTest.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar2 = new GregorianCalendar();
        calendar2.setTimeInMillis(1515513600000L);
        System.out.println(calendar2.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar2.get(Calendar.HOUR) + "--" + calendar2.get(Calendar.MINUTE) + "---" + calendar2.get(Calendar.SECOND));
        Byte b = 1;
        Integer i = 1;
        System.out.println(b.equals(i));

        date = new Date();
        calendar.set(2018, 0, 20, 5, 0, 0);
        System.out.println(calendar.getTimeInMillis());
        System.out.println(DateFormatUtil.DateToString(new Date(1516395600180L)));
        date = new Date();
        System.out.println(date.getTime());
    }
}
//1516276587041
