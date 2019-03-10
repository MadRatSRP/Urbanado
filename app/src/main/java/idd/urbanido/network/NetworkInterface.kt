package idd.urbanido.network

import idd.urbanido.model.*
import idd.urbanido.model.authorization.Authorization
import idd.urbanido.model.authorization.AuthorizationResponse
import idd.urbanido.model.profile.Profile
import idd.urbanido.model.profile.ProfileResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface NetworkInterface {

    //GET_USER_ID
    @GET("api/v1/current_user")
    fun getUserId(@Header("Authorization") token: String)
            : Observable<IdResponse>

    //QUOTES
    @GET("api/v1/financial_instruments")
    fun getQuotesList(@Header("Authorization") token: String)
            : Observable<List<QuotesResponse>>

    //PROFILE_QUOTE
    @GET("api/v1/financial_instruments/{id}")
    fun getProfileQuote(@Header("Authorization") token: String,
                        @Path("id") id: String)
            : Observable<List<ProfileQuoteResponse>>

    //REGISTRATION
    @POST("api/v1/users")
    fun registerUser(@Body registrationResponse: RegistrationResponse)
            : Call<RegistrationResponse>

    //AUTHORIZATION
    @POST("api/v1/user_token")
    fun authorizeUser(@Body authorization: Authorization)
            : Call<AuthorizationResponse>


    //PROFILE
    @GET("api/v1/users/{id}/edit")
    fun getProfile(@Header("Authorization") token: String,
                   @Path("id") id: String)
            : Observable<ProfileResponse>

    @PUT("api/v1/users/{id}/edit")
    fun updateProfile(@Header("Authorization") token: String,
                      @Path("id") id: String,
                      @Body profile: Profile)
            : Call<Profile>
}
