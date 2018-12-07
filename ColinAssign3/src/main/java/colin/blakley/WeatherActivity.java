package colin.blakley;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class WeatherActivity extends AppCompatActivity {

    private String TAG = WeatherActivity.class.getSimpleName();
    private static String url;
    private ProgressDialog pDialog;

    Button btn;
    EditText editText;

    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    String url1 = "https://api.openweathermap.org/data/2.5/weather?zip=";
    String apiKey = "&appid=b2d8cf9973b9ee3bde5be5f7e4d25a7f";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btn = (Button) findViewById(R.id.colin13);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editText = (EditText) findViewById(R.id.colin11);
                String zipcode = editText.getText().toString();
                StringBuilder builder = new StringBuilder();
                builder.append(url1).append(zipcode).append(apiKey);
                url = builder.toString();
                new GetWeather().execute(url);
            }
        });


    }



    private class GetWeather extends AsyncTask<String, Void, Void> {

                @Override
                protected Void doInBackground(String... strings) {

                            HttpHandler sh = new HttpHandler();

                            // Making a request to url and getting response
                            final String jsonStr = sh.makeServiceCall(url);

                            Log.e(TAG, "Response from url: " + jsonStr);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (jsonStr != null) {
                                try {

                                    JSONObject jsonObj = new JSONObject(jsonStr);
                                    //coord object
                                    JSONObject coord = jsonObj.getJSONObject("coord");
                                    String lon = coord.getString("lon");
                                    String lat = coord.getString("lat");

                                    String name = jsonObj.getString("name");

                                    JSONObject main = jsonObj.getJSONObject("main");

                                    String humidity = main.getString("humidity");

                                    textView = (TextView) findViewById(R.id.colin08);
                                    textView2 = (TextView) findViewById(R.id.colin09);
                                    textView3 = (TextView) findViewById(R.id.colin10);
                                    textView4 = (TextView) findViewById(R.id.colin12);
                                    textView.setText(name);
                                    textView2.setText(lon);
                                    textView3.setText(lat);
                                    textView4.setText(humidity);



                                } catch (final JSONException e) {
                                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(),
                                                    "Json parsing error: " + e.getMessage(),
                                                    Toast.LENGTH_LONG)
                                                    .show();
                                        }
                                    });

                                }

                            } else {
                                Log.e(TAG, "Couldn't get json from server.");
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(),
                                                "Invalid ZipCode try again",
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }
                                });

                            }


                        }
                    });
                        return null;
                }


                @Override
                protected void onPostExecute(Void result) {
                    super.onPostExecute(result);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });


                }


            }



}
