package az.mb.shop.presentation.profile

import az.mb.shop.domain.model.Product
import az.mb.shop.domain.model.User

sealed class ProfileEvents {
    data class SaveChanges(val user: User) : ProfileEvents()
}
