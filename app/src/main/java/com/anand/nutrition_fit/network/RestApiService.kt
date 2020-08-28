package com.anand.nutrition_fit.network

import com.anand.nutrition_fit.BuildConfig
import com.anand.nutrition_fit.model.ApprovedFoodData
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface RestApiService {

    @GET("b/5f2c36626f8e4e3faf2cb42e")
    fun getApprovedFoodListData(): Observable<ApprovedFoodData>

    companion object Factory {
        fun create(): RestApiService {
            val okHttpBuilder= OkHttpClient.Builder()
            okHttpBuilder.addInterceptor { chain ->
                var request=chain.request()
                val requestBuilder=request.newBuilder()
                request=requestBuilder.build()
                chain.proceed(request)
            }
            okHttpBuilder.connectTimeout(2, TimeUnit.MINUTES)
            okHttpBuilder.readTimeout(1, TimeUnit.MINUTES)
            val okHttpClient=okHttpBuilder.build()
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(BuildConfig.API_BASE_URL)
                .build()

            return retrofit.create(RestApiService::class.java)
        }
    }
}