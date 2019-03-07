package idd.urbanido.model.registration

data class RegistrationResponse(
        val user: User
) {
    data class User(
            val email: String,
            val name: String,
            val password: String,
            val password_confirm: String,
            val phone: String
    )
}