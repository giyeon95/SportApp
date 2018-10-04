package com.example.giyeo.testbar;

import android.net.Uri;

import java.util.ArrayList;

public class MainUriSource {

    private ArrayList<Uri> uriList = new ArrayList<>();

    private MainUriSource () {

    }

    public int size() {
        return uriList.size();
    }

    public ArrayList<Uri> getUriList() {
        return uriList;
    }

    public void aerBeginnerUri() {
         uriList.clear();
         uriList.add(Uri.parse("http://35.187.218.253/testmp4.mp4"));
         uriList.add(Uri.parse("http://35.187.218.253/Tridiary.mp4"));
    }

    public void aerIntermediateUri() {
        uriList.clear();
        uriList.add(Uri.parse("http://35.187.218.253/testmp4.mp4"));
        uriList.add(Uri.parse("http://35.187.218.253/Tridiary.mp4"));
    }

    public void aerExpertUri() {
        uriList.clear();
        uriList.add(Uri.parse("http://35.187.218.253/testmp4.mp4"));
        uriList.add(Uri.parse("http://35.187.218.253/Tridiary.mp4"));
    }

    public void pilaBeginnerUri() {
        uriList.clear();
        uriList.add(Uri.parse("http://35.187.218.253/testmp4.mp4"));
        uriList.add(Uri.parse("http://35.187.218.253/Tridiary.mp4"));
    }

    public void pilaIntermediateUri() {
        uriList.clear();
        uriList.add(Uri.parse("http://35.187.218.253/testmp4.mp4"));
        uriList.add(Uri.parse("http://35.187.218.253/Tridiary.mp4"));
    }

    public void pilaExpertUri() {
        uriList.clear();
        uriList.add(Uri.parse("http://35.187.218.253/testmp4.mp4"));
        uriList.add(Uri.parse("http://35.187.218.253/Tridiary.mp4"));
    }






    public static MainUriSource getInstance() { return LazyHolder.INSTANCE; }

    private static class LazyHolder {
        public static final MainUriSource INSTANCE = new MainUriSource();
    }
}
