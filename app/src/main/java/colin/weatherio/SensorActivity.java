/*
Colin Blakley
Brandon Lo
Brian Phan

 */
package colin.weatherio;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class SensorActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);


        TextView tv1 = (TextView) findViewById(R.id.editText);
        tv1.setText(randomData());

        TextView tv2 = (TextView) findViewById(R.id.editText2);
        tv2.setText(randomData());

    }

    public String randomData() {
        int data;

        Random rand = new Random();

        data = rand.nextInt(50) + 1;
        String sData = Integer.toString(data);
        return sData;
    }


        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            switch (item.getItemId()) {
                case android.R.id.home:
                    mDrawerLayout.openDrawer(GravityCompat.START);
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }

}

