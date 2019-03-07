package idd.urbanido.model.authorization

data class AuthorizationResponse(
        val user: User
) {
    data class User(
            val name: String,
            val password: String
    )
}