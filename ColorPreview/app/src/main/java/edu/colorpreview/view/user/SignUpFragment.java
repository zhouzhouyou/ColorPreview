package edu.colorpreview.view.user;

import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;
import edu.colorpreview.R;
import edu.colorpreview.databinding.SignUpBinding;
import edu.colorpreview.util.fragment.DataBindingFragment;
import edu.colorpreview.view.MainActivity;

public class SignUpFragment extends DataBindingFragment<SignUpBinding> {

    @Override
    protected int getLayout() {
        return R.layout.sign_up;
    }

    @Override
    protected void init(SignUpBinding signUpBinding) {
        SignUpViewModel viewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);
        signUpBinding.setSignUp(viewModel);
        viewModel.getWrong().observe(getViewLifecycleOwner(), s -> Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show());
        viewModel.signUp.observe(getViewLifecycleOwner(), log -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        });
    }
}
