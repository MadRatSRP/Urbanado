package idd.urbanido.network

import idd.urbanido.model.*
import idd.urbanido.model.authorization.Authorization
import idd.urbanido.model.authorization.AuthorizationResponse
import idd.urbanido.model.profile.ProfileResponse
import idd.urbanido.model.profile_quote.PercentResponse
import idd.urbanido.model.profile_quote.ProfileQuoteResponse
import io.reactivex.Observable
import okhttp3.ResponseBody
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
    @GET("/api/v1/financial_instruments/{id}/analityc")
    fun getQuotePercent(@Header("Authorization") token: String,
                        @Path("id") id: String,
                        @Query("start_date") start_date: String,
                        @Query("finish_date") finish_date: String)
            : Observable<String>
    //REGISTRATION
    @POST("api/v1/users")
    fun registerUser(@Body registrationResponse: RegistrationResponse)
            : Call<ResponseBody>
    //AUTHORIZATION
    @POST("api/v1/user_token")
    fun authorizeUser(@Body authorization: Authorization)
            : Call<AuthorizationResponse>
    //PROFILE
    @GET("api/v1/users/{id}/edit")
    fun getProfile(@Header("Authorization") token: String,
                   @Path("id") id: String)
            : Observable<ProfileResponse>
    @PUT("api/v1/users/{id}")
    fun updateProfile(@Header("Authorization") token: String,
                      @Path("id") id: String,
                      @Body profileResponse: ProfileResponse)
            : Call<ProfileResponse>
}
