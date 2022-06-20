package com.jlp.mvvm_jlp_project.view.auth;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.jlp.mvvm_jlp_project.R;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AuthActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    }
}

//public class LoginActivity implements BaseActivity {
//
//    private @NonNull ActivityLoginBinding binding;
//    protected final String TAG = getClass().getSimpleName();
//    private ProgressDialog mProgressDialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityLoginBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        presenter.onAttach();
//
//        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                presenter.login(binding.layoutUsername.inputUsername.getText().toString(), binding.layoutPassword.inputPassword.getText().toString());
//            }
//        });
//    }
//
//
//    @NonNull
//    @Override
//    protected LoginPresenter createPresenter() {
//        String data = "Data";
//        return new LoginPresenter(this, data);
//    }
//
//    @Override
//    public void showErrorMessage(int messageId) {
//        showToast(getResources().getString(messageId));
//    }
//
//    @Override
//    public void clearViews() {
//        binding.layoutUsername.inputUsername.setText("");
//        binding.layoutPassword.inputPassword.setText("");
//    }
//
//    @Override
//    public void showProgress() {
//        mProgressDialog = Utils.showLoadingDialog(this);
//    }
//
//    @Override
//    public void hideProgress() {
//        if (mProgressDialog != null && mProgressDialog.isShowing()) {
//            mProgressDialog.dismiss();
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        presenter.onDetach();
//        super.onDestroy();
//    }
//
//    @Override
//    public void navigateToHome() {
//        Intent intent = new Intent(this, ChangePasswordActivity.class);
//        startActivity(intent);
//    }

//    private void showSnackBar(String message) {
//        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
//                message, Snackbar.LENGTH_SHORT);
//        View sbView = snackbar.getView();
//        TextView textView = (TextView) sbView
//                .findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
//        snackbar.show();
//    }