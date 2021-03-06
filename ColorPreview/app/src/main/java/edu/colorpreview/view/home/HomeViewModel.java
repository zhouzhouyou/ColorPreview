package edu.colorpreview.view.home;

import android.app.Application;
import android.util.Log;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import edu.colorpreview.model.Design;
import edu.colorpreview.util.BaseViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends BaseViewModel {
    private final MutableLiveData<List<Design>> design = new MutableLiveData<>();

    public MutableLiveData<List<Design>> getDesign() {
        return design;
    }

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void refresh() {
        mApiInterface.getAllDesigns().enqueue(new Callback<List<Design>>() {
            @Override
            public void onResponse(Call<List<Design>> call, Response<List<Design>> response) {
                Log.d(TAG, "onResponse: " + response);
                if (response.body() != null) design.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Design>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }
}
