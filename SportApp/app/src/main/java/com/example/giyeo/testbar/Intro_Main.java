package com.example.giyeo.testbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Intro_Main extends Fragment {

    Context context;


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

        return view;
    }


    private static class LazyHolder {
        public static final Intro_Main INSTANCE = new Intro_Main();
    }

}
