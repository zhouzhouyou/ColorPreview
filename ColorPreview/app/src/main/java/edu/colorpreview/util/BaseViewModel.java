package edu.colorpreview.util;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import edu.colorpreview.network.ApiInterface;

public class BaseViewModel extends AndroidViewModel {
    protected final String TAG = getClass().getSimpleName();
    protected final ApiInterface mApiInterface = NetTool.getApi();

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }
}
