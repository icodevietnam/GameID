package id.grw.com.model;

import java.util.Date;

public class Result {

    public static final String RESULT_TABLE = "result";
    public static final String RESULT_ID = "result";
    public static final String RESULT_NAME = "name";
    public static final String RESULT_POINT = "point";
    public static final String RESULT_DATE = "date";

    private int id;
    private String name;
    private String point;
    private String date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
