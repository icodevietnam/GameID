package id.grw.com.idgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import id.grw.com.helper.DBHelper;
import id.grw.com.model.Level;
import id.grw.com.model.Question;

public class PlayActivity extends AppCompatActivity {

    private DBHelper myDB;

    private static final Integer MEDIUM_QUESTION =  5;

    public static final  Integer MAX_QUESTION = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //Hide Action Bar
        getSupportActionBar().hide();
        myDB = new DBHelper(this);

        TextView txtTitle = (TextView)findViewById(R.id.txtTitle);
        // Get Intent
        Intent intent = getIntent();
        String level = intent.getStringExtra("level");
        int point = getPointByLevel(level);
        int count = intent.getIntExtra("beginPlay",1);
        Log.d("Count ne:","Biendem" + count);
        Intent intentNew = null;
        if(count <= 1 ){
            String idStr = convertfromList(myDB,level,MEDIUM_QUESTION);
            String[] arr = idStr.split(",");
            Question question = myDB.getQuestionById(Integer.parseInt(arr[count-1]));
            txtTitle.setText(question.getTitle());
        }else if(count > 1 && count <= MEDIUM_QUESTION){
            intent.getStringExtra("idQuestion");

        }else{
            intentNew = new Intent(PlayActivity.this,ThankYouActivity.class);
        }
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
