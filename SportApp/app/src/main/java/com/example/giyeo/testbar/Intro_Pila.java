package com.example.giyeo.testbar;

import android.annotation.SuppressLint;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Intro_Pila extends Fragment {

    private Context context;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
    private ImageView introPilaImage;

    @SuppressLint("ValidFragment")
    private Intro_Pila() {

    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static Intro_Pila getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.intro_pila,container,false);


        setWhatIsPila(view);
        setDeatilInfo(view);

        return view;
    }
    public void setWhatIsPila(View view) {
        introPilaImage = (ImageView) view.findViewById(R.id.introPilaImage);
        Glide.with(view).load(R.drawable.pilates_intro_600x400).into(introPilaImage);
    }

    private void setDeatilInfo(View view) {
        expandableListView = (ExpandableListView) view.findViewById(R.id.pila_expandableListView);
        expandableListDetail = Intro_Pila_ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new Intro_Pila_ExpandableListAdapter(context, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
    }


    private static class LazyHolder {
        public static final Intro_Pila INSTANCE = new Intro_Pila();
    }
}
