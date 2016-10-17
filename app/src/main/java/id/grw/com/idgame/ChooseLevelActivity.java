package id.grw.com.idgame;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.grw.com.model.Level;


public class ChooseLevelActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);
        TextView textDescription = (TextView) findViewById(R.id.txtDescription);
        textDescription.setText("Please choose your level:");
        textDescription.setTextSize(20);
        textDescription.setTextColor(Color.WHITE);

        Button btnLevel1 = (Button)findViewById(R.id.btnLevel1);
        Button btnLevel2 = (Button)findViewById(R.id.btnLevel2);
        Button btnLevel3 = (Button)findViewById(R.id.btnLevel3);

        btnLevel1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLevelActivity.this,PlayActivity.class);
                intent.putExtra("level", Level.EASY);
                startActivity(intent);
            }
        });

        btnLevel2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLevelActivity.this,PlayActivity.class);
                intent.putExtra("level", Level.MEDIUM);
                startActivity(intent);
            }
        });

        btnLevel3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseLevelActivity.this,PlayActivity.class);
                intent.putExtra("level", Level.HARD);
                startActivity(intent);
            }
        });
    }
}
