package com.example.giyeo.testbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;



public class Course_Aer_Beginner extends Fragment{

    private Context context;

    private final Uri firstVideoURL = Uri.parse("http://35.187.218.253/testmp4.mp4");
    private final Uri secondVideoURL = Uri.parse("http://35.187.218.253/Tridiary.mp4");
    private  ExoPlayer player;
    private TextView titleText;
    private TextView contentsText;
    private TextView progressText;
    private TextView benefitText;
    private Course_Data_Connect dataConnect;



    private int pageNum;
    private View view;

    @SuppressLint("ValidFragment")
    private Course_Aer_Beginner() {

    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static Course_Aer_Beginner getInstance() {
        return LazyHolder.INSTANCE;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.course_aer_beginner,container, false);
        init_Vedio(view);

        return view;
    }

    private void init_Vedio(View view) {

        PlayerView playerView = (PlayerView)view.findViewById(R.id.aer_beginner_video);


        player = ExoPlayerFactory.newSimpleInstance(context);

        player.addListener(new Player.EventListener() {
            @Override
            public void onPlayerError(ExoPlaybackException error) {
                Log.e("error", "Error Reason : "+error );
            }

            @Override
            public void onPositionDiscontinuity(int reason) { // reason :0 = change window / 1 = 15sec preview or before reason / 2 = change Button window
                pageNum = player.getCurrentWindowIndex();

                switch (reason) {
                    case 0: //change window
                        init_Contents(true);
                        break;
                    case 1: // 15sec priview or before

                        break;
                    case 2: //click button and change window
                        init_Contents(false);
                        break;

                }

            }
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                switch(playbackState) {
                    case Player.STATE_BUFFERING:
                        Log.e("ttatata","BUFFERING");
                        break;
                    case Player.STATE_ENDED:
                        Log.e("ttatata","END");
                        break;
                    case Player.STATE_IDLE:
                        Log.e("ttatata","IDLE");
                        break;
                    case Player.STATE_READY:
                        Log.e("ttatata","READY");
                        break;

                }

            }
        });

        playerView.setPlayer(player);

        String userAgent = Util.getUserAgent(context,"TestBar");
        DefaultHttpDataSourceFactory httpDataSourceFactory = new DefaultHttpDataSourceFactory(
            userAgent,
                null,
                DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS,
                DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
                true
        );

        DataSource.Factory dataSourseFactory = new DefaultDataSourceFactory(context,null,httpDataSourceFactory);
        MediaSource firstVideo = new ExtractorMediaSource.Factory(dataSourseFactory).createMediaSource(firstVideoURL);
        MediaSource secondVideo = new ExtractorMediaSource.Factory(dataSourseFactory).createMediaSource(secondVideoURL);
        ConcatenatingMediaSource clippingMediaSource = new ConcatenatingMediaSource(firstVideo, secondVideo);



        player.prepare(clippingMediaSource);
        player.setPlayWhenReady(true);

    }

    private void init_Contents(boolean autoChange) {

        titleText = (TextView)view.findViewById(R.id.aer_beginner_title);
        contentsText = (TextView)view.findViewById(R.id.aer_beginner_contents);
        progressText = (TextView)view.findViewById(R.id.aer_beginner_progress);
        benefitText = (TextView)view.findViewById(R.id.aer_beginner_benefit);

        setContentsText();

        if(autoChange) { //시간이 되었을때 자동으로 움직임


        } else { //수동작으로 넘김

        }
    }
    private void setContentsText() {

        switch (pageNum) {
            case 0 : {
                titleText.setText(dataConnect.getInstance().courseAerBeginner1.getTitleText());
                contentsText.setText(dataConnect.getInstance().courseAerBeginner1.getContentsText());
                progressText.setText(dataConnect.getInstance().courseAerBeginner1.getProgressText());
                benefitText.setText(dataConnect.getInstance().courseAerBeginner1.getBenefitText());
                break;
            }
            case 1 : {
                titleText.setText(dataConnect.getInstance().courseAerBeginner2.getTitleText());
                contentsText.setText(dataConnect.getInstance().courseAerBeginner2.getContentsText());
                progressText.setText(dataConnect.getInstance().courseAerBeginner2.getProgressText());
                benefitText.setText(dataConnect.getInstance().courseAerBeginner2.getBenefitText());
                break;
            }
            case 2 : {
                break;
            }
        }
    }


    private static class LazyHolder {
        public static final Course_Aer_Beginner INSTANCE = new Course_Aer_Beginner();
    }

}
