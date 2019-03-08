package idd.urbanido.model

data class AuthorizationResponse(
        val auth: Auth
) {
    data class Auth(
            val email: String,
            val password: String
    )
}