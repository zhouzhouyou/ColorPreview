package edu.colorpreview.view.user;

import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;
import edu.colorpreview.R;
import edu.colorpreview.databinding.SignInBinding;
import edu.colorpreview.util.fragment.DataBindingFragment;
import edu.colorpreview.view.MainActivity;

public class SignInFragment extends DataBindingFragment<SignInBinding> {
    @Override
    protected int getLayout() {
        return R.layout.sign_in;
    }


    @Override
    protected void init(SignInBinding signInBinding) {
        SignInViewModel viewModel = ViewModelProviders.of(this).get(SignInViewModel.class);
        signInBinding.setLogin(viewModel);
        viewModel.getWrong().observe(getViewLifecycleOwner(), s -> Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show());
        viewModel.getLogin().observe(getViewLifecycleOwner(), login -> {
            if (login) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
