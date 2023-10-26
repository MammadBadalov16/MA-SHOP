package az.mb.shop

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import az.mb.shop.navigation.SetupHomeNavGraph
import az.mb.shop.navigation.SetupNavGraph
import az.mb.shop.presentation.home.HomeScreen
import az.mb.shop.presentation.main.MainScreen
import az.mb.shop.presentation.signIn.SignInScreen
import az.mb.shop.presentation.signup.SignUpScreen
import az.mb.shop.presentation.ui.theme.ShopTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


        setContent {
            ShopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navHostController = rememberNavController()
                    //SetupNavGraph(navController = navHostController)
                    // HomeScreen()
                    // SetupHomeNavGraph(navController = navHostController)
                    MainScreen()
                }
            }
        }
    }
}

