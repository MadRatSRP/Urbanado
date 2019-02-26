package idd.urbanido.network

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkClient {
    //fun NetworkClient() {}
    companion object {
        private var retrofit: Retrofit? = null
        private val URL = "https://hacaton2018app.herokuapp.com/"
        fun getRetrofit(context: Context): Retrofit? {
            if (retrofit == null) {

                val client = OkHttpClient.Builder()
                                         .connectTimeout(10, TimeUnit.SECONDS)
                                         .writeTimeout(10, TimeUnit.SECONDS)
                                         .readTimeout(30, TimeUnit.SECONDS)
                                         //Chuck
                                         .addInterceptor(ChuckInterceptor(context))
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