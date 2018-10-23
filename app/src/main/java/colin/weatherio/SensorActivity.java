/*
Colin Blakley
Brandon Lo
Brian Phan

 */
package colin.weatherio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class SensorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        /*
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        */
        TextView tv1 = (TextView)findViewById(R.id.editText);
        tv1.setText(randomData());

        TextView tv2 = (TextView)findViewById(R.id.editText2);
        tv2.setText(randomData());

    }
    public String randomData(){
        int data;

        Random rand = new Random();

        data = rand.nextInt(50)+1;
        String sData = Integer.toString(data);
       return sData;
    }
}
