package id.grw.com.helper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import id.grw.com.model.Question;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GameID.db";

    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Question.QUESTION_TABLE + " ( " + Question.QUESTION_ID + " integer primary key autoincrement not null ," + Question.QUESTION_TITLE + " text," + Question.QUESTION_ANSWER1 + " text," + Question.QUESTION_ANSWER2 + " text," + Question.QUESTION_ANSWER3 + " text," + Question.QUESTION_ANSWER4 + " text," + Question.QUESTION_CORRECT_ANSWER+ " int,"+ Question.QUESTION_TYPE +" text,"+ Question.QUESTION_IMAGE +" text)");
        //Init data;
        Log.d("Init database:", "Init two tables successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + Question.QUESTION_TABLE);
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
        Cursor cursor = db.rawQuery(" select * from " + Question.QUESTION_TABLE + " where " + Question.QUESTION_TYPE + " = " + level + "", null);
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

    public void insert45Questions(){
        Question question1 = new Question();
        //Question 1
        question1.setTitle("What's HCM city grade ?");
        question1.setAnswer1("Type 1");
        question1.setAnswer2("Type 2");
        question1.setAnswer3("Special");
        question1.setAnswer4("a and c");
        question1.setCorrectAnswer("3");
        question1.setType("easy");
        question1.setImage("1.jpg");
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
        question2.setImage("2.jpg");
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
        question3.setImage("3.jpg");
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
        question4.setImage("4.jpg");
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
        question5.setImage("5.jpg");
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
        question6.setImage("6.jpg");
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
        question7.setImage("7.jpg");
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
        question8.setImage("8.jpg");
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
        question9.setImage("9.jpg");
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
        question10.setImage("10.jpg");
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
        question11.setImage("11.jpg");
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
        question12.setImage("12.jpg");
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
        question13.setImage("13.jpg");
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
        question14.setImage("14.jpg");
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
        question15.setImage("15.jpg");
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
        question16.setImage("1.jpg");
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
        question17.setImage("2.jpg");
        insertQuestion(question17);

        Question question18 = new Question();
        //Question 18
        question18.setTitle("What's HCM city grade ?");
        question18.setAnswer1("Type 1");
        question18.setAnswer2("Type 2");
        question18.setAnswer3("Special");
        question18.setAnswer4("a and c");
        question18.setCorrectAnswer("3");
        question18.setType("medium");
        question18.setImage("3.jpg");
        insertQuestion(question18);

        Question question19 = new Question();
        //Question 19
        question19.setTitle("How many citizens of HCM City from 2014 ?");
        question19.setAnswer1("7.981.900");
        question19.setAnswer2("7.521.138");
        question19.setAnswer3("7.162.864");
        question19.setAnswer4("8.253.584");
        question19.setCorrectAnswer("1");
        question19.setType("medium");
        question19.setImage("4.jpg");
        insertQuestion(question19);

        Question question20 = new Question();
        //Question 20
        question20.setTitle("What's HCM city grade ?");
        question20.setAnswer1("Type 1");
        question20.setAnswer2("Type 2");
        question20.setAnswer3("Special");
        question20.setAnswer4("a and c");
        question20.setCorrectAnswer("3");
        question20.setType("medium");
        question20.setImage("5.jpg");
        insertQuestion(question20);

        Question question21 = new Question();
        //Question 21
        question21.setTitle("How many citizens of HCM City from 2014 ?");
        question21.setAnswer1("7.981.900");
        question21.setAnswer2("7.521.138");
        question21.setAnswer3("7.162.864");
        question21.setAnswer4("8.253.584");
        question21.setCorrectAnswer("1");
        question21.setType("medium");
        question21.setImage("6.jpg");
        insertQuestion(question21);

        Question question22 = new Question();
        //Question 22
        question22.setTitle("What's HCM city grade ?");
        question22.setAnswer1("Type 1");
        question22.setAnswer2("Type 2");
        question22.setAnswer3("Special");
        question22.setAnswer4("a and c");
        question22.setCorrectAnswer("3");
        question22.setType("medium");
        question22.setImage("7.jpg");
        insertQuestion(question22);

        Question question23 = new Question();
        //Question 23
        question23.setTitle("How many citizens of HCM City from 2014 ?");
        question23.setAnswer1("7.981.900");
        question23.setAnswer2("7.521.138");
        question23.setAnswer3("7.162.864");
        question23.setAnswer4("8.253.584");
        question23.setCorrectAnswer("1");
        question23.setType("medium");
        question23.setImage("8.jpg");
        insertQuestion(question23);

        Question question24 = new Question();
        //Question 24
        question24.setTitle("What's HCM city grade ?");
        question24.setAnswer1("Type 1");
        question24.setAnswer2("Type 2");
        question24.setAnswer3("Special");
        question24.setAnswer4("a and c");
        question24.setCorrectAnswer("3");
        question24.setType("medium");
        question24.setImage("9.jpg");
        insertQuestion(question24);

        Question question25 = new Question();
        //Question 25
        question25.setTitle("How many citizens of HCM City from 2014 ?");
        question25.setAnswer1("7.981.900");
        question25.setAnswer2("7.521.138");
        question25.setAnswer3("7.162.864");
        question25.setAnswer4("8.253.584");
        question25.setCorrectAnswer("1");
        question25.setType("medium");
        question25.setImage("10.jpg");
        insertQuestion(question25);

        Question question26 = new Question();
        //Question 26
        question26.setTitle("What's HCM city grade ?");
        question26.setAnswer1("Type 1");
        question26.setAnswer2("Type 2");
        question26.setAnswer3("Special");
        question26.setAnswer4("a and c");
        question26.setCorrectAnswer("3");
        question26.setType("medium");
        question26.setImage("11.jpg");
        insertQuestion(question26);

        Question question27 = new Question();
        //Question 27
        question27.setTitle("How many citizens of HCM City from 2014 ?");
        question27.setAnswer1("7.981.900");
        question27.setAnswer2("7.521.138");
        question27.setAnswer3("7.162.864");
        question27.setAnswer4("8.253.584");
        question27.setCorrectAnswer("1");
        question27.setType("medium");
        question27.setImage("12.jpg");
        insertQuestion(question27);

        Question question28 = new Question();
        //Question 28
        question28.setTitle("What's HCM city grade ?");
        question28.setAnswer1("Type 1");
        question28.setAnswer2("Type 2");
        question28.setAnswer3("Special");
        question28.setAnswer4("a and c");
        question28.setCorrectAnswer("3");
        question28.setType("medium");
        question28.setImage("13.jpg");
        insertQuestion(question28);

        Question question29 = new Question();
        //Question 29
        question29.setTitle("How many citizens of HCM City from 2014 ?");
        question29.setAnswer1("7.981.900");
        question29.setAnswer2("7.521.138");
        question29.setAnswer3("7.162.864");
        question29.setAnswer4("8.253.584");
        question29.setCorrectAnswer("1");
        question29.setType("medium");
        question29.setImage("14.jpg");
        insertQuestion(question29);

        Question question30 = new Question();
        //Question 30
        question30.setTitle("How many citizens of HCM City from 2014 ?");
        question30.setAnswer1("7.981.900");
        question30.setAnswer2("7.521.138");
        question30.setAnswer3("7.162.864");
        question30.setAnswer4("8.253.584");
        question30.setCorrectAnswer("1");
        question30.setType("medium");
        question30.setImage("15.jpg");
        insertQuestion(question30);

        Question question31 = new Question();
        //Question 31
        question31.setTitle("What's HCM city grade ?");
        question31.setAnswer1("Type 1");
        question31.setAnswer2("Type 2");
        question31.setAnswer3("Special");
        question31.setAnswer4("a and c");
        question31.setCorrectAnswer("3");
        question31.setType("hard");
        question31.setImage("1.jpg");
        insertQuestion(question31);

        Question question32 = new Question();
        //Question 32
        question32.setTitle("How many citizens of HCM City from 2014 ?");
        question32.setAnswer1("7.981.900");
        question32.setAnswer2("7.521.138");
        question32.setAnswer3("7.162.864");
        question32.setAnswer4("8.253.584");
        question32.setCorrectAnswer("1");
        question32.setType("hard");
        question32.setImage("2.jpg");
        insertQuestion(question32);

        Question question33 = new Question();
        //Question 33
        question33.setTitle("What's HCM city grade ?");
        question33.setAnswer1("Type 1");
        question33.setAnswer2("Type 2");
        question33.setAnswer3("Special");
        question33.setAnswer4("a and c");
        question33.setCorrectAnswer("3");
        question33.setType("hard");
        question33.setImage("3.jpg");
        insertQuestion(question33);

        Question question34 = new Question();
        //Question 34
        question34.setTitle("How many citizens of HCM City from 2014 ?");
        question34.setAnswer1("7.981.900");
        question34.setAnswer2("7.521.138");
        question34.setAnswer3("7.162.864");
        question34.setAnswer4("8.253.584");
        question34.setCorrectAnswer("1");
        question34.setType("hard");
        question34.setImage("4.jpg");
        insertQuestion(question34);

        Question question35 = new Question();
        //Question 35
        question35.setTitle("What's HCM city grade ?");
        question35.setAnswer1("Type 1");
        question35.setAnswer2("Type 2");
        question35.setAnswer3("Special");
        question35.setAnswer4("a and c");
        question35.setCorrectAnswer("3");
        question35.setType("hard");
        question35.setImage("5.jpg");
        insertQuestion(question35);

        Question question36 = new Question();
        //Question 36
        question36.setTitle("How many citizens of HCM City from 2014 ?");
        question36.setAnswer1("7.981.900");
        question36.setAnswer2("7.521.138");
        question36.setAnswer3("7.162.864");
        question36.setAnswer4("8.253.584");
        question36.setCorrectAnswer("1");
        question36.setType("hard");
        question36.setImage("6.jpg");
        insertQuestion(question36);

        Question question37 = new Question();
        //Question 37
        question37.setTitle("What's HCM city grade ?");
        question37.setAnswer1("Type 1");
        question37.setAnswer2("Type 2");
        question37.setAnswer3("Special");
        question37.setAnswer4("a and c");
        question37.setCorrectAnswer("3");
        question37.setType("hard");
        question37.setImage("7.jpg");
        insertQuestion(question37);

        Question question38 = new Question();
        //Question 38
        question38.setTitle("How many citizens of HCM City from 2014 ?");
        question38.setAnswer1("7.981.900");
        question38.setAnswer2("7.521.138");
        question38.setAnswer3("7.162.864");
        question38.setAnswer4("8.253.584");
        question38.setCorrectAnswer("1");
        question38.setType("hard");
        question38.setImage("8.jpg");
        insertQuestion(question38);

        Question question39 = new Question();
        //Question 39
        question39.setTitle("What's HCM city grade ?");
        question39.setAnswer1("Type 1");
        question39.setAnswer2("Type 2");
        question39.setAnswer3("Special");
        question39.setAnswer4("a and c");
        question39.setCorrectAnswer("3");
        question39.setType("hard");
        question39.setImage("9.jpg");
        insertQuestion(question39);

        Question question40 = new Question();
        //Question 40
        question40.setTitle("How many citizens of HCM City from 2014 ?");
        question40.setAnswer1("7.981.900");
        question40.setAnswer2("7.521.138");
        question40.setAnswer3("7.162.864");
        question40.setAnswer4("8.253.584");
        question40.setCorrectAnswer("1");
        question40.setType("hard");
        question40.setImage("10.jpg");
        insertQuestion(question40);

        Question question41 = new Question();
        //Question 41
        question41.setTitle("What's HCM city grade ?");
        question41.setAnswer1("Type 1");
        question41.setAnswer2("Type 2");
        question41.setAnswer3("Special");
        question41.setAnswer4("a and c");
        question41.setCorrectAnswer("3");
        question41.setType("hard");
        question41.setImage("11.jpg");
        insertQuestion(question41);

        Question question42 = new Question();
        //Question 42
        question42.setTitle("How many citizens of HCM City from 2014 ?");
        question42.setAnswer1("7.981.900");
        question42.setAnswer2("7.521.138");
        question42.setAnswer3("7.162.864");
        question42.setAnswer4("8.253.584");
        question42.setCorrectAnswer("1");
        question42.setType("hard");
        question42.setImage("12.jpg");
        insertQuestion(question42);

        Question question43 = new Question();
        //Question 43
        question43.setTitle("What's HCM city grade ?");
        question43.setAnswer1("Type 1");
        question43.setAnswer2("Type 2");
        question43.setAnswer3("Special");
        question43.setAnswer4("a and c");
        question43.setCorrectAnswer("3");
        question43.setType("hard");
        question43.setImage("13.jpg");
        insertQuestion(question43);

        Question question44 = new Question();
        //Question 44
        question44.setTitle("How many citizens of HCM City from 2014 ?");
        question44.setAnswer1("7.981.900");
        question44.setAnswer2("7.521.138");
        question44.setAnswer3("7.162.864");
        question44.setAnswer4("8.253.584");
        question44.setCorrectAnswer("1");
        question44.setType("hard");
        question44.setImage("14.jpg");
        insertQuestion(question44);

        Question question45 = new Question();
        //Question 45
        question45.setTitle("How many citizens of HCM City from 2014 ?");
        question45.setAnswer1("7.981.900");
        question45.setAnswer2("7.521.138");
        question45.setAnswer3("7.162.864");
        question45.setAnswer4("8.253.584");
        question45.setCorrectAnswer("1");
        question45.setType("hard");
        question45.setImage("15.jpg");
        insertQuestion(question45);

    }

}
