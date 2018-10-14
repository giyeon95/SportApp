package com.example.giyeo.testbar;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class Intro_Main extends Fragment {

    Context context;
    View view;
    private TextView idText;
    private TextView nameText;
    private TextView lvText;
    private TextView timeText;
    private TextView expText;
    private TextView calText;
    private TextView toTimeText;
    private TextView toCalText;
    private TextView toExpText;
    private final String TAG = "introMain";

    @SuppressLint("ValidFragment")
    private Intro_Main() {

    }

    public void setContext(Context context) {
        this.context = context;
    }
    public static Intro_Main getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        InsertData task = new InsertData();
        task.execute("http://35.187.218.253/user/insertUser.php",String.valueOf(UserStatus.getUserId()), UserStatus.getUserName(), UserStatus.getUserImagePath());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.intro_main,container,false);
        Log.e("LifeCycle","onCreateView");

        idText = (TextView)view.findViewById(R.id.useridTV);
        nameText = (TextView) view.findViewById(R.id.userNameTV);
        lvText = (TextView)view.findViewById(R.id.userLvTV);
        timeText = (TextView)view.findViewById(R.id.userTimeTV);
        expText = (TextView)view.findViewById(R.id.userExpTV);
        calText = (TextView)view.findViewById(R.id.userCalTV);

        toCalText = (TextView)view.findViewById(R.id.todayCalTV);
        toTimeText = (TextView)view.findViewById(R.id.todayTimeTV);
        toExpText = (TextView)view.findViewById(R.id.todayExpTV);


        init_userDb();

        return view;
    }


    private void init_userDb() {
        idText.setText(String.valueOf(UserStatus.getUserId()));
        nameText.setText(UserStatus.getUserName());
        lvText.setText(UserStatus.getUserLv());
        timeText.setText(changeTime(UserStatus.getAllTime()));
        calText.setText(UserStatus.getAllCalorie()+" Cal");
        expText.setText(UserStatus.getAllExp()+" / 100000");

        toCalText.setText(UserStatus.getUserCalorie()+" Cal");
        toTimeText.setText(changeTime(UserStatus.getUserTime()));
        toExpText.setText(String.valueOf(UserStatus.getUserExp()));

    }
    private String changeTime(int  timeInt) {
        int sec = timeInt % 3600 % 60;
        int min = timeInt > 3600 ? timeInt % 3600 / 60 : timeInt / 60 ;
        int hour = timeInt/3600;

        String timeStr=  hour+"시간 "+min+"분 "+sec+"초 입니다.";

        return timeStr;
    }



    private static class LazyHolder {
        public static final Intro_Main INSTANCE = new Intro_Main();
    }

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(context,
                    "Please Wait", "User Data Connect Database", true, true);
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
                UserStatus.setAllExp(Integer.parseInt(jsonObj.getString("exp")));
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

                JSONObject jsonObj2 = jsonObject.getJSONObject("1");

                UserStatus.setUserCalorie(Integer.parseInt(jsonObj2.getString("calorie")));
                UserStatus.setUserTime(Integer.parseInt(jsonObj2.getString("time")));
                UserStatus.setUserExp(Integer.parseInt(jsonObj2.getString("exp")));

                init_userDb();
                ((MainActivity)getActivity()).setNavHeader();
            } catch(Exception e) {
                Log.e(TAG,"Error Reason : "+ e);
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String id = (String)params[1];
            String name = (String)params[2];
            String image = (String)params[3];

            String serverURL = (String)params[0];
            String postParameters = "id=" + id + "&name=" + name + "&image=" + image;


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
