package idd.urbanido.network

import android.content.Context
import com.readystatesoftware.chuck.api.ChuckInterceptor
import idd.urbanido.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class NetworkClient {
    companion object {
        fun getRetrofit(context: Context): Retrofit? {
            var retrofit: Retrofit? = null
            val url = context.getString(R.string.URL)

            if (retrofit == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                val client = OkHttpClient.Builder()
                        .connectTimeout(1000, TimeUnit.SECONDS)
                        .writeTimeout(1000, TimeUnit.SECONDS)
                        .readTimeout(1000, TimeUnit.SECONDS)
                        // LoggingInterceptor
                        .addInterceptor(interceptor)
                        // Chuck
                        .addInterceptor(ChuckInterceptor(context))
                        .build()
                retrofit = Retrofit.Builder()
                        .baseUrl(url)
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