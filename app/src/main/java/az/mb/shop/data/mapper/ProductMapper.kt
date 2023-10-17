package az.mb.shop.data.mapper

import az.mb.shop.data.remote.dto.product.ProductDTO
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