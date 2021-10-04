package com.mehdik.mvvmdi.di

import com.mehdik.mvvmdi.models.Dish
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelsModule {

    @ViewModelScoped
    @Provides
    fun provideDishList() = mutableListOf<Dish>()
}