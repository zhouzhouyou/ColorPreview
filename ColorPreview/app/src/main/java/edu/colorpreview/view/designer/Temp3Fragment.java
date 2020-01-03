package edu.colorpreview.view.designer;

import androidx.lifecycle.ViewModelProviders;
import edu.colorpreview.R;
import edu.colorpreview.databinding.Template1Binding;
import edu.colorpreview.util.fragment.DataBindingFragment;

public class Temp3Fragment extends DataBindingFragment<Template1Binding> {
    @Override
    protected int getLayout() {
        return R.layout.template_1;
    }

    @Override
    protected void init(Template1Binding template1Binding) {
        DesignerViewModel viewModel = ViewModelProviders.of(requireActivity()).get(DesignerViewModel.class);
        template1Binding.setDesign(viewModel.mDesign.getValue());
        viewModel.mDesign.observe(getViewLifecycleOwner(), template1Binding::setDesign);
    }
}
