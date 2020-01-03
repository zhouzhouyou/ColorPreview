package edu.colorpreview.view.designer;

import androidx.lifecycle.ViewModelProviders;
import edu.colorpreview.R;
import edu.colorpreview.databinding.Template2Binding;
import edu.colorpreview.util.fragment.DataBindingFragment;

public class Temp2Fragment extends DataBindingFragment<Template2Binding> {
    @Override
    protected int getLayout() {
        return R.layout.template_2;
    }

    @Override
    protected void init(Template2Binding template2Binding) {
        DesignerViewModel viewModel = ViewModelProviders.of(requireActivity()).get(DesignerViewModel.class);
        template2Binding.setDesign(viewModel.mDesign.getValue());
        viewModel.mDesign.observe(getViewLifecycleOwner(), template2Binding::setDesign);
    }
}
