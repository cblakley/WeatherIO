package colin.weatherio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class AddSensor extends AppCompatActivity {
    TextView Sensor_Serial_Number;
    TextView Name;
    EditText Your_Name;
    EditText Serial_Num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sensor);
        Your_Name = (EditText)findViewById(R.id.Your_Name);
        Serial_Num = (EditText)findViewById(R.id.Serial_Num);
        String SerialNumDB = Serial_Num.getText().toString();
        String NameDB =Your_Name.getText().toString();


    }
}
