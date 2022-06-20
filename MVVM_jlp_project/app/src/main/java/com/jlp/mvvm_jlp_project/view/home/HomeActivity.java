package com.jlp.mvvm_jlp_project.view.home;

import androidx.annotation.NonNull;

import android.os.Bundle;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.databinding.ActivityHomeBinding;
import com.jlp.mvvm_jlp_project.view.base.BaseActivity;

import dagger.hilt.android.AndroidEntryPoint;

//@AndroidEntryPoint
public class HomeActivity extends BaseActivity {

    private @NonNull
    ActivityHomeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initViewBinding() {
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}