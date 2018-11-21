/*
Colin Blakley
Brandon Lo
Brian Phan

 */
package colin.weatherio;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

import static android.os.SystemClock.sleep;

public class SensorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        /*
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        */

        try {

            while (true) {

                TextView tv1 = (TextView) findViewById(R.id.editText);
                tv1.setText(randomData());

                TextView tv2 = (TextView) findViewById(R.id.editText2);
                tv2.setText(randomData() + "%");
                Thread.sleep(1000);


            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public String randomData(){
        int data;

        Random rand = new Random();

        data = rand.nextInt(50)+1;
        String sData = Integer.toString(data);
       return sData;
    }
}
