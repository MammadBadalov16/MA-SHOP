package az.mb.shop.data.mapper

import az.mb.shop.data.remote.dto.CategoryDTO
import az.mb.shop.data.remote.dto.product.ProductDTO
import az.mb.shop.data.remote.dto.product.ProductsDTO
import az.mb.shop.domain.model.Category
import az.mb.shop.domain.model.Product

fun ProductDTO.toProduct(): Product {
    return Product(
        id = id,
        title = title,
        thumbnail = thumbnail,
        stock = stock,
        rating = rating,
        price = price,
        images = images,
        description = description,
        brand = brand,
        category = category,
        discountPercentage = discountPercentage
    )
}

fun ProductsDTO.toProducts(): List<Product> {
    val list: List<Product> = emptyList()
    products.forEach {
        list.contains(
            it.toProduct()
        )
    }
    return list
}

fun CategoryDTO.toCategory() = map {
    Category(it)
}




