package idd.urbanido.model

data class AuthorizationResponse(
        val user: User
) {
    data class User(
            val name: String,
            val password: String
    )
}