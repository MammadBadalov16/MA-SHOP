package az.mb.shop

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import az.mb.shop.common.Constants
import az.mb.shop.data.local.ShopDatabase
import az.mb.shop.navigation.graphs.Graph
import az.mb.shop.navigation.graphs.RootNavGraph
import az.mb.shop.presentation.favorites.FavoritesScreen
import az.mb.shop.presentation.product.ProductScreen
import az.mb.shop.presentation.ui.theme.ShopTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navHostController: NavHostController


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        val db = Room.databaseBuilder(
            applicationContext, ShopDatabase::class.java,
            Constants.DATABASE_NAME
        ).allowMainThreadQueries().build()


        setContent {
            ShopTheme {
                SetStatusBarColor(Color.White)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    /*val sharedPreferences = PreferencesManager(applicationContext)
                    val token = sharedPreferences.getData(Constants.TOKEN, "")
                    navHostController = rememberNavController()

                    if (token == "fakeToken")
                        RootNavGraph(navController = navHostController, startGraph = Graph.NAV)
                    else RootNavGraph(navController = navHostController)*/
                    //  HomeScreen(drawerState = drawer)
                    //  navHostController = rememberNavController()
                    // RootNavGraph(navController = navHostController, startGraph = Graph.NAV)
                    // ProductScreen()


                    navHostController = rememberNavController()
                    ProductScreen(navController = navHostController)


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

