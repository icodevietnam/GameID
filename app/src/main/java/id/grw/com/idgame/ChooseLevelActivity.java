package id.grw.com.idgame;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import id.grw.com.model.Level;


public class ChooseLevelActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_level);
        TextView textDescription = (TextView) findViewById(R.id.txtDescription);
        //Hide Action Bar
        getSupportActionBar().hide();
        textDescription.setText("Please choose your level:");
        textDescription.setTextSize(20);
        textDescription.setTextColor(Color.WHITE);


        final EditText textName = (EditText) findViewById(R.id.textName);


        final Button btnLevel1 = (Button)findViewById(R.id.btnLevel1);
        final Button btnLevel2 = (Button)findViewById(R.id.btnLevel2);
        final Button btnLevel3 = (Button)findViewById(R.id.btnLevel3);

        final Toast toast = Toast.makeText(this, "Please input your name :", Toast.LENGTH_LONG);

        btnLevel1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = textName.getText().toString();
                if(checkName(name)){
                    Intent intent = new Intent(ChooseLevelActivity.this,PlayActivity.class);
                    intent.putExtra("level", Level.EASY);
                    intent.putExtra("beginPlay",1);
                    startActivity(intent);
                }else {
                    toast.show();
                }
            }
        });

        btnLevel2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = textName.getText().toString();
                if(checkName(name)) {
                    Intent intent = new Intent(ChooseLevelActivity.this, PlayActivity.class);
                    intent.putExtra("level", Level.MEDIUM);
                    intent.putExtra("beginPlay", 1);
                    startActivity(intent);
                }else {
                    toast.show();
                }
            }
        });

        btnLevel3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = textName.getText().toString();
                if(checkName(name)){
                    Intent intent = new Intent(ChooseLevelActivity.this,PlayActivity.class);
                    intent.putExtra("level", Level.HARD);
                    intent.putExtra("beginPlay",1);
                    startActivity(intent);
                }else{
                    toast.show();
                }
            }
        });
    }

    private boolean checkName(String name){
        if(name.equalsIgnoreCase("") || name == null){
            return false;
        }
        return true;
    }
}
