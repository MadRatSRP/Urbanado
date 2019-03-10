package idd.urbanido.network

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import idd.urbanido.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class NetworkClient {

    companion object {
        private var retrofit: Retrofit? = null
        private const val URL = "https://hakatonapp.herokuapp.com/"

        fun getRetrofit(context: Context?): Retrofit? {
            if (retrofit == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                val client = OkHttpClient.Builder()
                        .connectTimeout(1000, TimeUnit.SECONDS)
                        .writeTimeout(1000, TimeUnit.SECONDS)
                        .readTimeout(1000, TimeUnit.SECONDS)

                        //LoggingInterceptor
                        .addInterceptor(interceptor)

                        //Chuck
                        .addInterceptor(ChuckInterceptor(context))
                        .build()

                retrofit = Retrofit.Builder()
                        .baseUrl(URL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(client)
                        .build()
            }
            return retrofit
        }
    }
}