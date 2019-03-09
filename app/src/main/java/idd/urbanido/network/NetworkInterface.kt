package idd.urbanido.network

import idd.urbanido.model.*
import idd.urbanido.model.authorization.Authorization
import idd.urbanido.model.authorization.AuthorizationResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface NetworkInterface {

    @POST("api/v1/users")
    fun registerUser(@Body registrationResponse: RegistrationResponse)
            : Call<RegistrationResponse>

    @POST("api/v1/user_token")
    fun authorizeUser(@Body authorization: Authorization)
            : Call<AuthorizationResponse>

    @GET("api/v1/financial_instruments")
    fun getQuotesList(@Header("Authorization") token: String)
            : Observable<List<QuotesResponse>>

    //PROFILE
    @GET("api/v1/users/{id}/edit")
    fun getProfile(@Header("Authorization") token: String,
                   @Path("id") id: String)
            : Observable<ProfileResponse>

    @PUT("api/v1/users/{id}")
    fun updateProfile(@Header("Authorization") token: String,
                      @Path("id") id: String,
                      @Body profile: Profile)
            : Call<Profile>
}
