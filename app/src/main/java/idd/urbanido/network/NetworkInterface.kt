package idd.urbanido.network

import idd.urbanido.model.EventResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface NetworkInterface {

    @POST("events")
    @FormUrlEncoded
    fun postEvent(@Field("discription") discription: String,
                  @Field("title") title: String,
                  @Field("address") address: String,
                  @Field("koord") koord: String,
                  @Field("date") date: String,
                  @Field("type") type: String,
                  @Field("picture") picture: String): Observable<EventResponse>
}
