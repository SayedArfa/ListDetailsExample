package com.sarfa.listdetailsexample.core.di


import com.sarfa.listdetailsexample.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [])
    abstract fun bindMainActivity(): MainActivity
}