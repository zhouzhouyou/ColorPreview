package edu.colorpreview.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import edu.colorpreview.R;
import edu.colorpreview.databinding.DesignItemBinding;
import edu.colorpreview.model.Design;
import edu.colorpreview.util.fragment.ViewModelFragment;
import edu.colorpreview.util.recycler.DataBindingRecyclerAdapter;
import edu.colorpreview.view.MyHandler;
import edu.colorpreview.view.user.UserStatus;

public class HomeFragment extends ViewModelFragment<HomeViewModel> {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private DesignAdapter mDesignAdapter;
    private MyHandler mMyHandler;

    @Override
    protected void init(View view) {
        initVM(HomeViewModel.class);
        mMyHandler = ViewModelProviders.of(requireActivity()).get(MyHandler.class);

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
            if (UserStatus.sUser != null) {
                mMyHandler.refreshBookmarks();
            }
        });


        mSwipeRefreshLayout.setOnRefreshListener(() -> mVM.refresh());

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mVM.refresh();
    }

    @Override
    protected int getLayout() {
        return R.layout.design_recycler_view;
    }

    public class DesignAdapter extends DataBindingRecyclerAdapter<DesignItemBinding, Design> {
        public DesignAdapter(LayoutInflater layoutInflater, List<Design> dataList, int layoutId, int brId) {
            super(layoutInflater, dataList, layoutId, brId);
        }

        @Override
        protected void initViewHolder(DesignItemBinding designItemBinding) {
            designItemBinding.setHandler(mMyHandler);
            mMyHandler.bookmarks.observe(getViewLifecycleOwner(), integers -> {
                Design design = designItemBinding.getDesign();
                if (design != null) {
                    designItemBinding.bookmark.setImageResource(
                            mMyHandler.isBookMarked(design.id) ?
                                    R.drawable.ic_bookmark_red_500_24dp :
                                    R.drawable.ic_bookmark_grey_600_24dp);
                }
            });
        }
    }
}
