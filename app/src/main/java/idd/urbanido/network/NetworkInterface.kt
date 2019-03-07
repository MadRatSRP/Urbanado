package idd.urbanido.network

import idd.urbanido.model.authorization.AuthorizationResponse
import idd.urbanido.model.registration.RegistrationResponse
import idd.urbanido.model.registered_user.RegisteredUsers
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
            : Call<AuthorizationResponse>

    @GET("api/v1/users")
    fun getusers(): Observable<List<RegisteredUsers>>

    @GET("api/v1/users/{id}")
    fun getuser(@Path("id") id: String): Observable<RegisteredUsers>
}
