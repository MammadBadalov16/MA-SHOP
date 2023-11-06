package az.mb.shop.domain.use_case.cart

data class CartUseCase(
    val getCartUseCase: GetCartUseCase,
    val addCartUseCase: AddCartUseCase,
    val deleteCartUseCase: DeleteCartUseCase
)