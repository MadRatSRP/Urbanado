package idd.urbanido.network

import java.util.concurrent.TimeUnit

import idd.urbanido.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient {

    fun NetworkClient() {

    }

    companion object {


        var retrofit: Retrofit? = null
        private val URL = "https://hacaton2018app.herokuapp.com/"

        fun getRetrofit(): Retrofit {
            if (retrofit == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

                val client = OkHttpClient.Builder()
                        .writeTimeout(95, TimeUnit.SECONDS)
                        .readTimeout(95, TimeUnit.SECONDS)
                        .connectTimeout(95, TimeUnit.SECONDS)
                        .addInterceptor(interceptor)
                        .build()

                retrofit = Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(client)
                        .build()
            }
            return retrofit
        }
    }
}
