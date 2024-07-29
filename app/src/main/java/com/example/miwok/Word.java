package com.example.miwok;

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceid=NO_IMAGE_PROVIDED;

    private int mAudioResourceID;

    private static final int NO_IMAGE_PROVIDED=-1;

    public Word(String defaultTranslation, String miwokTranslation,int audioResource) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceID=audioResource;

    }

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId,int audioResource) {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceid=imageResourceId;
        mAudioResourceID=audioResource;

    }



    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getmImageResourceid()
    {
        return mImageResourceid;
    }

    public boolean hasImage()
    {
        return mImageResourceid != NO_IMAGE_PROVIDED;
    }

    public int getAudioResource()
    {
        return getAudioResource();
    }



}
