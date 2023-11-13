package az.mb.shop.presentation.components

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import com.droidman.ktoasty.KToasty
import com.droidman.ktoasty.showErrorToast
import com.droidman.ktoasty.showSuccessToast

@Composable
fun ToastSuccessC(
    context: Context,
    message: String
) {
    KToasty.success(context = context, message, Toast.LENGTH_SHORT, true).show()
}

fun ToastSuccess(
    context: Context,
    message: String
) {
    context.showSuccessToast(message = message, Toast.LENGTH_SHORT, true)
}

@Composable
fun ToastErrorC(
    context: Context,
    message: String
) {
    KToasty.error(context = context, message, Toast.LENGTH_SHORT, true).show()
}

fun ToastError(
    context: Context,
    message: String
) {
    context.showErrorToast(message = message, Toast.LENGTH_SHORT, true)
}

