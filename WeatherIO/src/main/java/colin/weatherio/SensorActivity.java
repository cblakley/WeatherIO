/*
Colin Blakley

 */
package colin.weatherio;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.Random;




public class SensorActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                    TextView tv1 = (TextView) findViewById(R.id.editText);
                    tv1.setText(randomData());

                    TextView tv2 = (TextView) findViewById(R.id.editText2);
                    tv2.setText(randomData() + "%");


    }
    public String randomData(){
        int data;

        Random rand = new Random();

        data = rand.nextInt(50)+1;
        String sData = Integer.toString(data);
       return sData;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(SensorActivity.this,SettingsActivity.class);
                startActivity(intent);

                return true;
            case R.id.git:
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_VIEW);
                intent1.addCategory(Intent.CATEGORY_BROWSABLE);
                intent1.setData(Uri.parse("https://github.com/cblakley/WeatherIO"));
                startActivity(intent1);


                return true;
            case R.id.about:
                Intent intent2 = new Intent(SensorActivity.this,About.class);
                startActivity(intent2);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
