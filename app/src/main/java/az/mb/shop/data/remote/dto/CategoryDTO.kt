package az.mb.shop.data.remote.dto

class CategoryDTO : ArrayList<CategoryItemDTO>()

data class CategoryItemDTO(
    val slug: String,
    val name: String,
    val url: String
)