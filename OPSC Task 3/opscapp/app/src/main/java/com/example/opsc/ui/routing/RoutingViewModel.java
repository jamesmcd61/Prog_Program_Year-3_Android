package com.example.opsc.ui.routing;

        import androidx.lifecycle.LiveData;
        import androidx.lifecycle.MutableLiveData;
        import androidx.lifecycle.ViewModel;

public class RoutingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RoutingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is rout fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}