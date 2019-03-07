package idd.urbanido.repositories

import android.content.Context
import idd.urbanido.interfaces.fragments.RegistrationMVP
import idd.urbanido.model.registration.RegistrationResponse
import idd.urbanido.network.NetworkClient
import idd.urbanido.network.NetworkInterface
import retrofit2.Call

class RegistrationRepository: RegistrationMVP.Repository {
    override fun registerUserCall(context: Context, registrationResponse: RegistrationResponse)
            : Call<RegistrationResponse>? {
        return NetworkClient.getRetrofit(context)?.create<NetworkInterface>(NetworkInterface::class.java)
                ?.registerUser(registrationResponse)
    }
}