/*
Colin Blakley
Brandon Lo
Brian Phan

 */
package colin.weatherio;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class CityActivity extends AppCompatActivity {

    private String TAG = CityActivity.class.getSimpleName();
    private static String url;
    private ProgressDialog pDialog;
    private ImageView imgView;

    Button btn;
    EditText editText;
    EditText editText2;

    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    String url1 = "https://api.openweathermap.org/data/2.5/weather?q=";
    String apiKey = "&appid=b2d8cf9973b9ee3bde5be5f7e4d25a7f";

    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) findViewById(R.id.inputCity);
                EditText editText2 = (EditText) findViewById(R.id.inputCountry);
                String city = editText.getText().toString();
                String country= editText2.getText().toString();
                StringBuilder builder1 = new StringBuilder();
                builder1.append(city).append(",").append(country);
                String cityCountry = builder1.toString();
                StringBuilder builder = new StringBuilder();
                builder.append(url1).append(cityCountry).append(apiKey);
                url = builder.toString();
                new GetWeather().execute(url);
            }
        });


    }
    private class GetWeather extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {

            final HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            final String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

                    if (jsonStr != null) {
                        try {

                            JSONObject jsonObj = new JSONObject(jsonStr);
                            //weather object
                            JSONArray jArr = jsonObj.getJSONArray("weather");
                            JSONObject weather = jArr.getJSONObject(0);
                            final String condition = weather.getString("main");
                            final String description = weather.getString("description");
                            final String icon = weather.getString("icon");

                            final String name = jsonObj.getString("name");

                            JSONObject main = jsonObj.getJSONObject("main");

                            String humidity = main.getString("humidity");
                            final String temp = main.getString("temp");

                            final byte[] iconData = sh.getImage(icon);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView = (TextView) findViewById(R.id.temp);
                                    textView2 = (TextView) findViewById(R.id.humidity);
                                    textView3 = (TextView) findViewById(R.id.main);
                                    textView4 = (TextView) findViewById(R.id.description);
                                    textView5 = (TextView) findViewById(R.id.cityname);

                                    textView5.setText(name);
                                    textView.setText(getRealTemp(temp));//method to change the passed value to Celius
                                    textView2.setText(condition);
                                    textView3.setText(description);
                                    textView4.setText(icon);

                                    img = (ImageView) findViewById(R.id.img);
                                    if (iconData != null && iconData.length > 0) {
                                        Bitmap wIcon = BitmapFactory.decodeByteArray(iconData, 0, iconData.length);
                                        img.setImageBitmap(wIcon);

                                    } else {
                                        Toast.makeText(getApplicationContext(),
                                                "Couldn't get image data",
                                                Toast.LENGTH_LONG)
                                                .show();
                                    }

                                }
                            });

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
                                        "Invalid City or Country",
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                        });

                    }




            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);











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
