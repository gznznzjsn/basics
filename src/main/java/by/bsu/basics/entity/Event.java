package by.bsu.basics.entity;

import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Event {
    private final int id = (int) (Math.random() * 100);
    private String msg;
    private final Date date;
    private final DateFormat dateFormat;

    public Event(Date date, DateFormat dateFormat) {
        this.date = date;
        this.dateFormat = dateFormat;
    }

    public static boolean isDay() {
        return 8 < LocalTime.now().getHour() && LocalTime.now().getHour() <= 17;

    }

    @Override
    public String toString() {
        return id + " " + msg + " " + dateFormat.format(date);
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public Date getDate() {
        return date;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
