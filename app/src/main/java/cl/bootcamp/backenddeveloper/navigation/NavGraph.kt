package cl.bootcamp.backenddeveloper.navigation


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cl.bootcamp.backenddeveloper.view.AddContactView
import cl.bootcamp.backenddeveloper.view.EditContactView
import cl.bootcamp.backenddeveloper.view.HomeView
import cl.bootcamp.backenddeveloper.viewModel.ContactViewModel


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "HomeView") {
        composable("HomeView") {
            val viewModel: ContactViewModel = hiltViewModel() // Obtener el ContactViewModel
            HomeView(navController = navController, viewModel = viewModel)
        }
        composable("add_contact") {
            val viewModel: ContactViewModel = hiltViewModel() // Obtener el ContactViewModel
            AddContactView(navController = navController, viewModel = viewModel)
        }
        composable("edit_contact/{contactId}") { backStackEntry ->
            val contactId = backStackEntry.arguments?.getString("contactId")?.toLong() ?: 0L
            val viewModel: ContactViewModel = hiltViewModel() // Obtener el ContactViewModel
            EditContactView(navController = navController, viewModel = viewModel, contactId = contactId)
        }
    }
}