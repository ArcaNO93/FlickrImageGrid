package com.example.ilcarro.dagger.modules

import android.content.Context
import android.content.SharedPreferences
import com.example.ilcarro.dagger.scopes.GlobalScope
import com.example.ilcarro.data.api.IlCarroAPI
import com.example.ilcarro.utils.ComponentProvider
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class AppModule {

    @Provides
    @GlobalScope
    fun provideShapedPref(application: ComponentProvider): SharedPreferences = application.getSharedPreferences("Service", Context.MODE_PRIVATE)

    @Provides
    @GlobalScope
    fun provideRetrofit() : IlCarroAPI =
            Retrofit
                .Builder()
                .baseUrl("https://rent-cars-app.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpClient()
                    .newBuilder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build()
                )
                .build()
                .create(IlCarroAPI::class.java)
}
