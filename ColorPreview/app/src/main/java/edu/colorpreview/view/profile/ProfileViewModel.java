package edu.colorpreview.view.profile;

import android.app.Application;
import android.content.Context;
import android.view.View;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import edu.colorpreview.R;
import edu.colorpreview.model.Design;
import edu.colorpreview.util.BaseViewModel;
import edu.colorpreview.view.user.UserStatus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends BaseViewModel {
    public final MutableLiveData<List<Design>> mDesigns = new MutableLiveData<>();
    public final MutableLiveData<String> message = new MutableLiveData<>();
    public final MyDesignHandler mMyDesignHandler = new MyDesignHandler();


    public ProfileViewModel(@NonNull Application application) {
        super(application);
    }

    public void refresh() {
        mApiInterface.getUserDesigns(UserStatus.sUser.id).enqueue(new Callback<List<Design>>() {
            @Override
            public void onResponse(Call<List<Design>> call, Response<List<Design>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mDesigns.postValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Design>> call, Throwable t) {
                message.postValue(t.toString());
            }
        });
    }

    public class MyDesignHandler {
        public void delete(View view, Design design) {
            Context context = view.getContext();
            mApiInterface.deleteDesign(design.id).enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    message.postValue(response.isSuccessful() ?
                            context.getText(R.string.success_delete).toString() :
                            context.getText(R.string.failed).toString()
                            );
                    refresh();
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    message.postValue(t.toString());
                }
            });
        }
    }


}
