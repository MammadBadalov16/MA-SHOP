package az.mb.shop.domain.model

data class Cart(
    val id: Int,
    val title: String,
    val price: Int,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    var quantity: Int? = null
)
