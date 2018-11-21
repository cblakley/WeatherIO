package colin.weatherio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddSensor extends AppCompatActivity {
    TextView Sensor_Serial_Number;
    TextView Name;
    EditText Your_Name;
    EditText Serial_Num;
    Button sndBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sensor);

        final DBAdapter db = new DBAdapter(this);
        Your_Name = (EditText)findViewById(R.id.Your_Name);
        Serial_Num = (EditText)findViewById(R.id.Serial_Num);
        sndBtn = (Button)findViewById(R.id.sndBtn);

        final String NameDB =Your_Name.getText().toString();
        final String SerialNumDB = Serial_Num.getText().toString();

        sndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String NameDB =Your_Name.getText().toString();
                String SerialNumDB = Serial_Num.getText().toString();

                if(Serial_Num.length()>5 && Your_Name.length()>0){
                    db.insertSensor(SerialNumDB,NameDB);
                }else{
                    Toast.makeText(getBaseContext(),"Enter a name or valid Serial Number",Toast.LENGTH_LONG);

                }
            }
        });




    }
}
