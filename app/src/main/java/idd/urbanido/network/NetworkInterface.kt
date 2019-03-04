package idd.urbanido.network

import idd.urbanido.model.EventResponse
import idd.urbanido.model.registered_user.RegisteredUsers
import idd.urbanido.model.registration.RegistrationResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkInterface {

    @POST("events")
    fun getpost(@Body event: EventResponse): Call<EventResponse>

    @POST("api/v1/users")
    fun postuser(@Body registration: RegistrationResponse): Call<RegistrationResponse>


    @GET("api/v1/users")
    fun getuser(): Observable<List<RegisteredUsers>>
}
