package idd.urbanido.network;

import java.util.concurrent.TimeUnit;

import idd.urbanido.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {


    public static Retrofit retrofit;
    private static String URL = "https://hacaton2018app.herokuapp.com/";

    public void NetworkClient() {

    }

    public static Retrofit getRetrofit() {
        if (retrofit==null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

            OkHttpClient client = new OkHttpClient.Builder()
                    .writeTimeout(95, TimeUnit.SECONDS)
                    .readTimeout(95, TimeUnit.SECONDS)
                    .connectTimeout(95, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
