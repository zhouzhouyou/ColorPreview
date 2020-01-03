package edu.colorpreview.view.designer;

import androidx.lifecycle.ViewModelProviders;
import edu.colorpreview.R;
import edu.colorpreview.databinding.Template3Binding;
import edu.colorpreview.util.fragment.DataBindingFragment;

public class Temp3Fragment extends DataBindingFragment<Template3Binding> {
    @Override
    protected int getLayout() {
        return R.layout.template_3;
    }

    @Override
    protected void init(Template3Binding Template3Binding) {
        DesignerViewModel viewModel = ViewModelProviders.of(requireActivity()).get(DesignerViewModel.class);
        Template3Binding.setDesign(viewModel.mDesign.getValue());
        viewModel.mDesign.observe(getViewLifecycleOwner(), Template3Binding::setDesign);
    }
}
