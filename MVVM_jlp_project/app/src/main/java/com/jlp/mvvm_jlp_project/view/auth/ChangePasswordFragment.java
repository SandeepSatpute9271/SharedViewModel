package com.jlp.mvvm_jlp_project.view.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewbinding.ViewBinding;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.databinding.FragmentChangePasswordBinding;
import com.jlp.mvvm_jlp_project.databinding.FragmentLoginBinding;
import com.jlp.mvvm_jlp_project.model.ChangePasswordRequest;
import com.jlp.mvvm_jlp_project.model.LoginUserRequest;
import com.jlp.mvvm_jlp_project.utils.Helper;
import com.jlp.mvvm_jlp_project.utils.Utils;
import com.jlp.mvvm_jlp_project.view.base.BaseFragment;
import com.jlp.mvvm_jlp_project.view.home.HomeActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ChangePasswordFragment extends BaseFragment {

    private @NonNull
    FragmentChangePasswordBinding binding;
    private AuthViewModel authViewModel;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }

    @Override
    protected View initViewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChangePasswordBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        initObserver(view);
        initListener();
    }


    private void initListener() {
        binding.btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authViewModel.validateChangePassword(
                        binding.inputUsername.getText().toString().trim(),
                        binding.inputOldPassword.getText().toString().trim(),
                        binding.inputNewPassword.getText().toString().trim(),
                        binding.inputConfirmPassword.getText().toString().trim());
            }
        });
    }

    private void initObserver(View view) {
        authViewModel.validationResult.observe(getViewLifecycleOwner(), new Observer<Pair<Boolean, Integer>>() {
            @Override
            public void onChanged(Pair<Boolean, Integer> validationResult) {
                if(validationResult.first){
                    authViewModel.changePasswordUser(new ChangePasswordRequest(
                            binding.inputUsername.getText().toString().trim(),
                            binding.inputOldPassword.getText().toString().trim(),
                            binding.inputNewPassword.getText().toString().trim(),
                            binding.inputConfirmPassword.getText().toString().trim()));
//                    NavController navController = Navigation.findNavController(view);
//                    navController.navigate(R.id.action_loginFragment_to_changePasswordFragment);
                    Helper.redirectToActivity(getActivity(), HomeActivity.class, true);
                }else{
                    showErrors(validationResult.second);
                }
            }
        });
    }

    private void showErrors(Integer errorStrId){
        Utils.showErrorMessage(getActivity(), getResources().getString(errorStrId));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}