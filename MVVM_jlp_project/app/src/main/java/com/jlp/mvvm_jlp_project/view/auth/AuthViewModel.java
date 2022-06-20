package com.jlp.mvvm_jlp_project.view.auth;/*
 * Created by Sandeep(Techno Learning) on 18,June,2022
 */

import android.content.Context;
import android.content.res.loader.ResourcesProvider;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.jlp.mvvm_jlp_project.R;
import com.jlp.mvvm_jlp_project.constants.Constants;
import com.jlp.mvvm_jlp_project.constants.Utils;
import com.jlp.mvvm_jlp_project.model.ChangePasswordRequest;
import com.jlp.mvvm_jlp_project.model.LoginUserRequest;
import com.jlp.mvvm_jlp_project.view.base.BaseViewModel;

import javax.inject.Inject;

import dagger.Provides;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ActivityContext;

@HiltViewModel
public class AuthViewModel extends BaseViewModel {

    public MutableLiveData<Pair<Boolean, Integer>> validationResult = new MutableLiveData<>();
    private MutableLiveData<LoginUserRequest> userMutableLiveData;

    @Inject
    AuthViewModel(){
    }

    public MutableLiveData<LoginUserRequest> login() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }

    public void onClick(View view) {
        //LoginUserRequest loginUser = new LoginUserRequest(EmailAddress.getValue(), Password.getValue());
        //userMutableLiveData.setValue(loginUser);
    }

    public void validateLogin(String username, String password) {
        Pair<Boolean, Integer> result = null;
        if(TextUtils.isEmpty(username) && TextUtils.isEmpty(password)){
            result = new Pair(false, R.string.please_enter_user_id_and_password);
        }if(TextUtils.isEmpty(username)){
            result = new Pair(false, R.string.please_enter_user_id);
        }else if(TextUtils.isEmpty(password)){
            result = new Pair(false, R.string.please_enter_password);
        }else if(password.length() < Constants.MIN_PASSWORD_LENGTH || password.length() > Constants.MAX_PASSWORD_LENGTH){
            result = new Pair(false, R.string.password_should_be);
        }else{
            result = new Pair <Boolean, Integer> (true, 0);
        }
        validationResult.setValue(result);
    }

    // TODO: Old password match remaining
    public void validateChangePassword(String username, String oldPassword,
                                       String newPassword, String confirmPassword) {
        Pair<Boolean, Integer> result = null;
        if(TextUtils.isEmpty(oldPassword)){
            result = new Pair(false, R.string.please_enter_old_password);
        }if(TextUtils.isEmpty(newPassword) && TextUtils.isEmpty(confirmPassword)){
            result = new Pair(false, R.string.please_enter_new_password_and_confirm);
        }else if(newPassword.length() < Constants.MIN_PASSWORD_LENGTH || newPassword.length() > Constants.MAX_PASSWORD_LENGTH){
            result = new Pair(false, R.string.new_password_should_be);
        }else if(Utils.isValidPassword(newPassword)){
            result = new Pair(false, R.string.at_least_one_alpha_one_num_req);
        }else if(!newPassword.equals(confirmPassword)){
            result = new Pair(false, R.string.new_password_and_confirm_password_mismatch);
        }else{
            result = new Pair <Boolean, Integer> (true, 0);
        }
        validationResult.setValue(result);
    }

    void loginUser(LoginUserRequest loginUserRequest){
        //userRepository.loginUser(loginUserRequest)
    }

    void changePasswordUser(ChangePasswordRequest changePasswordRequest){
        //userRepository.changePassword(changePasswordRequest)
    }

}
