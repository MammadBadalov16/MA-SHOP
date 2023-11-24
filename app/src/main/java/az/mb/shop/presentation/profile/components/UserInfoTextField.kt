package az.mb.shop.presentation.profile.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import az.mb.shop.presentation.ui.theme.placeHolder

@Composable
fun UserInfoTextField(
    label: String,
    initOldValue: String,
    isReadOnly: Boolean = true,
    onChangeValue: (String) -> Unit
) {

    var value by rememberSaveable { mutableStateOf("") }
    value = initOldValue

    OutlinedTextField(
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Gray,
            focusedLeadingIconColor = Color.Black,
            unfocusedLeadingIconColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            cursorColor = Color.Black,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        ),
        value = value,
        onValueChange = {
            value = it
            onChangeValue(it)
        },
        readOnly = isReadOnly,
        maxLines = 2,
        label = { Text(text = label) },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(),
        shape = CircleShape
    )
}