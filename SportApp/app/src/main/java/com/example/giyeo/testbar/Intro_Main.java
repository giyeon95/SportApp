package com.example.giyeo.testbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Intro_Main extends Fragment {

    Context context;
    View view;
    TextView textView;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.intro_main,container,false);
        Log.e("LifeCycle","onCreateView");
        textView = (TextView)view.findViewById(R.id.main_topText);
        textView.setText(UserStatus.getUserName());
        this.view = view;
        return view;
    }


    public void init_userStatus() {
        textView.setText(UserStatus.getUserName());
    }


    private static class LazyHolder {
        public static final Intro_Main INSTANCE = new Intro_Main();
    }

}
