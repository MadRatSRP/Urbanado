package idd.urbanido.network

import idd.urbanido.model.registration.RegistrationResponse
import idd.urbanido.model.users.UsersResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {

    @POST("api/v1/users")
    fun registerUser(@Body registrationResponse: RegistrationResponse): Call<RegistrationResponse>


    @GET("api/v1/users")
    fun getuser(): Observable<UsersResponse> }



