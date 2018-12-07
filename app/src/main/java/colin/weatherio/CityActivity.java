/*
Colin Blakley
Brandon Lo
Brian Phan

 */
package colin.weatherio;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CityActivity extends AppCompatActivity {

    private String TAG = CityActivity.class.getSimpleName();
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
        setContentView(R.layout.activity_city);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String zipcode = "90210";
        StringBuilder builder = new StringBuilder();
        builder.append(url1).append(zipcode).append(apiKey);
        url = builder.toString();
        new GetWeather().execute(url);

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
                            //weather object
                            JSONArray jArr = jsonObj.getJSONArray("weather");
                            JSONObject weather = jArr.getJSONObject(0);
                            String condition = weather.getString("main");
                            String description = weather.getString("description");

                            //String name = jsonObj.getString("name");

                            JSONObject main = jsonObj.getJSONObject("main");

                            String humidity = main.getString("humidity");
                            String temp = main.getString("temp");

                            textView = (TextView) findViewById(R.id.temp);
                            textView2 = (TextView) findViewById(R.id.humidity);
                            textView3 = (TextView) findViewById(R.id.main);
                            textView4 = (TextView) findViewById(R.id.description);
                            textView.setText(getRealTemp(temp));//method to change the passed value to Celuis
                            textView2.setText(condition);
                            textView3.setText(description);
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

    private String getRealTemp(String temp) {
        double dTemp = Math.round(Double.parseDouble(temp) - 273.15);
        String realTemp = Double.toString(dTemp);
        return realTemp;

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

                return true;
            case R.id.info:

                return true;
            case R.id.about:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
