package az.mb.shop.data.mapper

import az.mb.shop.data.local.entity.cart.CartAboutEntity
import az.mb.shop.data.local.entity.cart.CartImageEntity
import az.mb.shop.data.local.entity.favProduct.FavProductAboutEntity
import az.mb.shop.data.local.entity.favProduct.FavProductEntity
import az.mb.shop.data.local.entity.favProduct.FavProductImageEntity
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

fun FavProductEntity.toProduct(): Product {
    val mutableList: MutableList<String> = mutableListOf()
    images.forEach {
        mutableList.add(it.url)
    }

    return Product(
        id = favProductAboutEntity.id,
        brand = favProductAboutEntity.brand,
        category = favProductAboutEntity.category,
        discountPercentage = favProductAboutEntity.discountPercentage,
        price = favProductAboutEntity.price,
        rating = favProductAboutEntity.rating,
        stock = favProductAboutEntity.stock,
        thumbnail = favProductAboutEntity.thumbnail,
        title = favProductAboutEntity.title,
        description = favProductAboutEntity.description,
        images = mutableList
    )
}

fun Product.toFavProductAboutEntity(): FavProductAboutEntity {
    return FavProductAboutEntity(
        id = id,
        brand = brand,
        category = category,
        discountPercentage = discountPercentage,
        price = price,
        rating = rating,
        stock = stock,
        thumbnail = thumbnail,
        title = title,
        description = description
    )
}

fun Product.toCartAboutEntity(): CartAboutEntity {
    return CartAboutEntity(
        id = id,
        brand = brand,
        category = category,
        discountPercentage = discountPercentage,
        price = price,
        rating = rating,
        stock = stock,
        thumbnail = thumbnail,
        title = title,
        description = description
    )
}

fun Product.toListFavProductImageEntity(): List<FavProductImageEntity> {

    val list: MutableList<FavProductImageEntity> = mutableListOf()
    images.forEach {
        list.add(FavProductImageEntity(productId = id, url = it))
    }

    return list
}

fun Product.toListCartImageEntity(): List<CartImageEntity> {

    val list: MutableList<CartImageEntity> = mutableListOf()
    images.forEach {
        list.add(CartImageEntity(productId = id, url = it))
    }

    return list
}







