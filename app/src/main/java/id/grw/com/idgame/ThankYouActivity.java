package id.grw.com.idgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import id.grw.com.helper.DBHelper;
import id.grw.com.model.Result;

public class ThankYouActivity extends AppCompatActivity {

    private DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);
        //Hide Action Bar
        getSupportActionBar().hide();
        final Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int count = intent.getIntExtra("count",1);
        int score = intent.getIntExtra("score",0);
        String level = intent.getStringExtra("level");

        TextView textThankYou = (TextView)findViewById(R.id.textThankyou);
        TextView textRecommend = (TextView)findViewById(R.id.textRecommend);
        Button btnHome = (Button)findViewById(R.id.btnHome);
        btnHome.setText("Play Again.");
        textThankYou.setText("Congragulation,"+ name +" !!! ");
        textRecommend.setText("You have : " + score+ " points! Answerd: "+ count + ". With level: " + level );

        myDB = new DBHelper(this);

        Result result = new Result();
        result.setName(name);
        result.setPoint(score+"");
        DateFormat df = new SimpleDateFormat("dd/MM HH:mm");
        Date date = Calendar.getInstance().getTime();
        String now = df.format(date);
        result.setDate(now);
        myDB.insertResult(result);

        btnHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ThankYouActivity.this,MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }
}
