package edu.colorpreview.util;

import android.view.LayoutInflater;

import java.util.List;

import edu.colorpreview.databinding.DesignItemBinding;
import edu.colorpreview.model.Design;
import edu.colorpreview.util.recycler.DataBindingRecyclerAdapter;
import edu.colorpreview.view.MyHandler;

public class DesignAdapter extends DataBindingRecyclerAdapter<DesignItemBinding, Design> {
    public DesignAdapter(LayoutInflater layoutInflater, List<Design> dataList, int layoutId, int brId) {
        super(layoutInflater, dataList, layoutId, brId);
    }

    @Override
    protected void initViewHolder(DesignItemBinding designItemBinding) {
        designItemBinding.setHandler(new MyHandler());
    }
}
