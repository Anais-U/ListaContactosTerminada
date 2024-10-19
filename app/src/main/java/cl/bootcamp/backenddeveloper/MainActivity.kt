package cl.bootcamp.backenddeveloper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import cl.bootcamp.backenddeveloper.navigation.NavGraph
import cl.bootcamp.backenddeveloper.ui.theme.BackendDeveloperTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BackendDeveloperTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}
