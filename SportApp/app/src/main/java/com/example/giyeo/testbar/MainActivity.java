package com.example.giyeo.testbar;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager manager;
   // public static FirebaseDatabase database;
    //public static DatabaseReference myRef;


    private final String TAG = "mainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        GlobalApplication.setCurrentActivity(this);

        setNavHeader();
        InsertData task = new InsertData();
        task.execute("http://35.187.218.253/user/insertUser.php",String.valueOf(UserStatus.getUserId()), UserStatus.getUserName());
        //connectDataBase();

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

        manager = setManager();
        Intro_Main.getInstance().setContext(this);
        manager.beginTransaction().replace(R.id.content_main, Intro_Main.getInstance()).commit();

    }





    private void connectDataBase() {
        /*
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("userStatus");


        //HashMap<String, String> map = new HashMap<String,String>();
        //map.put("id",String.valueOf(UserStatus.getUserId()));
        //map.put("name",UserStatus.getUserName());
        //map.put("lv","3");
        //map.put("time","10000");
        //map.put("exp","87");
       // map.put("calorie","2733");
        //myRef.child("users").child(String.valueOf(UserStatus.getUserId())).setValue(map);


        myRef.child("users").child(String.valueOf(UserStatus.getUserId())).child("id").setValue(String.valueOf(UserStatus.getUserId()));
        myRef.child("users").child(String.valueOf(UserStatus.getUserId())).child("name").setValue(String.valueOf(UserStatus.getUserName()));

        myRef.child("users").child(String.valueOf(UserStatus.getUserId())).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count = 0;
                for(DataSnapshot messageData : dataSnapshot.getChildren()) {
                    count++;
                }

                for(DataSnapshot messageData : dataSnapshot.getChildren()) {
                    if(count == 2) {
                        Log.e("testFirebase", "신규 회원!");
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put("id",String.valueOf(UserStatus.getUserId()));
                        map.put("name",UserStatus.getUserName());
                        map.put("lv","1");
                        map.put("time","0");
                        map.put("exp","0");
                        map.put("calorie","0");
                        myRef.child("users").child(String.valueOf(UserStatus.getUserId())).setValue(map);
                        break;
                    } else if(count != -1){
                        Log.e("testFirebase", "기존 회원!");
                        count = -1;
                    }

                    if(messageData.getKey().toString().equals("lv")) {
                        UserStatus.setUserLv(messageData.getValue().toString());
                        //Log.e("testFirebase","Inside For loop in function Lv value "+UserStatus.getUserLv());

                    } else if(messageData.getKey().toString().equals("time")) {
                        UserStatus.setUserTime(Integer.parseInt(messageData.getValue().toString()));
                        //Log.e("testFirebase","Inside For loop in function Time value "+UserStatus.getUserTime());

                    } else if(messageData.getKey().toString().equals("exp")) {
                        UserStatus.setUserExp(Integer.parseInt(messageData.getValue().toString()));
                        //Log.e("testFirebase","Inside For loop in function Exp value "+UserStatus.getUserExp());

                    } else if(messageData.getKey().toString().equals("calorie")) {
                        UserStatus.setUserCalorie(Integer.parseInt(messageData.getValue().toString()));
                        //Log.e("testFirebase","Inside For loop in function calorie value "+UserStatus.getUserCalorie());

                    }
                }
                Intro_Main.getInstance().init_userStatus();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/
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
        Log.e("mainActivity", UserStatus.getUserImagePath());
        Glide.with(navHeaderView).load(UserStatus.getUserImagePath()).apply(new RequestOptions().circleCrop()).into(userImage);

        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intro_Main.getInstance().init_userStatus();
                manager.beginTransaction().replace(R.id.content_main, Intro_Main.getInstance()).commit();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }
        });
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
                manager.beginTransaction().replace(R.id.content_main, Intro_Pila.getInstance(),"PlaItd").commit();

                break;
            }
            case R.id.nav_Pla_Main : {
                Main_Pila.getInstance().setContext(this, manager);
                manager.beginTransaction().replace(R.id.content_main, Main_Pila.getInstance(),"PlaMain").commit();
                break;
            }
            case R.id.nav_Aer_Itd : {
                Intro_Aer.getInstance().setContext(this);
                manager.beginTransaction().replace(R.id.content_main, Intro_Aer.getInstance(),"AerItd").commit();

                break;
            }
            case R.id.nav_Aer_Main : {
                Main_Aer.getInstance().setContext(this, manager);
                manager.beginTransaction().replace(R.id.content_main, Main_Aer.getInstance(),"AerMain").commit();
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



    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MainActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            //mTextViewResult.setText(result);
            Log.d(TAG, "POST response  - " + result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONObject jsonObj = jsonObject.getJSONObject("0");

                UserStatus.setAllCalorie(Integer.parseInt(jsonObj.getString("calorie")));
                UserStatus.setAllTime(Integer.parseInt(jsonObj.getString("time")));

                switch (Integer.parseInt(jsonObj.getString("lv"))) {
                    case 1:
                        UserStatus.setUserLv("Beginner");
                        break;
                    case 2:
                        UserStatus.setUserLv("Intermediate");
                        break;
                    case 3:
                        UserStatus.setUserLv("Expert");
                        break;
                }
                Log.e(TAG,"User Status : "+UserStatus.getUserLv() + ", "+UserStatus.getAllTime());
            } catch(Exception e) {
                Log.e(TAG,"Error Reason : "+ e);
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String id = (String)params[1];
            String name = (String)params[2];

            String serverURL = (String)params[0];
            String postParameters = "id=" + id + "&name=" + name;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {

                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
    }

}
