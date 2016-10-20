package id.grw.com.helper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import id.grw.com.model.Level;
import id.grw.com.model.Question;
import id.grw.com.model.Result;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GameID.db";

    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + Question.QUESTION_TABLE + " ( " + Question.QUESTION_ID + " integer primary key autoincrement not null ," + Question.QUESTION_TITLE + " text," + Question.QUESTION_ANSWER1 + " text," + Question.QUESTION_ANSWER2 + " text," + Question.QUESTION_ANSWER3 + " text," + Question.QUESTION_ANSWER4 + " text," + Question.QUESTION_CORRECT_ANSWER+ " int,"+ Question.QUESTION_TYPE +" text,"+ Question.QUESTION_IMAGE +" text )");
        //Init data;
        db.execSQL("Create table " + Result.RESULT_TABLE  + " (" + Result.RESULT_ID + " integer primary key autoincrement not null , " + Result.RESULT_NAME + " text, " + Result.RESULT_DATE + " text, " + Result.RESULT_POINT + " text )");
        Log.d("Init database:", "Create two tables successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + Question.QUESTION_TABLE);
        db.execSQL("DROP TABLE IF EXIST " + Result.RESULT_TABLE);
        Log.d("Drop Table", "Drop table successfully");
        onCreate(db);
    }

    //Question
    //Insert Question
    public boolean insertQuestion(Question question){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Question.QUESTION_TITLE,question.getTitle());
            contentValues.put(Question.QUESTION_ANSWER1,question.getAnswer1());
            contentValues.put(Question.QUESTION_ANSWER2,question.getAnswer2());
            contentValues.put(Question.QUESTION_ANSWER3,question.getAnswer3());
            contentValues.put(Question.QUESTION_ANSWER4,question.getAnswer4());
            contentValues.put(Question.QUESTION_CORRECT_ANSWER,question.getCorrectAnswer());
            contentValues.put(Question.QUESTION_TYPE,question.getType());
            contentValues.put(Question.QUESTION_IMAGE,question.getImage());
            db.insert(Question.QUESTION_TABLE,null,contentValues);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Question> selectQuestionsByLevel(String level){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" select * from " + Question.QUESTION_TABLE + " where " + Question.QUESTION_TYPE + " ='" + level + "'", null);
        List<Question> listQuestions = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndex(Question.QUESTION_ID)));
                question.setTitle(cursor.getString(cursor.getColumnIndex(Question.QUESTION_TITLE)));
                question.setAnswer1(cursor.getString(cursor.getColumnIndex(Question.QUESTION_ANSWER1)));
                question.setAnswer2(cursor.getString(cursor.getColumnIndex(Question.QUESTION_ANSWER2)));
                question.setAnswer3(cursor.getString(cursor.getColumnIndex(Question.QUESTION_ANSWER3)));
                question.setAnswer4(cursor.getString(cursor.getColumnIndex(Question.QUESTION_ANSWER4)));
                question.setCorrectAnswer(cursor.getString(cursor.getColumnIndex(Question.QUESTION_CORRECT_ANSWER)));
                question.setType(cursor.getString(cursor.getColumnIndex(Question.QUESTION_TYPE)));
                question.setImage(cursor.getString(cursor.getColumnIndex(Question.QUESTION_IMAGE)));
                listQuestions.add(question);
            }while (cursor.moveToNext());
        }
        return listQuestions;
    }

    public Question getQuestionById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" select * from " + Question.QUESTION_TABLE + " where " + Question.QUESTION_ID + " =" + id + "", null);
        List<Question> listQuestions = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                Question question = new Question();
                question.setId(cursor.getInt(cursor.getColumnIndex(Question.QUESTION_ID)));
                question.setTitle(cursor.getString(cursor.getColumnIndex(Question.QUESTION_TITLE)));
                question.setAnswer1(cursor.getString(cursor.getColumnIndex(Question.QUESTION_ANSWER1)));
                question.setAnswer2(cursor.getString(cursor.getColumnIndex(Question.QUESTION_ANSWER2)));
                question.setAnswer3(cursor.getString(cursor.getColumnIndex(Question.QUESTION_ANSWER3)));
                question.setAnswer4(cursor.getString(cursor.getColumnIndex(Question.QUESTION_ANSWER4)));
                question.setCorrectAnswer(cursor.getString(cursor.getColumnIndex(Question.QUESTION_CORRECT_ANSWER)));
                question.setType(cursor.getString(cursor.getColumnIndex(Question.QUESTION_TYPE)));
                question.setImage(cursor.getString(cursor.getColumnIndex(Question.QUESTION_IMAGE)));
                listQuestions.add(question);
            }while (cursor.moveToNext());
        }
        return listQuestions.get(0);
    }

    //Result
    //Insert Result
    public boolean insertResult(Result result){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Result.RESULT_NAME,result.getName());
            contentValues.put(Result.RESULT_POINT,result.getPoint());
            contentValues.put(Result.RESULT_DATE,result.getDate());
            db.insert(Result.RESULT_TABLE,null,contentValues);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Result> selectTop10(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(" select * from " + Result.RESULT_TABLE + " ORDER BY " + Result.RESULT_POINT + " DESC LIMIT 10", null);
        List<Result> listResults = new ArrayList<>();
        if(cursor.moveToFirst()){
            do {
                Result result = new Result();
                result.setId(cursor.getInt(cursor.getColumnIndex(Result.RESULT_ID)));
                result.setName(cursor.getString(cursor.getColumnIndex(Result.RESULT_NAME)));
                result.setDate(cursor.getString(cursor.getColumnIndex(Result.RESULT_DATE)));
                result.setPoint(cursor.getString(cursor.getColumnIndex(Result.RESULT_POINT)));
                listResults.add(result);
            }while (cursor.moveToNext());
        }
        return listResults;
    }

    public void insert45Questions(){

        if(selectQuestionsByLevel(Level.EASY).size() == 0){
            Question question1 = new Question();
            //Question 1
            question1.setTitle("What's HCM city grade ?");
            question1.setAnswer1("Type 1");
            question1.setAnswer2("Type 2");
            question1.setAnswer3("Special");
            question1.setAnswer4("a and c");
            question1.setCorrectAnswer("3");
            question1.setType("easy");
            question1.setImage("a1.jpeg");
            insertQuestion(question1);

            Question question2 = new Question();
            //Question 2
            question2.setTitle("How many citizens of HCM City from 2014 ?");
            question2.setAnswer1("7.981.900");
            question2.setAnswer2("7.521.138");
            question2.setAnswer3("7.162.864");
            question2.setAnswer4("8.253.584");
            question2.setCorrectAnswer("1");
            question2.setType("easy");
            question2.setImage("a2.jpeg");
            insertQuestion(question2);

            Question question3 = new Question();
            //Question 3
            question3.setTitle("GDP Of HCM City to 07/07/2017 by Sai Gon Giai Phong news ?");
            question3.setAnswer1("22,9 % GDP");
            question3.setAnswer2("29,38 % GDP");
            question3.setAnswer3("27,9 % GDP");
            question3.setAnswer4("21,3 % GDP");
            question3.setCorrectAnswer("4");
            question3.setType("easy");
            question3.setImage("a3.jpg");
            insertQuestion(question3);

            Question question4 = new Question();
            //Question 4
            question4.setTitle("Another name of HCM City ?");
            question4.setAnswer1("Sai Gon");
            question4.setAnswer2("Thanh Pho Mang Ten Bac");
            question4.setAnswer3("Hon Ngoc Vien Dong");
            question4.setAnswer4("a) b) c) are correct ");
            question4.setCorrectAnswer("4");
            question4.setType("easy");
            question4.setImage("a4.jpg");
            insertQuestion(question4);

            Question question5 = new Question();
            //Question 5
            question5.setTitle("How Many Districts of HCM City?");
            question5.setAnswer1("18 Quan, 5 Huyen");
            question5.setAnswer2("19 Quan, 5 Huyen");
            question5.setAnswer3("19 Quan, 4 Huyen");
            question5.setAnswer4("17 Quan, 6 Huyen");
            question5.setCorrectAnswer("2");
            question5.setType("easy");
            question5.setImage("a5.jpeg");
            insertQuestion(question5);

            Question question6 = new Question();
            //Question 6
            question6.setTitle("At 1698, Who is founder of Phu Gia Dinh, it's begin of HCM City ?");
            question6.setAnswer1("Nguyen Canh Chan");
            question6.setAnswer2("Nguyen Huu Canh");
            question6.setAnswer3("Nguyen Phuc Chu");
            question6.setAnswer4("Nguyen Tat Thanh");
            question6.setCorrectAnswer("2");
            question6.setType("easy");
            question6.setImage("a6.jpg");
            insertQuestion(question6);

            Question question7 = new Question();
            //Question 7
            question7.setTitle("By GSO Viet Nam, at 1/4/2009, What'are local nation were most crowd at Viet Nam ?");
            question7.setAnswer1("Tày, Nùng, Thái, Mường.");
            question7.setAnswer2("Kinh, Hoa, Khmer, Chăm.");
            question7.setAnswer3("Kinh, Hoa, Khmer.");
            question7.setAnswer4("Kinh, Hoa, Chăm.");
            question7.setCorrectAnswer("2");
            question7.setType("easy");
            question7.setImage("a7.jpg");
            insertQuestion(question7);

            Question question8 = new Question();
            //Question 8
            question8.setTitle("What's a district has border beside the beach ?");
            question8.setAnswer1("Binh Chanh");
            question8.setAnswer2("Nha Be");
            question8.setAnswer3("Can Gio");
            question8.setAnswer4("Hoc Mon");
            question8.setCorrectAnswer("3");
            question8.setType("easy");
            question8.setImage("a8.jpg");
            insertQuestion(question8);

            Question question9 = new Question();
            //Question 9
            question9.setTitle("What'are districts have borders beside Nha Be District belongs HCM City ?");
            question9.setAnswer1("Nhon Trach, Quan 9, Binh Chanh");
            question9.setAnswer2("Binh Chanh, Can Gio, Can Giuoc, Quan 7");
            question9.setAnswer3("Quan 2, Binh Chanh, Can Gio, Hoc Mon");
            question9.setAnswer4("Binh Chanh, Can Gio, Can Giuoc, Quan 2");
            question9.setCorrectAnswer("2");
            question9.setType("easy");
            question9.setImage("a9.jpg");
            insertQuestion(question9);

            Question question10 = new Question();
            //Question 10
            question10.setTitle("What'are districts have borders beside Go Vap District belongs HCM City  ?");
            question10.setAnswer1("Quan 12, Tan Binh, Binh Thanh, Phu Nhuan");
            question10.setAnswer2("Tan Binh, Phu Nhuan, Quan 3, Hoc Mon");
            question10.setAnswer3("Tan Binh, Phu Nhuan, Q12, Thu Duc");
            question10.setAnswer4("Tan Binh, Phu Nhuan, Thu Duc, Nha Be");
            question10.setCorrectAnswer("1");
            question10.setType("easy");
            question10.setImage("a10.jpeg");
            insertQuestion(question10);

            Question question11 = new Question();
            //Question 11
            question11.setTitle("What're districts call by 'Cho Lon' ?");
            question11.setAnswer1("Quan 5, 6 ,8 and a part of Tan Binh");
            question11.setAnswer2("Quan 5, 6 ,8 and a part of 10,11");
            question11.setAnswer3("Quan 5, 8, 11");
            question11.setAnswer4("Quan 6 ,8 and a part of 10,11");
            question11.setCorrectAnswer("2");
            question11.setType("easy");
            question11.setImage("a11.jpg");
            insertQuestion(question11);

            Question question12 = new Question();
            //Question 12
            question12.setTitle("Which super markets belongs Cho Lon from past ?");
            question12.setAnswer1("An Dong, Kim Bien, Hanh Thong Tay");
            question12.setAnswer2("An Dong, Binh Tay, Kim Bien");
            question12.setAnswer3("An Dong, Binh Tay, Kim Bien, Xa Tay");
            question12.setAnswer4("An Dong, Binh Tay, Kim Bien, Dai Quang Minh");
            question12.setCorrectAnswer("4");
            question12.setType("easy");
            question12.setImage("a12.jpg");
            insertQuestion(question12);

            Question question13 = new Question();
            //Question 13
            question13.setTitle("What's district Phu My Hung  belongs ?");
            question13.setAnswer1("Cu Chi");
            question13.setAnswer2("Binh Chanh");
            question13.setAnswer3("Quan 7");
            question13.setAnswer4("Thu Duc");
            question13.setCorrectAnswer("3");
            question13.setType("easy");
            question13.setImage("a13.jpg");
            insertQuestion(question13);

            Question question14 = new Question();
            //Question 14
            question14.setTitle("What's district Phu My Hung department belongs");
            question14.setAnswer1("Cu Chi");
            question14.setAnswer2("Binh Chanh");
            question14.setAnswer3("Quan 7");
            question14.setAnswer4("Thu Duc");
            question14.setCorrectAnswer("3");
            question14.setType("easy");
            question14.setImage("a14.jpg");
            insertQuestion(question14);

            Question question15 = new Question();
            //Question 15
            question15.setTitle("After 1975, What're  districts were split ?");
            question15.setAnswer1("Tan Binh, Tan Phu, Tan Son Nhi");
            question15.setAnswer2("Tan Binh, Tan Phu, Phu Nhuan");
            question15.setAnswer3("Tan Binh, Tan Phu, Phu Nhuan and a part of Binh Chanh District");
            question15.setAnswer4("Go Vap, Phu Nhuan, Binh Chanh");
            question15.setCorrectAnswer("3");
            question15.setType("easy");
            question15.setImage("a15.jpg");
            insertQuestion(question15);


            Question question16 = new Question();
            //Question 16
            question16.setTitle("What're seasons of HCM City ?");
            question16.setAnswer1("4: Spring, Autumn, Summer, Winter");
            question16.setAnswer2("2: Sunny, Raining");
            question16.setAnswer3("2: Raining, Drying");
            question16.setAnswer4("3: Raining, Drying, Sunny");
            question16.setCorrectAnswer("2");
            question16.setType("medium");
            question16.setImage("a1.jpeg");
            insertQuestion(question16);

            Question question17 = new Question();
            //Question 17
            question17.setTitle("What's the place called by 'Walking Street' ?");
            question17.setAnswer1("Nguyen Binh Khiem");
            question17.setAnswer2("Dong Khoi");
            question17.setAnswer3("Nguyen Hue");
            question17.setAnswer4("Ham Nghi");
            question17.setCorrectAnswer("3");
            question17.setType("medium");
            question17.setImage("a2.jpeg");
            insertQuestion(question17);

            Question question18 = new Question();
            //Question 18
            question18.setTitle("What's district Dam Sen belongs ?");
            question18.setAnswer1("Quan 11");
            question18.setAnswer2("Tan Phu");
            question18.setAnswer3("BinH Tan");
            question18.setAnswer4("Tan Binh");
            question18.setCorrectAnswer("1");
            question18.setType("medium");
            question18.setImage("a3.jpg");
            insertQuestion(question18);

            Question question19 = new Question();
            //Question 19
            question19.setTitle("How many regular ports that HCM have?");
            question19.setAnswer1("3");
            question19.setAnswer2("4");
            question19.setAnswer3("5");
            question19.setAnswer4("6");
            question19.setCorrectAnswer("2");
            question19.setType("medium");
            question19.setImage("a4.jpg");
            insertQuestion(question19);

            Question question20 = new Question();
            //Question 20
            question20.setTitle("Detail main port at HCM City ?");
            question20.setAnswer1("Binh Dong, Tan Thuan, Ton That Thuyet");
            question20.setAnswer2("Ton That Thuyet, Binh Loi, Binh Phuoc");
            question20.setAnswer3("Sai Gon, Ben Nghe, Nha Nghe, Tan Cang");
            question20.setAnswer4("Sai Gon, Ben Nghe, Tan Thuan, Binh Loi");
            question20.setCorrectAnswer("3");
            question20.setType("medium");
            question20.setImage("a5.jpeg");
            insertQuestion(question20);

            Question question21 = new Question();
            //Question 21
            question21.setTitle("What's the district Tan Son Nhat international airport belongs ?");
            question21.setAnswer1("Tan Binh");
            question21.setAnswer2("Tan Phu");
            question21.setAnswer3("Binh Tan");
            question21.setAnswer4("Phu Thanh");
            question21.setCorrectAnswer("1");
            question21.setType("medium");
            question21.setImage("a6.jpg");
            insertQuestion(question21);

            Question question22 = new Question();
            //Question 22
            question22.setTitle("The Financial System of HCM City ?");
            question22.setAnswer1("UBND, HĐND, MTGPMN.");
            question22.setAnswer2("UBND, HĐND, TAND, MTGPMN.");
            question22.setAnswer3("UBND, HĐND, TAND.");
            question22.setAnswer4("UBND, HĐND, TAND, Thành Ủy TPHCM.");
            question22.setCorrectAnswer("3");
            question22.setType("medium");
            question22.setImage("a7.jpg");
            insertQuestion(question22);

            Question question23 = new Question();
            //Question 23
            question23.setTitle("What's date the 'Nha Tho Duc Ba' was built ?");
            question23.setAnswer1("11/4/1880");
            question23.setAnswer2("7/10/1877");
            question23.setAnswer3("8/1876");
            question23.setAnswer4("10/11/1879");
            question23.setCorrectAnswer("2");
            question23.setType("medium");
            question23.setImage("a8.jpg");
            insertQuestion(question23);

            Question question24 = new Question();
            //Question 24
            question24.setTitle("The regular parks of HCM City ?");
            question24.setAnswer1("Cong Vien Gia Dinh, Hoang Van Thu, Tao Dan");
            question24.setAnswer2("Le Thi Rieng, Le Van Tam, 23/9");
            question24.setAnswer3("30/4, Thao cam Vien");
            question24.setAnswer4("All of them are corrects");
            question24.setCorrectAnswer("4");
            question24.setType("medium");
            question24.setImage("a9.jpg");
            insertQuestion(question24);

            Question question25 = new Question();
            //Question 25
            question25.setTitle("What's time the Thong Nhat President Department was built ?");
            question25.setAnswer1("1/7/1962");
            question25.setAnswer2("31/10/1966");
            question25.setAnswer3("23/2/1868");
            question25.setAnswer4("2/11/1963");
            question25.setCorrectAnswer("3");
            question25.setType("medium");
            question25.setImage("a10.jpeg");
            insertQuestion(question25);

            Question question26 = new Question();
            //Question 26
            question26.setTitle("Where's History Museum VietNam located ?");
            question26.setAnswer1("Nguyen Thai Hoc");
            question26.setAnswer2("Ly Tu Trong");
            question26.setAnswer3("Ham Nghi");
            question26.setAnswer4("Nguyen Binh Khiem");
            question26.setCorrectAnswer("4");
            question26.setType("medium");
            question26.setImage("a11.jpg");
            insertQuestion(question26);

            Question question27 = new Question();
            //Question 27
            question27.setTitle("How many wars in District 1 ?");
            question27.setAnswer1("8");
            question27.setAnswer2("9");
            question27.setAnswer3("10");
            question27.setAnswer4("11");
            question27.setCorrectAnswer("3");
            question27.setType("medium");
            question27.setImage("a12.jpg");
            insertQuestion(question27);

            Question question28 = new Question();
            //Question 28
            question28.setTitle("Which wards belongs District 1 ?");
            question28.setAnswer1("Tan Dinh, Da Kao, Ben Nghe, Ben Thanh");
            question28.setAnswer2("Nguyen Thai Binh, Cau Ong Lanh, Co Giang, Cau Kho");
            question28.setAnswer3("Nguyen Cu Trinh, Pham Ngu Lao");
            question28.setAnswer4("Above're correct");
            question28.setCorrectAnswer("4");
            question28.setType("medium");
            question28.setImage("a13.jpg");
            insertQuestion(question28);

            Question question29 = new Question();
            //Question 29
            question29.setTitle("Two statue reliefs in front of, what its image ?");
            question29.setAnswer1("Unicorn");
            question29.setAnswer2("Lion");
            question29.setAnswer3("Dragon");
            question29.setAnswer4("Ladies");
            question29.setCorrectAnswer("4");
            question29.setType("medium");
            question29.setImage("a14.jpg");
            insertQuestion(question29);

            Question question30 = new Question();
            //Question 30
            question30.setTitle("What's the football stadium located in HCM and was choose by V-l ?");
            question30.setAnswer1("Thong Nhat");
            question30.setAnswer2("Phu Tho");
            question30.setAnswer3("Quan Khu 7");
            question30.setAnswer4("Phan Dinh Phung");
            question30.setCorrectAnswer("1");
            question30.setType("medium");
            question30.setImage("a15.jpg");
            insertQuestion(question30);

            Question question31 = new Question();
            //Question 31
            question31.setTitle("The General TV Chanel of HCM City ?");
            question31.setAnswer1("SCTV");
            question31.setAnswer2("HTV");
            question31.setAnswer3("FPT-Play");
            question31.setAnswer4("K+");
            question31.setCorrectAnswer("2");
            question31.setType("hard");
            question31.setImage("a1.jpeg");
            insertQuestion(question31);

            Question question32 = new Question();
            //Question 32
            question32.setTitle("What's district Bach Khoa University located ?");
            question32.setAnswer1("Quan 11");
            question32.setAnswer2("Quan Tan Binh");
            question32.setAnswer3("Quan Binh Tan");
            question32.setAnswer4("Quan 10");
            question32.setCorrectAnswer("4");
            question32.setType("hard");
            question32.setImage("a2.jpeg");
            insertQuestion(question32);

            Question question33 = new Question();
            //Question 33
            question33.setTitle("What's district Binh Quoi Travel located ?");
            question33.setAnswer1("Binh Tan");
            question33.setAnswer2("Binh Thanh");
            question33.setAnswer3("Go Vap");
            question33.setAnswer4("Thu Duc");
            question33.setCorrectAnswer("2");
            question33.setType("hard");
            question33.setImage("a3.jpg");
            insertQuestion(question33);

            Question question34 = new Question();
            //Question 34
            question34.setTitle("What's district Sai Gon Train Station located ?");
            question34.setAnswer1("Quan 1");
            question34.setAnswer2("Quan 3");
            question34.setAnswer3("Tan Binh");
            question34.setAnswer4("Quan 10");
            question34.setCorrectAnswer("2");
            question34.setType("hard");
            question34.setImage("a4.jpg");
            insertQuestion(question34);

            Question question35 = new Question();
            //Question 35
            question35.setTitle("What're district Thu Thiem Tunnel merge ?");
            question35.setAnswer1("Quan 2 and Quan 3");
            question35.setAnswer2("Quan 1 and Quan 2");
            question35.setAnswer3("Quan 2 and Quan 7");
            question35.setAnswer4("a and c");
            question35.setCorrectAnswer("2");
            question35.setType("hard");
            question35.setImage("a5.jepg");
            insertQuestion(question35);

            Question question36 = new Question();
            //Question 36
            question36.setTitle("What're district Dai Lo Vo Van Kiet cut-off ?");
            question36.setAnswer1("1,3,5,6");
            question36.setAnswer2("1,5,6");
            question36.setAnswer3("1,5,Tan Phu");
            question36.setAnswer4("1,5,6,Binh Tan");
            question36.setCorrectAnswer("4");
            question36.setType("hard");
            question36.setImage("a6.jpg");
            insertQuestion(question36);

            Question question37 = new Question();
            //Question 37
            question37.setTitle("What're district Phu My Bridge merge ?");
            question37.setAnswer1("Quan 2 , Binh Chanh");
            question37.setAnswer2("Quan 7 , Nha Be");
            question37.setAnswer3("Quan 2, Quan 7");
            question37.setAnswer4("Quan 1, Quan 2");
            question37.setCorrectAnswer("3");
            question37.setType("hard");
            question37.setImage("a7.jpg");
            insertQuestion(question37);

            Question question38 = new Question();
            //Question 38
            question38.setTitle("Bo To is famous food of?");
            question38.setAnswer1("Can Gio");
            question38.setAnswer2("Tan Hung");
            question38.setAnswer3("Cu Chi");
            question38.setAnswer4("Phu Hoa");
            question38.setCorrectAnswer("3");
            question38.setType("hard");
            question38.setImage("a8.jpg");
            insertQuestion(question38);

            Question question39 = new Question();
            //Question 39
            question39.setTitle("What's Tu Linh of Suoi Tien park ?");
            question39.setAnswer1("Dong Tay Nam Bac");
            question39.setAnswer2("Long Lan Quy Phung");
            question39.setAnswer3("Xuan Ha Thu Dong");
            question39.setAnswer4("Mai Lan Cuc Truc");
            question39.setCorrectAnswer("2");
            question39.setType("hard");
            question39.setImage("a9.jpg");
            insertQuestion(question39);

            Question question40 = new Question();
            //Question 40
            question40.setTitle("What's famous history location of Cu Chi ?");
            question40.setAnswer1("Quang Trung Nguyen Hue pagoda");
            question40.setAnswer2("Dia Dao");
            question40.setAnswer3("Bo Tat Thich Quang Duc statue");
            question40.setAnswer4("Lang Ong");
            question40.setCorrectAnswer("2");
            question40.setType("hard");
            question40.setImage("a10.jpeg");
            insertQuestion(question40);

            Question question41 = new Question();
            //Question 41
            question41.setTitle("What's time Nghinh Ong Can Gio contribute ?");
            question41.setAnswer1("1/7 Lunar Year");
            question41.setAnswer2("15/8 Lunar Year");
            question41.setAnswer3("15/9 Lunar Year");
            question41.setAnswer4("1/9 Lunar Year");
            question41.setCorrectAnswer("3");
            question41.setType("hard");
            question41.setImage("a11.jpg");
            insertQuestion(question41);

            Question question42 = new Question();
            //Question 42
            question42.setTitle("What's district Dao Khi belongs ?");
            question42.setAnswer1("Cu Chi");
            question42.setAnswer2("Nha Be");
            question42.setAnswer3("Can Gio");
            question42.setAnswer4("Binh Chanh");
            question42.setCorrectAnswer("3");
            question42.setType("hard");
            question42.setImage("a12.jpg");
            insertQuestion(question42);

            Question question43 = new Question();
            //Question 43
            question43.setTitle("What's district Can Giuoc river through out ?");
            question43.setAnswer1("Can Gio");
            question43.setAnswer2("Nha Be");
            question43.setAnswer3("Binh Chanh");
            question43.setAnswer4("Can Duoc");
            question43.setCorrectAnswer("3");
            question43.setType("hard");
            question43.setImage("a13.jpg");
            insertQuestion(question43);

            Question question44 = new Question();
            //Question 44
            question44.setTitle("Which district Hoang Phap pagoda belongs ?");
            question44.setAnswer1("Thu Duc");
            question44.setAnswer2("Cu Chi");
            question44.setAnswer3("Hoc Mon");
            question44.setAnswer4("Nha Be");
            question44.setCorrectAnswer("3");
            question44.setType("hard");
            question44.setImage("a14.jpg");
            insertQuestion(question44);

            Question question45 = new Question();
            //Question 45
            question45.setTitle("University Campus located at ?");
            question45.setAnswer1("Nha Be");
            question45.setAnswer2("Hoc Mon");
            question45.setAnswer3("Thu Duc");
            question45.setAnswer4("Go Vap");
            question45.setCorrectAnswer("1");
            question45.setType("hard");
            question45.setImage("a15.jpg");
            insertQuestion(question45);

            //Result 1
            Result result1 = new Result();
            result1.setName("Hai");
            result1.setPoint("50");
            DateFormat df = new SimpleDateFormat("dd/MM HH:mm");
            Date date = Calendar.getInstance().getTime();
            String now = df.format(date);
            result1.setDate(now);
            insertResult(result1);

            //Result 1
            Result result2 = new Result();
            result2.setName("Hai Le");
            result2.setPoint("30");
            result2.setDate(now);
            insertResult(result2);

            Log.d("Init import database:", "Import data successfully");
        }

    }

}
