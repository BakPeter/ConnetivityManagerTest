package com.bpapps.connectivitymanagertest;

import androidx.lifecycle.ViewModel;

public class MainViewViewModel extends ViewModel {
    private Repository mRepository = Repository.getInstance();

    public String getDataSet(int flag) {
        return mRepository.getDataSet(flag);
    }
}