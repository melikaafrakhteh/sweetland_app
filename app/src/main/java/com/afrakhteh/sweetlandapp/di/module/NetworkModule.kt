package com.afrakhteh.sweetlandapp.di.module

import com.afrakhteh.sweetlandapp.constants.Strings
import com.afrakhteh.sweetlandapp.model.data.network.SweetsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideApi(): SweetsApi{
       return Retrofit.Builder()
               .baseUrl(Strings.BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
               .build()
               .create(SweetsApi::class.java)
    }
}