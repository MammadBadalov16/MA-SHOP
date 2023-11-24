package az.mb.shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import az.mb.shop.common.Constants
import az.mb.shop.common.PreferencesManager
import az.mb.shop.navigation.graphs.AuthGraph
import az.mb.shop.presentation.main.MainScreen
import az.mb.shop.presentation.ui.theme.ShopTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            ShopTheme {
                SetStatusBarColor(Color.White)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    navHostController = rememberNavController()

                    val sharedPreferences = PreferencesManager(applicationContext)
                    val token = sharedPreferences.getData(Constants.TOKEN, "")

                    if (token.isEmpty())
                        AuthGraph(navController = navHostController)
                    else MainScreen()

                }
            }
        }
    }
}


@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}

