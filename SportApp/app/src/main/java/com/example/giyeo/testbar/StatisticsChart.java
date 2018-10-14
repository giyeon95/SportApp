package com.example.giyeo.testbar;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class StatisticsChart extends Fragment {

    private Context context;
    private LineChart averageChart;
    private final String TAG = "statisticsChart";
    private String chartDate;
    private int time;
    private List<Entry> user_entries;
    private List<Entry> avge_entries;
    private TextView stText1;
    private TextView stText2;
    private ImageView stImage;



    @SuppressLint("ValidFragment")
    private StatisticsChart() {

    }
    public void setContext(Context context) { this.context = context; }

    public static StatisticsChart getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statistics_chart,container,false);

        stText1 = (TextView)view.findViewById(R.id.statusText);
        stText2 = (TextView)view.findViewById(R.id.statusUnderText);
        stImage = (ImageView)view.findViewById(R.id.statusImage);


        averageChart =(LineChart)view.findViewById(R.id.averageChart);
        averageChart.setTouchEnabled(false);

        XAxis xAxis = averageChart.getXAxis();
        xAxis . setGranularity ( 0.2f );
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);





        InsertData task = new InsertData();
        task.execute("http://35.187.218.253/user/getUserChart.php",String.valueOf(UserStatus.getUserId()), UserStatus.getUserName());

        return view;
    }

    private void addingData() {

    }

    protected float changeDate(String str) {

        String noYear = str.substring(5);
        noYear = noYear.replace('-','.');

        return  Float.parseFloat(noYear);
    }

    protected float changeTime(String str) {
        return Float.valueOf(str) / 60.0f;
    }


    private static class LazyHolder { public static final StatisticsChart INSTANCE = new StatisticsChart();}

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

            Log.d(TAG, "POST response  - " + result);

            try {
                JSONObject jsonObject = new JSONObject(result);

                JSONArray jsonArray = jsonObject.getJSONArray("user");

                int count =  jsonArray.length();



                user_entries = new ArrayList<Entry>();


                for(int i=0 ; i < count ; i++) {
                    JSONObject jsonUserObj = jsonArray.getJSONObject(i);
                    DecimalFormat df = new DecimalFormat("0.##");
                    Float.valueOf(df.format(changeDate(jsonUserObj.getString("date"))));

                    user_entries.add(new Entry(changeDate(jsonUserObj.getString("date")), changeTime(jsonUserObj.getString("sum(time)"))));
                }

                jsonArray = jsonObject.getJSONArray("uAvg");
                count = jsonArray.length();

                avge_entries = new ArrayList<Entry>();

                for(int i =0 ; i< count ; i++) {
                    JSONObject jsonUAvgObj = jsonArray.getJSONObject(i);

                    avge_entries.add(new Entry(changeDate(jsonUAvgObj.getString("date")), changeTime(jsonUAvgObj.getString("uAvg"))));
                }



                Log.e(TAG, user_entries+" this is user entries");

                Log.e(TAG, avge_entries+" this is avge entries");

                LineDataSet userDataSet = new LineDataSet(user_entries, "나의 운동량");
                LineDataSet avgeDataSet = new LineDataSet(avge_entries, "전체 평균 운동량");

                userDataSet.setDrawCircles(false);
                avgeDataSet.setDrawCircles(false);
                userDataSet.setColor(Color.RED);
                avgeDataSet.setColor(Color.BLUE);

                List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
                dataSets.add(userDataSet);
                dataSets.add(avgeDataSet);

                LineData LineData = new LineData(dataSets);
                averageChart.setData(LineData);
                averageChart.invalidate();

                /*TextView 연결 및 수치에 따른 이동*/
/*
                int iUpper=0;

                for(int i= 0 ; i< count ; i++) {
                    if(user_entries.get(i).getX() != avge_entries.get(i).getX()) {
                        iUpper = (user_entries.size()> avge_entries.size() ? iUpper++ :iUpper-- ;
                        for(int j=0 ; j< count ; j++) {
                            if(user_entries.get(i).getX() == avge_entries.get(j).getX()) {
                                iUpper = (user_entries.get(i).getY() > avge_entries.get(j).getY()) ? iUpper++ :iUpper-- ;
                            }
                        }
                    }
                }
    */


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
