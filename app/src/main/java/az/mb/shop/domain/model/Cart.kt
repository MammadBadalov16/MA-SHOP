package az.mb.shop.domain.model

data class Cart(
    val cartId: Int,
    val id: Int,
    val title: String,
    val price: Double,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    var quantity: Int? = null
)
