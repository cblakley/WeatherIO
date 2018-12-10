/*
Colin Blakley
Brandon Lo
Brian Phan

 */

package colin.weatherio;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    int[] images={R.drawable.ic_chancerain,R.drawable.ic_clear,R.drawable.ic_snow};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       displayHomeImage();


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
                            case R.id.nav_city:
                                Intent intent = new Intent(MainActivity.this,CityActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.nav_sensor:

                                    Intent intent2 = new Intent(MainActivity.this, SensorActivity.class);
                                    startActivity(intent2);

                                break;
                            case R.id.nav_add_sensor:
                                Intent intent3 = new Intent(MainActivity.this,AddSensor.class);
                                startActivity(intent3);
                                break;
                        }

                        return true;
                    }
                });
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.settings:

                return true;
            case R.id.info:

                return true;
            case R.id.about:

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
        //return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }
    public void displayHomeImage(){

        int x = (int) (Math.random() * 3);

        ImageView img = (ImageView) findViewById(R.id.img);
        img.setImageResource(images[x]);
    }


}