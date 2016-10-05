package id.grw.com.idgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Hide Action Bar
        getSupportActionBar().hide();
        //set image of game
        ImageView gameImg = (ImageView)findViewById(R.id.gameImg);
        gameImg.setImageResource(R.drawable.multichoice);
    }
}
