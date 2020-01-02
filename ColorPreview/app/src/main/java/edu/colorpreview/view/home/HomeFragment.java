package edu.colorpreview.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import edu.colorpreview.BR;
import edu.colorpreview.R;
import edu.colorpreview.util.DesignAdapter;
import edu.colorpreview.util.fragment.ViewModelFragment;

public class HomeFragment extends ViewModelFragment<HomeViewModel> {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private DesignAdapter mDesignAdapter;

    @Override
    protected void init(View view) {
        initVM(HomeViewModel.class);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe);
        mRecyclerView = view.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        mDesignAdapter = new DesignAdapter(
                Objects.requireNonNull(getActivity()).getLayoutInflater(),
                null,
                R.layout.design_item,
                edu.colorpreview.BR.design
                );

        mRecyclerView.setAdapter(mDesignAdapter);
        mVM.getDesign().observe(getViewLifecycleOwner(), designs -> {
            mDesignAdapter.setDataList(designs);
            mSwipeRefreshLayout.setRefreshing(false);
        });
        mVM.refresh();

        mSwipeRefreshLayout.setOnRefreshListener(() -> mVM.refresh());
    }

    @Override
    protected int getLayout() {
        return R.layout.design_recycler_view;
    }
}
