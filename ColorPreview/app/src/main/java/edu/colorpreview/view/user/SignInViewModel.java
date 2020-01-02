package edu.colorpreview.view.user;

import android.app.Application;
import android.content.Intent;
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

public class SignInViewModel extends BaseViewModel {
    public String username = "";
    public String password = "";

    private MutableLiveData<String> wrong = new MutableLiveData<>();
    private MutableLiveData<Boolean> login = new MutableLiveData<>();

    public MutableLiveData<String> getWrong() {
        return wrong;
    }

    public MutableLiveData<Boolean> getLogin() {
        return login;
    }

    public SignInViewModel(@NonNull Application application) {
        super(application);
    }

    public void login() {
        if (username.length() == 0) {
            wrong.postValue("请输入用户名");
            return;
        }
        if (password.length() == 0) {
            wrong.postValue("请输入密码");
            return;
        }
        mApiInterface.signIn(username, password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    UserStatus.sUser = response.body();
                    login.postValue(true);
                } else {
                    wrong.postValue("账户或密码错误");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                wrong.postValue(t.toString());
            }
        });
    }

    public void navToSignUp(View view) {
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.signUp);
    }
}
