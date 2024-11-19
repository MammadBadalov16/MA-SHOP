package az.mb.shop.data.mapper

import az.mb.shop.data.local.entity.CartEntity
import az.mb.shop.data.local.entity.FavProductEntity
import az.mb.shop.data.remote.dto.CategoryDTO
import az.mb.shop.data.remote.dto.product.ProductDTO
import az.mb.shop.data.remote.dto.product.ProductsDTO
import az.mb.shop.domain.model.Cart
import az.mb.shop.domain.model.Category
import az.mb.shop.domain.model.Product
import az.mb.shop.domain.model.User
import com.google.firebase.auth.FirebaseUser


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

fun CategoryDTO.toCategory(): List<Category> = map {
    Category(category = it.name)
}

fun FavProductEntity.toProduct(): Product {

    return Product(
        id = id,
        brand = brand,
        category = category,
        discountPercentage = discountPercentage,
        price = price,
        rating = rating,
        stock = stock,
        thumbnail = thumbnail,
        title = title,
        description = description,
        images = emptyList()
    )
}

fun Product.toFavProductEntity(): FavProductEntity {
    return FavProductEntity(
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

fun Product.toCartEntity(): CartEntity {
    return CartEntity(
        id = id,
        price = price,
        rating = rating,
        thumbnail = thumbnail,
        title = title,
        stock = stock
    )
}

fun CartEntity.toCart(): Cart {
    return Cart(
        cartId = cartId!!,
        id = id,
        price = price,
        rating = rating,
        thumbnail = thumbnail,
        title = title,
        stock = stock,
        quantity = quantity
    )
}

fun FirebaseUser.toUser(): User? {
    return User(
        name = displayName!!,
        surname = displayName!!,
        email = email!!,
        password = ""
    )
}








