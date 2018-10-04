package com.example.giyeo.testbar;



public class Course_Data_Repository {

    private String titleText;
    private String contentsText;
    private String progressText;
    private String benefitText;

    public Course_Data_Repository (String title, String contents, String progress,String benefit) {
       titleText = title;
       contentsText = contents;
       progressText = progress;
       benefitText = benefit;
    }
    public String getBenefitText() {
        return benefitText;
    }

    public String getTitleText() {
        return titleText;
    }

    public String getContentsText() {
        return contentsText;
    }

    public String getProgressText() {
        return progressText;
    }
}
