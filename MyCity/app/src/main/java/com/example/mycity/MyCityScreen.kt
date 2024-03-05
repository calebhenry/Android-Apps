package com.example.mycity

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycity.model.Destination
import com.example.mycity.ui.CategoryScreen
import com.example.mycity.ui.DestinationScreen
import com.example.mycity.ui.HomeScreen
import com.example.mycity.ui.MyCityViewModel
import javax.security.auth.DestroyFailedException

enum class MyCityScreen(val title: String) {
    Home("Home"),
    Category("Category"),
    Destination("Destination")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    currentScreen: MyCityScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(currentScreen.title) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back button"
                    )
                }
            }
        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )

}

@Composable
fun MyCityApp(
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyCityScreen.valueOf(
        backStackEntry?.destination?.route ?: MyCityScreen.Home.name
    )

    val viewModel:MyCityViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold (
        topBar = {
            AppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MyCityScreen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MyCityScreen.Home.name) {
                HomeScreen(
                    onCategoryClick = {
                        viewModel.updateCategory(it)
                        navController.navigate(MyCityScreen.Category.name)
                    },
                    categories = uiState.categoriesList.orEmpty(),
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = MyCityScreen.Category.name) {
                CategoryScreen(
                    onDestinationClick = {
                        viewModel.updateDestination(it)
                        navController.navigate(MyCityScreen.Destination.name)
                    },
                    destinations = uiState.destinationsList.orEmpty(),
                    modifier = Modifier.fillMaxSize()
                )
            }
            composable(route = MyCityScreen.Destination.name) {
                DestinationScreen(
                    destination = uiState.destination?: Destination(
                        name = "",
                        description = "",
                        features = ""
                    ),
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}