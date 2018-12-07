package colin.blakley;

import android.support.design.widget.NavigationView;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

import java.io.IOException;

import java.net.URL;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private String TAG = MainActivity.class.getSimpleName();
    private static String url = "https://samples.openweathermap.org/data/2.5/weather?zip=93709,us&appid=b2d8cf9973b9ee3bde5be5f7e4d25a7f";
    private ProgressDialog pDialog;

    ImageView mImageView;
    TextView tv;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date date = new Date();
        tv1 = (TextView) findViewById(R.id.colin01);
        String strDate = date.toString();
        tv1.setText(strDate);
        createFile();
        addSpinner();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {


                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();
                        switch (menuItem.getItemId()){
                            case R.id.CoHome:
                                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.CoDown:
                                Intent intent2 = new Intent(MainActivity.this,DownloadActivity.class);
                                startActivity(intent2);
                                break;
                            case R.id.BlSrv:
                                Intent intent3 = new Intent(MainActivity.this,WeatherActivity.class);
                                startActivity(intent3);
                                break;
                        }

                        return true;
                    }
                });



        mImageView = (ImageView) findViewById(R.id.colin06);
        //tv = (TextView) findViewById(R.id.colin07);

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

    }
    public void createFile(){
        try {
            File file = new File(this.getFilesDir(),"myFile.txt");
            FileOutputStream fos = openFileOutput("myFile.txt",Context.MODE_PRIVATE);
            fos.write("CENG317".getBytes());
            fos.write("\n".getBytes());
            fos.write("CENG318".getBytes());
            fos.write("\n".getBytes());
            fos.write("CENG319".getBytes());
            fos.write("\n".getBytes());
            fos.write("CENG320".getBytes());
            fos.write("\n".getBytes());
            fos.write("NEST204".getBytes());

            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void addSpinner(){

        try {
            BufferedReader br = new BufferedReader((new FileReader("myFile.txt")));
            List<String> spinnerList = new ArrayList<String>();
            String line;
            while((line = br.readLine())!= null){
                spinnerList.add(line);
            }
            br.close();
            String[] spinnerArray = spinnerList.toArray(new String[0]);
            Spinner s = (Spinner) findViewById(R.id.colin04);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, spinnerArray);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            s.setAdapter(adapter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }
}
