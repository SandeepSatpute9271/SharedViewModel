package com.jlp.mvvm_jlp_project.view.base;/*
 * Created by Sandeep(Techno Learning) on 17,June,2022
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void initViewBinding();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewBinding();
    }
}
