package idd.urbanido.network

import idd.urbanido.model.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface NetworkInterface {

    @POST("api/v1/users")
    fun registerUser(@Body registrationResponse: RegistrationResponse)
            : Call<RegistrationResponse>

    @POST("api/v1/user_token")
    fun authorizeUser(@Body authorizationResponse: AuthorizationResponse)
            : Call<Auth>

    @GET("api/v1/users/{id}/edit")
    fun getProfile(@Header("Token") token: String, @Path("id") id: String)
            : Observable<ProfileResponse>

    @GET("api/v1/users")
    fun getQuotesList(): Observable<List<QuotesResponse>>

    @PUT("api/v1/users/{id}")
    fun changeProfileData(@Path("id") id: String, @Body profileResponse: ProfileResponse)
            : Observable<ProfileResponse>




}
