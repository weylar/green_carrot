package com.android.greencarot.remote


import android.content.Context
import com.android.greencarot.BuildConfig
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NetworkServiceFactory() {

    fun <T> makeService(service: Class<T>): T {
        val okHttpClient = makeOkHttpClient(makeLoggingInterceptor())
        return makeService(okHttpClient, makeGson(), service)
    }

    private fun <T> makeService(
        okHttpClient: OkHttpClient, gson: Gson,
        service: Class<T>
    ): T {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gc-rest.herokuapp.com/api/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(service)
    }

    private fun makeOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain: Interceptor.Chain ->
                val request: Request =
                    chain.request()
                        .newBuilder()
                        .build()
                chain.proceed(request)
            }
            .retryOnConnectionFailure(true)
            .build()

    }

    private fun makeGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }


}