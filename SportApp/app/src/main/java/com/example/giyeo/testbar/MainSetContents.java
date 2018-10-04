package com.example.giyeo.testbar;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class MainSetContents {

    private Context context;
    private View view;
    private TextView titleText;
    private TextView contentsText;
    private TextView progressText;
    private TextView benefitText;

    private MainSetContents() {}

    public void setContext(Context context, View view){
        this.context = context;
        this.view = view;

        titleText = (TextView)view.findViewById(R.id.title);
        contentsText = (TextView)view.findViewById(R.id.contents);
        progressText = (TextView)view.findViewById(R.id.progress);
        benefitText = (TextView)view.findViewById(R.id.benefit);
    }

    public void aerBeginnerContents(int pageNum) {

        switch (pageNum) {
            case 0 : {
                titleText.setText(Course_Data_Connect.getInstance().courseAerBeginner1.getTitleText());
                contentsText.setText(Course_Data_Connect.getInstance().courseAerBeginner1.getContentsText());
                progressText.setText(Course_Data_Connect.getInstance().courseAerBeginner1.getProgressText());
                benefitText.setText(Course_Data_Connect.getInstance().courseAerBeginner1.getBenefitText());
                break;
            }
            case 1 : {
                titleText.setText(Course_Data_Connect.getInstance().courseAerBeginner2.getTitleText());
                contentsText.setText(Course_Data_Connect.getInstance().courseAerBeginner2.getContentsText());
                progressText.setText(Course_Data_Connect.getInstance().courseAerBeginner2.getProgressText());
                benefitText.setText(Course_Data_Connect.getInstance().courseAerBeginner2.getBenefitText());
                break;
            }
            case 2 : {
                break;
            }
        }
    }

    public void aerIntermediateContents(int pageNum) {

    }

    public void aerExpertContents(int pageNum) {

    }

    public void pilaBeginnerContents(int pageNum) {

    }

    public void pilaIntermediateContents(int pageNum) {

    }

    public void pilaExpertContents(int pageNum) {

    }

    public static MainSetContents getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        public static final MainSetContents INSTANCE = new MainSetContents();
    }
}

