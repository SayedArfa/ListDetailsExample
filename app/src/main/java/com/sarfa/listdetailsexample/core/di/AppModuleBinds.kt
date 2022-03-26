package com.sarfa.listdetailsexample.core.di

import com.sarfa.domain.repo.items.ItemRepo
import com.sarfa.listdetailsexample.data.repo.ItemRepoImp
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModuleBinds {
    @Singleton
    @Binds
    abstract fun bindItemRepo(impl: ItemRepoImp): ItemRepo
}