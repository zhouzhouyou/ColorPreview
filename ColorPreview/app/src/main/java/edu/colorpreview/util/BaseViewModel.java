package edu.colorpreview.util;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class BaseViewModel extends AndroidViewModel {
    protected final String TAG = getClass().getSimpleName();

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }
}
