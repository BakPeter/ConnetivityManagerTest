package com.bpapps.connectivitymanagertest;

public class Repository {
    private static Repository sInstance = null;

    private String mDataSet1 = "data set 1";
    private String mDataSet2 = "data set 2";
    private String mDataSetDefault = "data set default";

    public static Repository getInstance() {
        if (sInstance == null) {
            sInstance = new Repository();
        }

        return sInstance;
    }

    private Repository() {
    }

    public String getDataSet(int flag) {
        switch (flag) {
            case 1:
                return mDataSet1;
            case 2:
                return mDataSet2;
            default:
                return mDataSetDefault;
        }
    }
}
