package idd.urbanido.network;

import idd.urbanido.model.EventResponse;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NetworkInterface {

    @POST("events")
    @FormUrlEncoded
    Observable<EventResponse> postEvent(@Field("discription") String discription,
                                        @Field("title") String title,
                                        @Field("address") String address,
                                        @Field("koord") String koord,
                                        @Field("date") String date,
                                        @Field("type") String type,
                                        @Field("picture") String picture);
