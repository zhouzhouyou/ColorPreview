package edu.colorpreview.view.bookmark;

import android.view.LayoutInflater;
import android.view.View;

import java.util.List;
import java.util.Objects;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import edu.colorpreview.R;
import edu.colorpreview.databinding.DesignItemBinding;
import edu.colorpreview.model.Design;
import edu.colorpreview.util.fragment.ViewModelFragment;
import edu.colorpreview.util.recycler.DataBindingRecyclerAdapter;
import edu.colorpreview.view.MyHandler;
import edu.colorpreview.view.user.UserStatus;

public class BookmarkFragment extends ViewModelFragment<BookmarkViewModel> {
    private MyHandler mMyHandler;

    @Override
    protected int getLayout() {
        return R.layout.design_recycler_view;
    }

    @Override
    protected void init(View view) {
        mVM = ViewModelProviders.of(requireActivity()).get(BookmarkViewModel.class);
        mMyHandler = ViewModelProviders.of(requireActivity()).get(MyHandler.class);
        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        DesignAdapter adapter = new DesignAdapter(Objects.requireNonNull(getActivity()).getLayoutInflater(),
                null,
                R.layout.design_item,
                edu.colorpreview.BR.design
        );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        SwipeRefreshLayout swipe = view.findViewById(R.id.swipe);
        swipe.setOnRefreshListener(() -> mVM.refresh(UserStatus.sUser.id));
        mVM.design.observe(getViewLifecycleOwner(), designs -> {
            adapter.setDataList(designs);
            swipe.setRefreshing(false);
            mMyHandler.refreshBookmarks();
        });
        mVM.refresh(UserStatus.sUser.id);
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
