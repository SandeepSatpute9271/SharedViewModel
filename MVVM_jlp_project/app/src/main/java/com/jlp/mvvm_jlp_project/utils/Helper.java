package com.jlp.mvvm_jlp_project.utils;/*
 * Created by Sandeep(Techno Learning) on 20,June,2022
 */

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.jlp.mvvm_jlp_project.view.home.HomeActivity;

public class Helper {

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void redirectToActivity(Activity activity, Class<?> redirectTo, boolean isFinishCurrentAct) {
        Intent intent = new Intent(activity, redirectTo);
        activity.startActivity(intent);
        if(isFinishCurrentAct){
            activity.finish();
        }
    }

}
