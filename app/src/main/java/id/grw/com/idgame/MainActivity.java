package id.grw.com.idgame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import id.grw.com.helper.DBHelper;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Play Game");
        //Hide Action Bar
        getSupportActionBar().hide();
        //Init 45 questions and some result
        dbHelper = new DBHelper(this);
        dbHelper.insert45Questions();
        //set image of game
        ImageView gameImg = (ImageView)findViewById(R.id.gameImg);
        gameImg.setImageResource(R.drawable.multichoice);

        //Get Button Object
        Button btnPlay = (Button) this.findViewById(R.id.btnPlay);
        Button btnHighScore = (Button) this.findViewById(R.id.btnHighScore);

        final MediaPlayer mp = MediaPlayer.create(this,R.raw.gold);
        mp.start();

        btnPlay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mp.stop();
                Intent intent = new Intent(MainActivity.this,ChooseLevelActivity.class);
                startActivity(intent);
                finish();
            }
        });


        btnHighScore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mp.stop();
                Intent intent = new Intent(MainActivity.this,HighScoreActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
