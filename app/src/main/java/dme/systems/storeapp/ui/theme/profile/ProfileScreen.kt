package dme.systems.storeapp.ui.theme.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import dme.systems.storeapp.ui.theme.ScaffoldScreen

@Composable
fun HomeScreen(navController: NavHostController) {
    ScaffoldScreen(navController) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Home Screen")
        }
    }
}
