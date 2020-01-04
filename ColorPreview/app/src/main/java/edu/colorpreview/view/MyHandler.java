package edu.colorpreview.view;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.lifecycle.MutableLiveData;
import edu.colorpreview.R;
import edu.colorpreview.model.Design;
import edu.colorpreview.util.BaseViewModel;
import edu.colorpreview.util.XmlBuilder;
import edu.colorpreview.view.designer.DesignerActivity;
import edu.colorpreview.view.user.UserStatus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyHandler extends BaseViewModel {
    public MutableLiveData<Set<Integer>> bookmarks = new MutableLiveData<>(new HashSet<>());

    public boolean isBookMarked(int did) {
        Set<Integer> set = bookmarks.getValue();
        if (set == null) return false;
        return set.contains(did);
    }

    public MyHandler(@NonNull Application application) {
        super(application);
    }

    public void navToDesignActivity(View view, Design design) {
        Intent intent = new Intent(view.getContext(), DesignerActivity.class);
        intent.putExtra(DesignerActivity.ARG, design.getBundle());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(intent);
    }

    public void shareClicked(View view, Design design) {
        Context context = view.getContext();
        File file = XmlBuilder.generateXML(context, design);
        Intent intent = new Intent(Intent.ACTION_SEND);
        Uri uri = FileProvider.getUriForFile(context, "edu.colorpreview.fileprovider", file);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("text/plain");
        context.startActivity(intent);
    }

    public void bookmarkClicked(View view, Design design) {
        int did = design.id;
        Set<Integer> set = bookmarks.getValue();
        Context context = view.getContext();
        if (set == null) {
            Toast.makeText(context, context.getText(R.string.NULL), Toast.LENGTH_SHORT).show();
            return;
        }
        if (isBookMarked(did)) {
            mApiInterface.deleteBookmark(did, UserStatus.sUser.id).enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful()) {
                        set.remove(did);
                        bookmarks.postValue(set);
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            mApiInterface.createBookMark(did, UserStatus.sUser.id).enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful()) {
                        set.add(did);
                        bookmarks.postValue(set);
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void refreshBookmarks() {
        mApiInterface.getAllUserBookmark(UserStatus.sUser.id).enqueue(new Callback<List<Design>>() {
            @Override
            public void onResponse(Call<List<Design>> call, Response<List<Design>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Set<Integer> set = new HashSet<>();
                        for (Design design : response.body()) {
                            set.add(design.id);
                        }
                        bookmarks.postValue(set);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Design>> call, Throwable t) {
            }
        });
    }
}
