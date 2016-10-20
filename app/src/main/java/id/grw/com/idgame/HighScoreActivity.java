package id.grw.com.idgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import id.grw.com.helper.DBHelper;
import id.grw.com.model.Result;

public class HighScoreActivity extends AppCompatActivity {

    private DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        //Hide Action Bar
        getSupportActionBar().hide();
        myDB = new DBHelper(this);

        List<Result> top10Result =  myDB.selectTop10();
        String rank = "";
        int count = 0;
        for(Result result : top10Result){
            count++;
            rank+= count+". \t "+ " Name : " + result.getName() + " ....... " +result.getPoint() + "pts [" +result.getDate() +" ] \n" ;
        }
        TextView listRank = (TextView)findViewById(R.id.textListRanking);
        listRank.setText(rank);
    }
}
