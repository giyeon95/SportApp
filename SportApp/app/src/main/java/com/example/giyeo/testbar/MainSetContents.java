package com.example.giyeo.testbar;

import android.widget.TextView;

public class MainSetContents {

    private TextView titleText;
    private TextView contentsText;
    private TextView progressText;
    private TextView benefitText;

    private MainSetContents() {}

    public void setContext(TextView titleText, TextView contentsText, TextView progressText, TextView benefitText){



        this.titleText = titleText;
        this.contentsText = contentsText;
        this.progressText = progressText;
        this.benefitText = benefitText;
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

