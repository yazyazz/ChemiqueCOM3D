package com.chemique3d.app;

public class ExampleItem {

    private int mImageResource;
    private String mText1;
    private String mText2;
    private String mA1;
    private String mA2;
    private String mA3;
    private String mA4;

    public ExampleItem(int imageResource, String text1, String text2, String a1, String a2, String a3, String a4) {
        mImageResource = imageResource;
        mText1 = text1;
        mText2 = text2;
        mA1 = a1;
        mA2 = a2;
        mA3 = a3;
        mA4 = a4;
    }

    public int getImageResource() {
        return mImageResource;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }

    public String getA2() {
        return mA2;
    }

    public String getA3() {
        return mA3;
    }

    public String getA4() {
        return mA4;
    }

    public String getA1() {
        return mA1;
    }

}
