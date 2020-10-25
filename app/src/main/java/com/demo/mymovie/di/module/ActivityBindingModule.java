package com.demo.mymovie.di.module;

import com.demo.mymovie.ui.main.MainActivity;
import com.demo.mymovie.ui.main.MainFragmentBindingModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindMainActivity();
}