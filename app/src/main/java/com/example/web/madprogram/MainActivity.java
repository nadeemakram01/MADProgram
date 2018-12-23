package com.example.web.madprogram;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                    MainFragment.OnFragmentInteractionListener,
                    HTMLCSSFragment.OnFragmentInteractionListener,
                    PHPFragment.OnFragmentInteractionListener,
                    TipFragment.OnFragmentInteractionListener,
                    JavaScriptFragment.OnFragmentInteractionListener,
                    FactFragment.OnFragmentInteractionListener,
                    ContactFragment.OnFragmentInteractionListener,
                    DataTypeFragment.OnFragmentInteractionListener,
                    OOPTerminologyFragment.OnFragmentInteractionListener,
                    UpdatedContactFragment.OnFragmentInteractionListener{

    //Step 1 email) build list of email addresses
    String[] emailAddresses = {"pnikita@stclaircollege.ca"};
    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fm = getSupportFragmentManager();
        //if this is the first time we run the app
        if(savedInstanceState == null){
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.content, new MainFragment(), "Java");
            transaction.commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, emailAddresses);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Question From the MAD App");
                intent.putExtra(Intent.EXTRA_TEXT, "I have a question about ...");
                //try to see if the phone has something that can resolve this intent
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "You do not have the correct software",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(R.anim.ferris_wheel_in,
                R.anim.ferris_wheel_out, R.anim.ferris_wheel_back_in,
                R.anim.ferris_wheel_back_out);
        if (id == R.id.nav_java) {
            Fragment selectedFragment = fm.findFragmentByTag("Java");
            if(selectedFragment == null) {
                transaction.replace(R.id.content, new MainFragment(), "Java");
                transaction.addToBackStack(null);
                transaction.commit();
            }
            else if(!selectedFragment.isVisible()){
                transaction.replace(R.id.content, new MainFragment(), "Java");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        } else if (id == R.id.nav_html) {
            Fragment selectedFragment = fm.findFragmentByTag("HTML");
            if(selectedFragment == null) {
                transaction.replace(R.id.content, new HTMLCSSFragment(), "HTML");
                transaction.addToBackStack(null);
                transaction.commit();
            }
            else if(!selectedFragment.isVisible()){
                transaction.replace(R.id.content, new HTMLCSSFragment(), "HTML");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        } else if (id == R.id.nav_javascript) {
            transaction.replace(R.id.content, new JavaScriptFragment(), "JavaScript");
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_php) {
            transaction.replace(R.id.content, new PHPFragment(), "PHP");
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_tips) {
            transaction.replace(R.id.content, new TipFragment(), "Tip");
            transaction.addToBackStack(null);
            transaction.commit();
        }
        else if(id == R.id.nav_contact){
            transaction.replace(R.id.content, new UpdatedContactFragment(), "Contact");
            transaction.addToBackStack(null);
            transaction.commit();
        }
        else if(id == R.id.nav_website){
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://scweb.ca/mad/"));
            if(intent.resolveActivity(getPackageManager())!= null){
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(),
                        "You do not have the correct software",
                        Toast.LENGTH_SHORT).show();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch(requestCode){
            case ContactFragment.PERMISSION_SEND_SMS:
                if(grantResults.length > 0 &&
                        (grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    //Permission was granted so send the message
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("smsto:"));
                    intent.putExtra("sms_body",
                            "Check this out! " +
                                    "https://www.youtube.com/watch?v=qdgkUkOIwXk");
                    if(intent.resolveActivity(getPackageManager()) != null){
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this,
                                "You do not have the correct software",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    //Permission was not granted, we should disable the button
                }
            break;

        }

    }

    public void onFragmentInteraction(Uri uri) {

    }
}
