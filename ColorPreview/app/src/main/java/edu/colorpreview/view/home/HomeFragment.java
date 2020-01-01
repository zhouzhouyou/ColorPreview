package edu.colorpreview.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import edu.colorpreview.R;
import edu.colorpreview.util.fragment.ViewModelFragment;

public class HomeFragment extends ViewModelFragment<HomeViewModel> {
    @Override
    protected void init() {

    }

    @Override
    protected int getLayout() {
        return R.layout.design_recycler_view;
    }
}
