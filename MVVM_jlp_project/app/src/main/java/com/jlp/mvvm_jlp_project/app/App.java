package com.jlp.mvvm_jlp_project.app;/*
 * Created by Sandeep(Techno Learning) on 10,June,2022
 */

import android.app.Application;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class App extends Application {

    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        init(this);
    }

    private static void init(App app) {
        sInstance = app;
    }

    public static App getInstance() {
        return sInstance;
    }

}
