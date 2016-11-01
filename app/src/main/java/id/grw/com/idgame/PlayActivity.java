package id.grw.com.idgame;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import id.grw.com.helper.DBHelper;
import id.grw.com.model.Level;
import id.grw.com.model.Question;

public class PlayActivity extends AppCompatActivity {

    private DBHelper myDB;

    public static final  Integer MAX_QUESTION = 8;

    private String level;

    private int count;
    private int score = 0;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //Hide Action Bar
        getSupportActionBar().hide();
        myDB = new DBHelper(this);

        TextView txtTitle = (TextView)findViewById(R.id.txtTitle);
        RadioButton radioValue1 = (RadioButton)findViewById(R.id.radioVal1);
        RadioButton radioValue2 = (RadioButton)findViewById(R.id.radioVal2);
        RadioButton radioValue3 = (RadioButton)findViewById(R.id.radioVal3);
        RadioButton radioValue4 = (RadioButton)findViewById(R.id.radioVal4);
        ImageView questionImg = (ImageView)findViewById(R.id.questionImage);
        final RadioGroup correctQuestion = (RadioGroup) findViewById(R.id.answerListVal);
        Button btnNext = (Button) findViewById(R.id.btnNext);
        // Get Intent
        final Intent intent = getIntent();
        level = intent.getStringExtra("level");
        final String idStr;
        final int standartPoint = getPointByLevel(level);
        count = intent.getIntExtra("beginPlay",1);
        score = intent.getIntExtra("score",0);
        name = intent.getStringExtra("name");
        if(count == 1){
            idStr = convertfromList(myDB,level,MAX_QUESTION);
        }else{
            idStr = intent.getStringExtra("idStr");
        }
        String[] arr = idStr.split(",");
        final Question question = myDB.getQuestionById(Integer.parseInt(arr[count-1]));
        txtTitle.setText("Cau " + count + ":" + question.getTitle() + "- Point:" + score);
        radioValue1.setText(question.getAnswer1());
        radioValue2.setText(question.getAnswer2());
        radioValue3.setText(question.getAnswer3());
        radioValue4.setText(question.getAnswer4());
        //set question image
        Drawable drawable = getDrawable(question.getImage());
        questionImg.setImageDrawable(drawable);


        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent newIntent = null;
                if(count >= 1 && count <MAX_QUESTION){
                    count++;
                    newIntent = new Intent(PlayActivity.this,PlayActivity.class);
                    newIntent.putExtra("level",level);
                    newIntent.putExtra("beginPlay",count);
                    newIntent.putExtra("idStr",idStr);
                    newIntent.putExtra("name",name);
                    newIntent.putExtra("score",getScore(correctQuestion,R.id.radioVal1,R.id.radioVal2,R.id.radioVal3,R.id.radioVal4,question,score,standartPoint,newIntent));
                    Log.d("array",idStr);
                    Log.d("Question Id:",question.getId()+"");
                    startActivity(newIntent);
                    finish();
                }else{
                    newIntent  = new Intent(PlayActivity.this,ThankYouActivity.class);
                    newIntent.putExtra("count",count);
                    newIntent.putExtra("score",score);
                    newIntent.putExtra("level",level);
                    newIntent.putExtra("name",name);
                    startActivity(newIntent);
                    finish();
                }
            }
        });
    }

    private Drawable getDrawable(String picName){
        Drawable drawable = null;
        String name ="";
        if(picName.contains("jpeg")){
            name = picName.substring(0,picName.length()-5);
        }else{
            name = picName.substring(0,picName.length()-4);
        }
        Resources res = getResources();
        int resId = res.getIdentifier(name,"drawable",getPackageName());
        if(resId!=0){
            drawable = res.getDrawable(resId);
        }else {
            drawable = res.getDrawable(0);
        }
        return drawable;
    }

    private int getScore(RadioGroup correctQuestion, int radio1, int radio2, int radio3, int radio4, Question question, int score, int levelScore, Intent newIntent){
        int rdGId = correctQuestion.getCheckedRadioButtonId();
        if(rdGId == -1){

        }else {
            if(( rdGId == radio1 && question.getCorrectAnswer().equalsIgnoreCase("1") ) || (rdGId == radio2 && question.getCorrectAnswer().equalsIgnoreCase("2") ) || (rdGId == radio3 && question.getCorrectAnswer().equalsIgnoreCase("3") ) || (rdGId == radio4 && question.getCorrectAnswer().equalsIgnoreCase("4") )){
                score+=levelScore;
                final MediaPlayer mp = MediaPlayer.create(this,R.raw.y);
                mp.start();
            }
        }
        return score;
    }

    private Integer getPointByLevel(String level){
        int point = 0;
        switch (level){
            case Level.EASY: point = 10;
                break;
            case Level.MEDIUM : point = 15;
                break;
            case Level.HARD : point = 20;
                break;
        }
        return point;
    }

    private String convertfromList(DBHelper myDB,String level,int maxQuestion){
        String idList = "";
        List<Question> listQuestions = myDB.selectQuestionsByLevel(level);
        Collections.shuffle(listQuestions);
        int count = 0;
        for(Question question : listQuestions){
            if(count < maxQuestion) {
                idList += question.getId() + ",";
            }
            count ++;
        }
        idList.substring(0,idList.length()-1);
        return idList;
    }
}
