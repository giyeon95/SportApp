package com.example.giyeo.testbar;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

class UserAdapter extends BaseAdapter{

    private int userlayout;
    private ArrayList<userData> userData;
    private String userName;
    private String userLv;
    private String userTime;
    private String userCalorie;
    private String userImage;
    private Context context;
    private final String TAG ="userAdapter";


    public UserAdapter(Context context, int userlayout, ArrayList<userData> udata) {
        this.context = context;
        this.userData = udata;
        this.userlayout = userlayout;
    }

    @Override
    public int getCount() {
        return userData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    private String changeTime(int  timeInt) {
        int sec = timeInt % 3600 % 60;
        int min = timeInt > 3600 ? timeInt % 3600 / 60 : timeInt / 60 ;
        int hour = timeInt/3600;

        String timeStr=  hour+"시간 "+min+"분 "+sec+"초";

        return timeStr;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.statistics_rank_user,viewGroup,false);
        }

            userData uData = userData.get(position);
            userName = uData.getUserName();
            userLv = uData.getUserLv();
            userTime = uData.getUserTime();
            userCalorie = uData.getUserCalorie();
            userImage = uData.getUserImage();




            ImageView image = (ImageView) view.findViewById(R.id.userPhoto);
            TextView rankNumber = (TextView) view.findViewById(R.id.rankNumber);
            TextView rank_name = (TextView) view.findViewById(R.id.rank_name);
            TextView rank_level = (TextView) view.findViewById(R.id.rank_level);
            TextView rank_time = (TextView) view.findViewById(R.id.rank_time);
            TextView rank_cal = (TextView) view.findViewById(R.id.rank_cal);



            if (uData.getUserImage() != null)
                Glide.with(view).load(userImage).apply(new RequestOptions().circleCrop()).into(image);
            else {
                image.setImageResource(R.mipmap.ic_launcher_round);
            }

            rankNumber.setText(position + 1 + "등 ");
            rank_name.setText("이름 : " + userName);
            rank_level.setText("계급 : " +  uData.getUserLv());
            rank_time.setText("운동시간 : " + changeTime(Integer.parseInt(userTime)));
            rank_cal.setText("총 칼로리\n" + userCalorie + "cal");

            Log.e(TAG,"userData Count " +userData.size() );



        return view;
    }
}
