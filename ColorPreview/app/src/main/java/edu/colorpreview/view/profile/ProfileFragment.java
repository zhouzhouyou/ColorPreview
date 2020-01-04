package edu.colorpreview.view.profile;

import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import edu.colorpreview.R;
import edu.colorpreview.databinding.MyDesignItemBinding;
import edu.colorpreview.databinding.ProfileFragmentBinding;
import edu.colorpreview.model.Design;
import edu.colorpreview.util.fragment.DataBindingFragment;
import edu.colorpreview.util.recycler.DataBindingRecyclerAdapter;
import edu.colorpreview.view.MyHandler;
import edu.colorpreview.view.user.UserStatus;

public class ProfileFragment extends DataBindingFragment<ProfileFragmentBinding> {
    private ProfileViewModel viewModel;
    private MyHandler mMyHandler;


    @Override
    protected int getLayout() {
        return R.layout.profile_fragment;
    }

    @Override
    protected void init(ProfileFragmentBinding profileFragmentBinding) {
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        mMyHandler = ViewModelProviders.of(requireActivity()).get(MyHandler.class);
        DesignAdapter adapter = new DesignAdapter(
                Objects.requireNonNull(getActivity()).getLayoutInflater(),
                null,
                R.layout.my_design_item,
                edu.colorpreview.BR.design
        );
        RecyclerView recyclerView = profileFragmentBinding.recycler;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.message.observe(getViewLifecycleOwner(), s -> Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show());
        profileFragmentBinding.setName(UserStatus.sUser.name);
        viewModel.refresh();
        SwipeRefreshLayout swipeRefreshLayout = profileFragmentBinding.swipe;
        swipeRefreshLayout.setOnRefreshListener(viewModel::refresh);
        viewModel.mDesigns.observe(getViewLifecycleOwner(), designs ->  {
            adapter.setDataList(designs);
            swipeRefreshLayout.setRefreshing(false);
            mMyHandler.refreshBookmarks();
        });
    }

    public class DesignAdapter extends DataBindingRecyclerAdapter<MyDesignItemBinding, Design> {
        public DesignAdapter(LayoutInflater layoutInflater, List<Design> dataList, int layoutId, int brId) {
            super(layoutInflater, dataList, layoutId, brId);
        }

        @Override
        protected void initViewHolder(MyDesignItemBinding designItemBinding) {
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
            designItemBinding.setMy(viewModel.mMyDesignHandler);
        }
    }
}
