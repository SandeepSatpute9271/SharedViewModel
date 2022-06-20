package com.jlp.mvvm_jlp_project.utils;/*
 * Created by Sandeep(Techno Learning) on 13,June,2022
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.android.material.snackbar.Snackbar;
import com.jlp.mvvm_jlp_project.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    /**
     * Create a progress bar which shows the progress
     * @param context
     * @return Progress Dialog to hide from where it is called
     */
    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog mDialog = new ProgressDialog(context);
        mDialog.setMessage("Loading...");
        mDialog.setCancelable(false);
        mDialog.show();
        return mDialog;
    }

    /**
     * Show the snackbar for error messages
     * @param activity On which activity we are showing this
     * @param message error message
     */
    public static void showErrorMessage(Activity activity, String message) {
        Snackbar snackbar = Snackbar.make(activity.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(activity.getResources().getColor(R.color.teal_200));
        snackbar.setTextColor(activity.getResources().getColor(R.color.red));
        snackbar.show();
    }


    /**
     * Validate the password with at least one alphabet and one number
     * @param password
     * @return true or false
     */
    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }

    /**
     * Checks if the Internet connection is available.
     *
     * @return Returns true if the Internet connection is available. False otherwise.
     **/
    public static boolean isInternetAvailable(Context ctx) {
        // using received context (typically activity) to get SystemService causes memory link as this holds strong reference to that activity.
        // use application level context instead, which is available until the app dies.
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // if network is NOT available networkInfo will be null
        // otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }

        return false;
    }

}
