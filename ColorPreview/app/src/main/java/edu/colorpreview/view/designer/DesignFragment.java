package edu.colorpreview.view.designer;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import edu.colorpreview.R;
import edu.colorpreview.databinding.DesignFragmentBinding;
import edu.colorpreview.util.fragment.DataBindingFragment;

public class DesignFragment extends DataBindingFragment<DesignFragmentBinding> {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private DesignerViewModel mDesignerViewModel;
    private Temp1Fragment mTemp1Fragment;
    private Temp2Fragment mTemp2Fragment;
    private Temp3Fragment mTemp3Fragment;

    @Override
    protected int getLayout() {
        return R.layout.design_fragment;
    }

    @Override
    protected void init(DesignFragmentBinding designFragmentBinding) {
        mDesignerViewModel = ViewModelProviders.of(requireActivity()).get(DesignerViewModel.class);
        mTabLayout = designFragmentBinding.tabLayout;
        mViewPager = designFragmentBinding.viewPager;
        PagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager());
        mTemp1Fragment = new Temp1Fragment();
        mTemp2Fragment = new Temp2Fragment();
        mTemp3Fragment = new Temp3Fragment();
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        designFragmentBinding.setDesign(mDesignerViewModel.mDesign.getValue());
        mDesignerViewModel.mDesign.observe(getViewLifecycleOwner(), designFragmentBinding::setDesign);
        designFragmentBinding.setHandler(new Handler());
    }

    public class Handler {
        public void changeP() {

        }

        public void changeS() {

        }

        public void changeTP() {

        }

        public void changeTS() {

        }
    }

    public class PagerAdapter extends FragmentPagerAdapter {
        String[] tabNames = getResources().getStringArray(R.array.pager);

        public PagerAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames[position];
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return mTemp1Fragment;
                case 1:
                    return mTemp2Fragment;
                case 2:
                    return mTemp3Fragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
