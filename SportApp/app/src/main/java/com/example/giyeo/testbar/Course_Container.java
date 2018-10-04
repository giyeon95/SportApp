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




public class Course_Container extends Fragment{

    private Context context;
    private String course;
    private int level;

    private  ExoPlayer player;

    private int pageNum;
    private View view;

    @SuppressLint("ValidFragment")
    private Course_Container() {

    }

    public void setContext(Context context,String course ,int level) {
        this.context = context;
        this.course = course;
        this.level = level;
    }

    public static Course_Container getInstance() {
        return LazyHolder.INSTANCE;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_course_container,container, false);

        MainSetContents.getInstance().setContext(context,view);
        init_Contents();
        checkSetVideo(true);
        init_Vedio(view);

        return view;
    }


    private void init_Vedio(View view) {

        PlayerView playerView = (PlayerView)view.findViewById(R.id.video);
        player = null;
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
                        Log.e("state","BUFFERING");
                        break;
                    case Player.STATE_ENDED:
                        Log.e("state","END");
                        break;
                    case Player.STATE_IDLE:
                        Log.e("state","IDLE");
                        break;
                    case Player.STATE_READY:
                        Log.e("state","READY");
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

        ConcatenatingMediaSource clippingMediaSource = new ConcatenatingMediaSource();
        clippingMediaSource.clear();

        for (Uri uri: MainUriSource.getInstance().getUriList()) {
            MediaSource video = new ExtractorMediaSource.Factory(dataSourseFactory).createMediaSource(uri);
            clippingMediaSource.addMediaSource(video);
        }

        player.prepare(clippingMediaSource);
        player.setPlayWhenReady(true);

    }

    private void init_Contents() {
        if(course == "aer") {
            switch (level){
                case 0: MainSetContents.getInstance().aerBeginnerContents(0); break;
                case 1: MainSetContents.getInstance().aerIntermediateContents(0); break;
                case 2: MainSetContents.getInstance().aerExpertContents(0); break;
            }
        }
        if(course == "pila") {
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

        if(course == "aer") { //Aerobic
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
        if(course == "pila") { //Aerobic
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


    private static class LazyHolder {
        public static final Course_Container INSTANCE = new Course_Container();
    }

}
