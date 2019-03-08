package idd.urbanido.network

import idd.urbanido.model.AuthorizationResponse
import idd.urbanido.model.RegistrationResponse
import idd.urbanido.model.ProfileResponse
import idd.urbanido.model.QuotesResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NetworkInterface {

    @POST("api/v1/users")
    fun registerUser(@Body registrationResponse: RegistrationResponse)
            : Call<RegistrationResponse>

    @POST("api/v1/users")
    fun authorizeUser(@Body authorizationResponse: AuthorizationResponse)
            : Call<String>

    @GET("api/v1/users/{id}")
    fun getProfile(@Path("id") id: String): Observable<ProfileResponse>

    @GET("api/v1/users")
    fun getQuotesList(): Observable<List<QuotesResponse>>


}
