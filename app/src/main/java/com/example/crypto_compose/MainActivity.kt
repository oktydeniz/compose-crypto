package com.example.crypto_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.crypto_compose.ui.theme.CryptoComposeTheme
import com.example.crypto_compose.views.DetailScreen
import com.example.crypto_compose.views.ListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoComposeTheme {
                val navController = rememberNavController()
                StartDrawScreens(navController = navController)
            }
        }
    }

    @Composable
    fun StartDrawScreens(navController: NavHostController){
        NavHost(navController = navController, startDestination = "crypto_list_screen") {
            composable(route="crypto_list_screen"){
                ListScreen(navController = navController)
            }
            composable(route= "crypto_detail_screen/{cryptoId}/{cryptoPrice}", arguments = listOf(
                navArgument("cryptoId"){
                    type = NavType.StringType
                },
                navArgument("cryptoPrice"){
                    type = NavType.StringType
                }
            ) ){
                val cryptoId = remember{
                    it.arguments?.getString("cryptoId")
                }
                val cryptoPrice = remember {
                    it.arguments?.getString("cryptoPrice")
                }
                DetailScreen(navController = navController,
                    id = cryptoId ?: "",
                    price = cryptoPrice ?: "")
            }
        }
    }
}
