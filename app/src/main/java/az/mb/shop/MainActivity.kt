package az.mb.shop

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import az.mb.shop.common.Constants
import az.mb.shop.common.PreferencesManager
import az.mb.shop.navigation.graphs.Graph
import az.mb.shop.navigation.graphs.RootNavGraph
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val sharedPreferences = PreferencesManager(applicationContext)
                    val token = sharedPreferences.getData(Constants.TOKEN, "")
                    navHostController = rememberNavController()

                    if (token == "fakeToken")
                        RootNavGraph(navController = navHostController, startGraph = Graph.NAV)
                    else RootNavGraph(navController = navHostController)

                }
            }
        }
    }
}

