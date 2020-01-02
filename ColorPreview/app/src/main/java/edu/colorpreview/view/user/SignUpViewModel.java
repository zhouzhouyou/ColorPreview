package edu.colorpreview.view.user;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import edu.colorpreview.R;
import edu.colorpreview.model.User;
import edu.colorpreview.util.BaseViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends BaseViewModel {
    public String username = "";
    public String password = "";

    private MutableLiveData<String> wrong = new MutableLiveData<>();

    public MutableLiveData<String> getWrong() {
        return wrong;
    }

    public SignUpViewModel(@NonNull Application application) {
        super(application);
    }

    public void signUp(View view) {
        if (username.length() == 0) {
            wrong.postValue("请输入用户名");
            return;
        }
        if (password.length() == 0) {
            wrong.postValue("请输入密码");
            return;
        }
        mApiInterface.signUp(username, password).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    UserStatus.sUser = new User(response.body(), username, password, 0.0);
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.account);
                } else {
                    wrong.postValue("注册失败，可能有相同的用户名");
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                wrong.postValue(t.toString());
            }
        });
    }

    public void navToSignIn(View view) {
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.signIn);
    }
}
