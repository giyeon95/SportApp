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
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
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



public class Course_Aer_Beginner extends Fragment implements Player.EventListener{

    private Context context;

    private final Uri firstVideoURL = Uri.parse("http://35.187.218.253/testmp4.mp4");
    private final Uri secondVideoURL = Uri.parse("http://35.187.218.253/Tridiary.mp4");
    private  ExoPlayer player;


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
        View view = inflater.inflate(R.layout.course_aer_beginner,container, false);
        init_Vedio(view);

        return view;
    }


    @Override
    public void onPositionDiscontinuity(int reason) {
        int sourceIndex = player.getCurrentWindowIndex();
        Log.e("tttttttttttta",sourceIndex+"입니다.");
    }

    @Override
    public void onLoadingChanged(boolean isLoading) {
        Log.e("dsadasdadas","onLoadingChanged!");
    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        Log.e("dasdasdasdada","onPlaybackParametersChanged");
    }

    private void init_Vedio(View view) {

        PlayerView playerView = (PlayerView)view.findViewById(R.id.aer_beginner_video);


        player = ExoPlayerFactory.newSimpleInstance(context);
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


    private static class LazyHolder {
        public static final Course_Aer_Beginner INSTANCE = new Course_Aer_Beginner();
    }

}
