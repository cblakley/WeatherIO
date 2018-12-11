//Colin Blakley
package colin.weatherio;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;

public class AddSensor extends AppCompatActivity {
    TextView Sensor_Serial_Number;
    TextView Name;
    EditText Your_Name;
    EditText Serial_Num;
    Button sndBtn;
    DBAdapter db;
    String NameDB;
    String SerialNumDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sensor);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DBAdapter(this);
        //db.open("MyDB.db");
        Your_Name = (EditText)findViewById(R.id.Your_Name);
        Serial_Num = (EditText)findViewById(R.id.Serial_Num);
        sndBtn = (Button)findViewById(R.id.sndBtn);



        sndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    if (Serial_Num.length() >= 5 && Your_Name.length() >= 0) {
                        NameDB = Your_Name.getText().toString();
                        SerialNumDB = Serial_Num.getText().toString();

                        db.insertSensor(NameDB,SerialNumDB);
                        Intent intent3 = new Intent(AddSensor.this, SensorActivity.class);
                        startActivity(intent3);

                    } else {
                        Toast.makeText(getBaseContext(), "Enter a name or valid Serial Number", Toast.LENGTH_LONG).show();

                    }

             db.close();
        }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(AddSensor.this,SettingsActivity.class);
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
                Intent intent2 = new Intent(AddSensor.this,About.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
