package com.example.opsc.ui.graphing;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class GraphingViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    public GraphingViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("This is graphing fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }





}