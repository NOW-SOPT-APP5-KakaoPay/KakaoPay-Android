package org.now.sopt.sopt_kakaopay.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.now.sopt.sopt_kakaopay.BuildConfig
import retrofit2.Retrofit

object ApiFactory {
    private const val BASE_URL: String = BuildConfig.AUTH_BASE_URL

    val retrofit: Retrofit by lazy {
      val json = Json {
          ignoreUnknownKeys = true
      }
      Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
          .build()
  }

    inline fun <reified T> create(): T = retrofit.create(T::class.java)
}

object ServicePool {
    val apiService = ApiFactory.create<ApiService>()
}
