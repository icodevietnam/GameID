package id.grw.com.idgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Play Game");
        //Hide Action Bar
        getSupportActionBar().hide();
        //set image of game
        ImageView gameImg = (ImageView)findViewById(R.id.gameImg);
        gameImg.setImageResource(R.drawable.multichoice);

        //Get Button Object
        Button btnPlay = (Button) this.findViewById(R.id.btnPlay);
        Button btnHighScore = (Button) this.findViewById(R.id.btnHighScore);

        btnPlay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ChooseLevelActivity.class);
                startActivity(intent);
            }
        });

        btnHighScore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HighScoreActivity.class);
                startActivity(intent);
            }
        });
    }
}
