package com.demo.mymovie.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.demo.mymovie.R;
import com.demo.mymovie.base.BaseActivity;
import com.demo.mymovie.ui.ListFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.screenContainer, new ListFragment()).commit();
    }
}