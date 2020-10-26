package com.demo.mymovie.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.demo.mymovie.R;
import com.demo.mymovie.base.BaseActivity;
import com.demo.mymovie.ui.main.MainActivity;

import butterknife.BindView;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.edt_email)
    EditText edt_email;
    @BindView(R.id.edt_password)
    EditText edt_password;
    @BindView(R.id.btn_login)
    Button btn_login;

    @Override
    protected int layoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        btn_login.setEnabled(false);
    }

    private void initView() {
        edt_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkRequiredFields();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edt_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkRequiredFields();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void checkRequiredFields() {
        if (validateEmail() && validatePassword()) {
            btn_login.setEnabled(true);
        } else {
            btn_login.setEnabled(false);
        }
    }

    /**
     * This fun will validated email
     */
    private boolean validateEmail() {
        String emailInput = edt_email.getText().toString().trim();

        if (emailInput.isEmpty()) {
            return false;
        } else if (Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This fun will validated password length
     */
    private boolean validatePassword() {
        String passwordInput = edt_password.getText().toString().trim();

        if (passwordInput.isEmpty() || passwordInput.length() < 6) {
            return false;
        } else if (passwordInput.length() > 6 && passwordInput.length() <= 12) {
            return true;
        } else {
            return false;
        }
    }
}
