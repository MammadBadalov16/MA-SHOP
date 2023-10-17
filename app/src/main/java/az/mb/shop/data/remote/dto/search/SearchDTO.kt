package az.mb.shop.data.remote.dto.search

data class SearchDTO(
    val limit: Int,
    val searchProducts: List<SearchProduct>,
    val skip: Int,
    val total: Int
)