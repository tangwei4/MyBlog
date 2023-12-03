package com.suye.personalblog.tool;

import java.util.Date;

//时间的转化
public class TimeConversion {
    static final long SECOND=1000;
    static final long MINUTE=SECOND*60;
    static final long HOUR=MINUTE*60;
    static final long DAY=HOUR*24;
    static final long MONTH=DAY*30;
    static final long YEAR=MONTH*12;

    public static String timeConversion(Date date){
        //这操蛋的Java时间戳，以后记得改
        //System.out.println("createtime is"+date);
        long now=new Date().getTime();
//        System.out.println("now is"+new Date());
//        System.out.println();
//        Date nowtrasfer=new Date(nows.getYear(),nows.getMonth(),nows.getDay(),nows.getHours(),nows.getMinutes(),nows.getSeconds());
//        long now=nowtrasfer.getTime();
//        System.out.println(nowtrasfer);
//        Date datetrasfer=new Date(date.getYear(),date.getMonth(),date.getDay(),date.getHours(),date.getMinutes(),date.getSeconds());
//        System.out.println(datetrasfer);
        long create=date.getTime()-HOUR*8;
        long time=now-create;
//        System.out.println("time"+time);
        if (time<0){
            return null;
        }
        if (time<MINUTE){
            return "0分钟前";
        }else if (time<HOUR){
            return (time)/MINUTE+"分钟前";
        }else if (time<DAY){
            return (time)/HOUR+"小时前";
        }else if (time<MONTH){
            return (time)/DAY+"天前";
        }else if (time<YEAR){
            return time/MONTH+"月前";
        }else{
            return time/YEAR+"年前";
        }
    }

    //Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec
    public static String monthConversion(String month){
        switch (month){
            case "Jan":return 1+"";
            case "Feb":return 2+"";
            case "Mar":return 3+"";
            case "Apr":return 4+"";
            case "May":return 5+"";
            case "Jun":return 6+"";
            case "Jul":return 7+"";
            case "Aug":return 8+"";
            case "Sep":return 9+"";
            case "Oct":return 10+"";
            case "Nov":return 11+"";
            case "Dec":return 12+"";
        }
        return null;
    }
}
