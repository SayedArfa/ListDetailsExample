package com.sarfa.listdetailsexample.core.di


import com.sarfa.listdetailsexample.ui.details.ItemDetailsFragment
import com.sarfa.listdetailsexample.ui.list.ItemListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector(modules = [])
    abstract fun bindItemListFragment(): ItemListFragment
    @ContributesAndroidInjector(modules = [])
    abstract fun bindDetailsFragment(): ItemDetailsFragment
}