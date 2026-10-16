package com.seminarioAndroid.cinecane.data.remote.api

import com.seminarioAndroid.cinecane.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private const val BASE_URL = "https://api.themoviedb.org/3/"

/**
 * Configura la conexión con la API de TMDB (se crea una sola vez en toda la app).
 *
 * - Agrega el token de autenticación a cada request mediante un interceptor.
 * - Usa Gson para convertir el JSON de la respuesta en los DTO.
 * - Expone [api], la implementación real de [TmdbApiService] que usa el Repository.
 */

object RetrofitClient {

    // Cliente HTTP: el interceptor le agrega el token a todos los requests que salen
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer ${BuildConfig.TMDB_TOKEN}")
                .build()
            chain.proceed(request)
        }
        .build()

    val api: TmdbApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<TmdbApiService>()

}