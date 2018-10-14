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
import android.widget.ListView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class StatisticsRank extends Fragment {

    private Context context;
    private ListView listView;
    private UserAdapter userAdapter;
    private ArrayList<userData> userData = new ArrayList<>();
    private final String TAG  = "statisticsRank";

    @SuppressLint("ValidFragment")
    private StatisticsRank() {

    }
    public void setContext(Context context) { this.context = context; }

    public static StatisticsRank getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistics_rank,container,false);

        listView = (ListView)view.findViewById(R.id.userListView);

        userData.clear();
        InsertData task = new InsertData();
        task.execute("http://35.187.218.253/user/getRankUser.php",String.valueOf(UserStatus.getUserId()));


        return view;
    }



    private static class LazyHolder {
        public static final StatisticsRank INSTANCE = new StatisticsRank();
    }

    private String changeLv(String str) {
        String restr= "tmp";

        switch (str) {
            case "1":
                restr="Beginner";
                break;
            case "2":
                restr="Intermediate";
                break;
            case "3":
                restr="Expert";
                break;
        }

        return restr;
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

            userData[] user;

            try {
                JSONObject jsonObject = new JSONObject(result);
                user = new userData[jsonObject.length()];

                for(int i=0 ; i< jsonObject.length(); i++) {
                    JSONObject jsonObj = jsonObject.getJSONObject(String.valueOf(i));
                    user[i] = new userData(jsonObj.getString("name"),changeLv(jsonObj.getString("lv")),jsonObj.getString("time"),jsonObj.getString("calorie"),jsonObj.getString("image"));
                }


                for(int i=0 ; i < jsonObject.length() ; i++) {
                    userData.add(user[i]);
                }
                Log.e(TAG,userData.get(0).getUserLv()+"입니다.");

                Log.e(TAG,userData.get(1).getUserLv()+"입니다.");


                userAdapter = new UserAdapter(context, R.layout.statistics_rank_user, userData);
                listView.setAdapter(userAdapter);

                userAdapter.notifyDataSetChanged();


            } catch(Exception e) {
                Log.e(TAG,"Error Reason : "+ e);
            }

        }


        @Override
        protected String doInBackground(String... params) {

            String id = (String)params[1];


            String serverURL = (String)params[0];
            String postParameters = "id=" + id;


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
