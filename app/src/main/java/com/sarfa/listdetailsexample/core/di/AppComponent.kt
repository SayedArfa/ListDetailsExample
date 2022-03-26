package com.sarfa.listdetailsexample.core.di

import android.app.Application
import com.sarfa.listdetailsexample.core.app.MyApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, NetworkModule::class, AppModuleBinds::class, ActivityBuilder::class, FragmentBuilder::class])
interface AppComponent {

    fun inject(app: MyApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}