package com.example.giyeo.testbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Main_Aer extends Fragment {

    private Context context;
    private Button courseBtn_1;
    private Button courseBtn_2;
    private Button courseBtn_3;
    private FragmentManager manager;

    //private ImageView beginnerBtn;
    @SuppressLint("ValidFragment")
    private Main_Aer() {

    }

    public void setContext(Context context, FragmentManager manager) {

        this.context = context;
        this.manager = manager;
    }

    public static Main_Aer getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_aer,container, false);


        init_BtnEvent(view);


        //setBeginnerBtn(view);

        return view;
    }


    private void init_BtnEvent(View view) {
        courseBtn_1 = (Button) view.findViewById(R.id.main_aer_btn_1); //beginner
        courseBtn_2 = (Button) view.findViewById(R.id.main_aer_btn_2); //intermediate
        courseBtn_3 = (Button) view.findViewById(R.id.main_aer_btn_3); //expert


        courseBtn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Beginner Start", Toast.LENGTH_SHORT).show();
                Course_Aer_Beginner.getInstance().setContext(context);
                manager.beginTransaction().replace(R.id.content_main, Course_Aer_Beginner.getInstance()).commit();
            }
        });

        courseBtn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "intermediate Start", Toast.LENGTH_SHORT).show();
            }
        });

        courseBtn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Expert Start", Toast.LENGTH_SHORT).show();
            }
        });

    }
/*
    private void setBeginnerBtn(View view) {
        beginnerBtn = (ImageView)view.findViewById(R.id.beginner_aer);
        Glide.with(view).load(R.drawable.tmp_aer_course_beginner).into(beginnerBtn);
        beginnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Start Beginner Course", Toast.LENGTH_SHORT).show();
            }
        });
    }
*/

    private static class LazyHolder {
        public static final Main_Aer INSTANCE = new Main_Aer();
    }

}
