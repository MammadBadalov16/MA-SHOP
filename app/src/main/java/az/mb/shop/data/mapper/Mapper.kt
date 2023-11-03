package az.mb.shop.data.mapper

import az.mb.shop.data.local.entity.FavProductAboutEntity
import az.mb.shop.data.local.entity.FavProductEntity
import az.mb.shop.data.local.entity.FavProductImageEntity
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

fun Product.toListFavProductImageEntity(): List<FavProductImageEntity> {

    val list: MutableList<FavProductImageEntity> = mutableListOf()
    images.forEach {
        list.add(FavProductImageEntity(productId = id, url = it))
    }

    return list
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








