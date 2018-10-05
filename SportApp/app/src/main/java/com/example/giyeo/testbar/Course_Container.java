package com.example.giyeo.testbar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


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

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class Course_Container extends AppCompatActivity{

    private String course;
    private int level;
    private  ExoPlayer player;
    private int pageNum;


    private ConcatenatingMediaSource clippingMediaSource;
    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;



    public Course_Container() {

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_course_container);

        course = getIntent().getStringExtra("course");
        level = getIntent().getIntExtra("level",0);

        setfont();
        TextView titleText = (TextView)findViewById(R.id.title);
        TextView contentsText = (TextView)findViewById(R.id.contents);
        TextView progressText = (TextView)findViewById(R.id.progress);
        TextView benefitText = (TextView)findViewById(R.id.benefit);

        MainSetContents.getInstance().setContext(titleText,contentsText,progressText,benefitText);
        init_Contents();
        checkSetVideo(true);
        init_Vedio();
    }


    private void init_Vedio() {

        PlayerView playerView = (PlayerView)findViewById(R.id.video);
        player = ExoPlayerFactory.newSimpleInstance(this);

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
                        updateContents(true);
                        break;
                    case 1: // 15sec priview or before

                        break;
                    case 2: //click button and change window
                        updateContents(false);
                        break;

                }

            }
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                switch(playbackState) {
                    case Player.STATE_BUFFERING:
                        Log.e("state!!!","BUFFERING");
                        break;
                    case Player.STATE_ENDED:
                        Log.e("state!!!","END");
                        break;
                    case Player.STATE_IDLE:
                        Log.e("state!!!","IDLE");
                        break;
                    case Player.STATE_READY:
                        Log.e("state!!!","READY");
                        break;

                }

            }
        });

        playerView.setPlayer(player);

        String userAgent = Util.getUserAgent(this,"TestBar");
        DefaultHttpDataSourceFactory httpDataSourceFactory = new DefaultHttpDataSourceFactory(
            userAgent,
                null,
                DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS,
                DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
                true
        );

        DataSource.Factory dataSourseFactory = new DefaultDataSourceFactory(this,null,httpDataSourceFactory);

        clippingMediaSource = new ConcatenatingMediaSource();


        for (Uri uri: MainUriSource.getInstance().getUriList()) {
            MediaSource video = new ExtractorMediaSource.Factory(dataSourseFactory).createMediaSource(uri);
            clippingMediaSource.addMediaSource(video);
        }

        player.prepare(clippingMediaSource);
        player.setPlayWhenReady(true);

    }

    private void init_Contents() {
        if(course.equals("aer")) {
            switch (level){
                case 0: MainSetContents.getInstance().aerBeginnerContents(0); break;
                case 1: MainSetContents.getInstance().aerIntermediateContents(0); break;
                case 2: MainSetContents.getInstance().aerExpertContents(0); break;
            }
        }
        if(course.equals("pila")) {
            switch (level){
                case 0: MainSetContents.getInstance().pilaBeginnerContents(0); break;
                case 1: MainSetContents.getInstance().pilaIntermediateContents(0); break;
                case 2: MainSetContents.getInstance().pilaExpertContents(0); break;
            }
        }
    }


    private void updateContents(boolean autoChange) {

        checkSetVideo(false);

        if(autoChange) { //시간이 되었을때 자동으로 움직임


        } else { //수동작으로 넘김

        }
    }

    private void checkSetVideo(boolean isVedio) {

        if(course.equals("aer")) { //Aerobic
            switch (level) {
                case 0: //Beginner
                    if(isVedio) { MainUriSource.getInstance().aerBeginnerUri(); }
                        else { MainSetContents.getInstance().aerBeginnerContents(pageNum);}
                    break;
                case 1: //Intermediate
                    if(isVedio) { MainUriSource.getInstance().aerIntermediateUri(); }
                    else { MainSetContents.getInstance().aerIntermediateContents(pageNum);}
                    break;
                case 2: //Expert
                    if(isVedio) { MainUriSource.getInstance().aerExpertUri(); }
                    else { MainSetContents.getInstance().aerExpertContents(pageNum);}
                    break;
            }
        }
        if(course.equals("pila")) { //Aerobic
            switch (level) {
                case 0: //Beginner
                    if(isVedio) {MainUriSource.getInstance().pilaBeginnerUri(); }
                    else {MainSetContents.getInstance().pilaBeginnerContents(pageNum);}
                    break;
                case 1: //Intermediate
                    if(isVedio) {MainUriSource.getInstance().pilaIntermediateUri();}
                    else {MainSetContents.getInstance().pilaIntermediateContents(pageNum);}
                    break;
                case 2: //Expert
                    if(isVedio) {MainUriSource.getInstance().pilaExpertUri();}
                    else {MainSetContents.getInstance().pilaExpertContents(pageNum);}
                    break;
            }
        }

    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            //super.onBackPressed();
            player = null;
            clippingMediaSource.clear();
            this.finish();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(this, "뒤로 가시겠습니까?", Toast.LENGTH_SHORT).show();
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



}
