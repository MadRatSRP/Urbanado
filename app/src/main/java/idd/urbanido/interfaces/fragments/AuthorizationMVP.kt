package idd.urbanido.interfaces.fragments

import android.content.Context
import android.widget.EditText
import idd.urbanido.model.authorization.AuthorizationResponse
import retrofit2.Call

interface AuthorizationMVP {
    interface View {
        fun setupMVP()
    }

    interface Presenter {
        fun authorizeUser(context: Context, login: EditText, password: EditText)
        fun getData(context: Context, authorizationResponse: AuthorizationResponse)
    }

    interface Repository {
        fun authorizeUserCall(context: Context, authorizationResponse: AuthorizationResponse)
                : Call<AuthorizationResponse>?
    }
}