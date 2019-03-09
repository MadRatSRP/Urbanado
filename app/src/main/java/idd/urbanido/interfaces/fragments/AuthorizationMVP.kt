package idd.urbanido.interfaces.fragments

import android.content.Context
import android.widget.EditText
import idd.urbanido.model.authorization.AuthorizationResponse
import idd.urbanido.model.authorization.Authorization
import retrofit2.Call

interface AuthorizationMVP {
    interface View {
        fun setupMVP()
        fun saveToken(token: String)
        fun moveToQuotes()
        fun showSnack(text: String)
    }

    interface Presenter {
        fun authorizeUser(context: Context, login: EditText, password: EditText)
        fun getData(context: Context, authorization: Authorization)
    }

    interface Repository {
        fun authorizeUserCall(context: Context, authorization: Authorization)
                : Call<AuthorizationResponse>?
    }
}