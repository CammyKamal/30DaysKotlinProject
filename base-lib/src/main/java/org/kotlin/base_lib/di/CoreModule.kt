package org.kotlin.base_lib.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import org.kotlin.base_lib.ViewModelFactory


@Module
abstract class CoreModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}