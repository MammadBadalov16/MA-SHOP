package az.mb.shop.data.remote.dto.product

data class ProductDTO(
    val brand: String,
    val category: String,
    val description: String,
    val discountPercentage: Double,
    val id: Int,
    val images: List<String>,
    val price: Double,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val title: String
)