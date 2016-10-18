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



}
