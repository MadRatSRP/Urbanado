package idd.urbanido.model.authorization

data class Authorization(
        val auth: Auth
) {
    data class Auth(
            val email: String,
            val password: String
    )
}