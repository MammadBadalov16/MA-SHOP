package az.mb.shop.presentation.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import az.mb.shop.R
import az.mb.shop.presentation.ui.theme.f3
import az.mb.shop.presentation.ui.theme.f5
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.auth.User

@Composable
fun DrawerHeader(user: FirebaseUser?) {

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.drawer_user),
                contentDescription = "user_image",
                Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(f5)
            )
            Spacer(modifier = Modifier.height(5.dp))

            if ((user?.displayName != null) && (user.email != null)) {
                Text(
                    text = user.displayName!!,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(text = user.email!!, fontSize = 14.sp, color = Color.Black)
            }
        }
    }
}