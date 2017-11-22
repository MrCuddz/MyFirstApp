package com.example.VapeToolKit;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private String[] mDrawerTitles;
    private DrawerLayout mDrawer;
    private ListView mDrawerListView;
    private ActionBarDrawerToggle mDrawerToggle;
    private TextView messageTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerTitles = getResources().getStringArray(R.array.menuTitles_array);
        messageTextView = (TextView) findViewById(R.id.messageTextView);
        mDrawer= (DrawerLayout) findViewById(R.id.drawer_layout);

        // These lines are needed to display the top-left hamburger button
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //make ha,burger button work
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.app_name,R.string.app_name){
          @Override
            public void onDrawerClosed(View drawerView){

          }
          @Override
            public void onDrawerOpened(View drawerView){

          }
        };
        mDrawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        // Change Activity when ListView item is clicked
        mDrawerListView = (ListView) findViewById(R.id.left_drawer);
        mDrawerListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO create activity on click
                /*// Start NewActivity.class
				Intent myIntent = new Intent(MainActivity.this,
						NewActivity.class);
				startActivity(myIntent);*/
                selectItem(position);
                //messageTextView.setText("Menu Item at position " + position + " clicked.");;
                mDrawer.closeDrawer(GravityCompat.START);
            }
        });
    }

    private void selectItem(int position) {
        Fragment fragment = null;
        //FragmentTransaction transaction  = getSupportFragmentManager().beginTransaction();

        switch (position){
            case 0:
                fragment = new coilBuilderFragment();
                break;
            case 1:
                fragment = new ohmsLawFragment();
                break;
            case 2:
                fragment = new batteryDatabaseFragment();
                break;
            case 3:
                fragment = new concentratesCabinetFragment();
                break;
            case 4:
                fragment = new mixByVolumeFragment();
                break;
            case 5:
                fragment = new mixByWeightFragment();
                break;
            case 6:
                fragment = new recipeDatabaseFragment();
                break;
            case 7:
                fragment = new unitConverterFragment();
                break;
            case 8:
                fragment = new quittersDiaryFragment();
                break;
            case 9:
                fragment = new settingsFragment();
                break;
            default:
                break;
        }

        if(fragment!=null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
        setTitle(mDrawerTitles[position]);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}

