package com.marble;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.marble.utils.PreferenceManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("Price");
            //The key argument here must match that used in the other activity

            if(!value.equals(null))
            {
                Bundle bundle = new Bundle();
                Fragment fragment = new CalculatorFragment();
                FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment);
                bundle.putString("price",value);
                fragment.setArguments(bundle);
                fragmentTransaction.commit();


            }else
            {
                setupHome();
            }
        }else
        {
            setupHome();
        }

         navigationView = (NavigationView) findViewById(R.id.nav_view);
         View headerview = navigationView.getHeaderView(0);
         findHeader(headerview);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupHome() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction().add(R.id.frame_layout, new HomeFragment()).commit();
    }

    private void findHeader(View headerview) {
        String UserId = PreferenceManager.getprefUserId(MainActivity.this);
        String Username = PreferenceManager.getprefUserFirstName(MainActivity.this);
        String Email = PreferenceManager.getprefUserEmail(MainActivity.this);
        TextView tvProfileName = (TextView) headerview.findViewById(R.id.profile_name);
        tvProfileName.setText(Username);
        TextView tvProfileemail =(TextView) headerview.findViewById(R.id.profile_email);
        tvProfileemail.setText(Email);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (checkmenuitem() != 0){
                navigationView.setCheckedItem(R.id.nav_home);
                Fragment fragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
            }

            super.onBackPressed();
        }
    }

    private int checkmenuitem() {
        Menu menu = navigationView.getMenu();
        for (int i=0; i<menu.size(); i++)
        {
            if (menu.getItem(i).isChecked())
            {
                return i;
            }
        }
        return  -1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment=null;
        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
        fragment = new GalleryFragment();
        }
        else if (id == R.id.nav_calculator) {
            fragment = new CalculatorFragment();

        }
        else if (id == R.id.nav_feedback) {
            fragment = new FeedbackFragment();
        }
        else if (id == R.id.nav_faq) {
            fragment = new FaqFragment();

        }else if (id == R.id.nav_aboutus) {
            fragment = new AboutusFragment();
        }
        else if (id == R.id.nav_dealer) {
            fragment = new DealerFragment();
        }
        else if (id == R.id.nav_retailer) {
            fragment = new RetailerFragment();
        }/*
        else if (id == R.id.nav_catelog) {
            fragment = new CatelogFragment();
        }*/
        else if (id == R.id.nav_share) {
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

            // Add data to the intent, the receiving app will decide
            // what to do with it.
            intent.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
            intent.putExtra(Intent.EXTRA_TEXT, "https://goo.gl/FwzK5m");

            startActivity(Intent.createChooser(intent, "Share link!"));
        }



        else if (id == R.id.nav_logout) {
            //PreferenceManager.removePref(MainActivity.this, "USER_ID");
            PreferenceManager.removePref(MainActivity.this, "Username");
            PreferenceManager.removePref(MainActivity.this, "email");
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
        }

        if (fragment != null)
        {
            FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
