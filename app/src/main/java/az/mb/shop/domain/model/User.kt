package az.mb.shop.domain.model

data class User(
    val uid: String? = null,
    val name: String? = null,
    val surname: String? = null,
    val email: String? = null,
    val password: String? = null,
    val address: String? = null,
    val phone: String? = null
)
