package com.mehdik.mvvmdi.di

import android.content.Context
import com.mehdik.mvvmdi.BaseApplication
import com.mehdik.mvvmdi.repositories.MainRepository
import com.mehdik.mvvmdi.utils.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Singleton
    @Provides
    fun provideMainRepository(apiService: ApiService): MainRepository {
        return MainRepository(apiService)
    }

    @Provides
    fun provideCarouselImagesList() = mutableListOf<CarouselItem>()
}