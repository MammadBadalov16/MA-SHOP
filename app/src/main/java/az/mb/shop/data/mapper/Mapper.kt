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

fun ProductsDTO.toProduct(): List<Product> {
    val list: List<Product> = emptyList()
    products.forEach {
        list.contains(
            it.toProduct()
        )
    }
    return list
}

fun CategoryDTO.toCategory() = map {
    Category(category = it)
}
/*
fun ProductEntity.toProduct(): Product {
    return Product(
        id = pr.id,
        title = pr.title,
        thumbnail = pr.thumbnail,
        stock = pr.stock,
        rating = pr.rating,
        price = pr.price,
        images = null,
        description = pr.description,
        brand = pr.brand,
        category = pr.category,
        discountPercentage = pr.discountPercentage
    )
}

fun HomeEvents.FavProduct.toProductEntity(): ProductEntity {
    return ProductEntity(
        id = data.id,
        title = data.title,
        thumbnail = data.thumbnail,
        stock = data.stock,
        rating = data.rating,
        price = data.price,
        images = data.images,
        description = data.description,
        brand = data.brand,
        category = data.category,
        discountPercentage = data.discountPercentage
    )
}

fun Product.toProductEntity(): ProductEntity {
    return ProductEntity(
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
}*/








