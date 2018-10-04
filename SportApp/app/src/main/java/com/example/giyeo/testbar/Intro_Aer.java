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
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Intro_Aer extends Fragment {

    private Context context;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
    private ImageView introAerImage;

    @SuppressLint("ValidFragment")
    private Intro_Aer() {

    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static Intro_Aer getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.intro_aer,container, false);

        setWhatIsAer(view);
        setDeatilInfo(view);

        return view;
    }

    public void setWhatIsAer(View view) {
        introAerImage = (ImageView) view.findViewById(R.id.introAerImage);
        Glide.with(view).load(R.drawable.aer_intro_550x333).into(introAerImage);
    }

    private void setDeatilInfo(View view) {
        expandableListView = (ExpandableListView) view.findViewById(R.id.aer_expandableListView);
        expandableListDetail = Intro_Aer_ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new Intro_Aer_ExpandableListAdapter(context, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
    }



    private static class LazyHolder {
        public static final Intro_Aer INSTANCE = new Intro_Aer();
    }
}
