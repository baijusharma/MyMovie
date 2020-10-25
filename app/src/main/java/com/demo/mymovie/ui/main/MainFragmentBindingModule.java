package com.demo.mymovie.ui.main;

import com.demo.mymovie.ui.ListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract ListFragment provideListFragment();

}