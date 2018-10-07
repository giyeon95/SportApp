package com.example.giyeo.testbar;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GlobalApplication.setCurrentActivity(this);

        setNavHeader();

        getHashKey();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setfont();
        setStatusBar();
        setTitle("Sport Home Trainning");


        manager = setManager();
        Intro_Main.getInstance().setContext(this);
        manager.beginTransaction().replace(R.id.content_main, Intro_Main.getInstance()).commit();

    }

    private void setNavHeader() {
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navHeaderView = navigationView.getHeaderView(0);

        TextView userName = (TextView)navHeaderView.findViewById(R.id.userNameText);
        ImageView userImage = (ImageView)navHeaderView.findViewById(R.id.userProfile);
        TextView userLevel = (TextView)navHeaderView.findViewById(R.id.userLevel);

        userName.setText("사용자 : "+UserStatus.getUserName());
        userLevel.setText("일렬번호 : "+UserStatus.getUserId());
        Glide.with(navHeaderView).load(UserStatus.getUserImagePath()).apply(new RequestOptions().circleCrop()).into(userImage);
    }

    private void setStatusBar() {
        View view = getWindow().getDecorView();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(view != null) {
                view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getWindow().setStatusBarColor(Color.WHITE);
            } else if(Build.VERSION.SDK_INT >= 21 ) {
                getWindow().setStatusBarColor(Color.BLACK);
            }
        }
    }

    private void setfont() {
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("HoonPinkpungchaR.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private FragmentManager setManager() {
        return getSupportFragmentManager();
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

        switch (id) {
            case R.id.nav_Pla_Itd : {
                Intro_Pila.getInstance().setContext(this);
                manager.beginTransaction().replace(R.id.content_main, Intro_Pila.getInstance()).commit();

                break;
            }
            case R.id.nav_Pla_Main : {
                Main_Pila.getInstance().setContext(this, manager);
                manager.beginTransaction().replace(R.id.content_main, Main_Pila.getInstance()).commit();
                break;
            }
            case R.id.nav_Aer_Itd : {
                Intro_Aer.getInstance().setContext(this);
                manager.beginTransaction().replace(R.id.content_main, Intro_Aer.getInstance()).commit();
                break;
            }
            case R.id.nav_Aer_Main : {
                Main_Aer.getInstance().setContext(this, manager);
                manager.beginTransaction().replace(R.id.content_main, Main_Aer.getInstance()).commit();
                break;
            }
            default :
                Log.e ("ID", "ID Error Check Case Id");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void vedioStart(String course, int level) {
            Intent intent = new Intent(this,Course_Container.class);
            intent.putExtra("course",course);
            intent.putExtra("level",level);
            startActivity(intent);

    }

    private void getHashKey(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.giyeo.testbar", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("getHashKey","key_hash="+ Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


}
