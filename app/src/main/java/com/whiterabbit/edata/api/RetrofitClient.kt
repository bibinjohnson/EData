package com.whiterabbit.edata.api

import com.google.gson.GsonBuilder
import com.whiterabbit.edata.BuildConfig
import com.whiterabbit.edata.api.models.HomeResponseData
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface RetrofitClient {

    companion object {
        private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private val client = OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(Interceptor { chain ->
                var request = chain.request()
                val urlBuilder = request.url.newBuilder()
                println("old url $urlBuilder")
                request = request.newBuilder().url(urlBuilder.build())
                    .addHeader("Accept-Encoding", "identity").build();
                try {
                    chain.proceed(request)
                } catch (e: Exception) {
                    e.printStackTrace()
                    val dummy = JSONObject().apply {
                        put("message", "${e.message}")
                        put("url", "$urlBuilder")
                    }.toString()
                    Response.Builder()
                        .code(200)
                        .message("OK")
                        .protocol(Protocol.HTTP_1_1)
                        .request(Request.Builder().url("http://localhost/").build())
                        .message(e.message ?: "")
                        .body(dummy.toResponseBody("text/plain".toMediaType()))
                        .build()
                }
            })
            .addInterceptor(logging)
            .build()


        private fun createRetrofitClient(): RetrofitClient = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(client)
            .build()
            .create(RetrofitClient::class.java)

        val apiService: RetrofitClient by lazy { createRetrofitClient() }
    }

    @GET("5d565297300000680030a986")
    suspend fun getUserData(): List<HomeResponseData>
}