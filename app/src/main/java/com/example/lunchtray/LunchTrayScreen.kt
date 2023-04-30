/**
 * [NavHostController] used to navigate between multiple screens in the app
 */

package com.example.lunchtray

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.ui.OrderViewModel

enum class LunchTrayScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Entree(title = R.string.choose_entree),
    SideDish(title = R.string.choose_side_dish),
    Accompaniment(title = R.string.choose_accompaniment),
    Checkout(title = R.string.order_checkout)
}

/**
 * LunchTrayAppBar displays the top app bar of the app-
 * [canNavigateBack] determines if the backStack is empty or not,
 * [currentScreen] displays the name of the current activity in focus,
 * [navigateUp] is used to pop the non-empty back stack
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayAppBar(
    modifier: Modifier = Modifier,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    currentScreen: LunchTrayScreen
) {
    TopAppBar(
        title = { Text(stringResource(id = currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayApp(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = viewModel(),
    navHostController: NavHostController = rememberNavController()
) {
    val backStackEntry by navHostController.currentBackStackEntryAsState()
    val currentScreen = LunchTrayScreen.valueOf(
        backStackEntry?.destination?.route ?: LunchTrayScreen.Start.name
    )

    Scaffold(
        topBar = {
            LunchTrayAppBar(
                canNavigateBack = true,
                navigateUp = { /*TODO*/ },
                currentScreen = LunchTrayScreen.Start
            )
        }
    ) {
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navHostController,
            startDestination = LunchTrayScreen.Start.name,
            modifier = modifier.padding(it)
        ) {
            composable(route = LunchTrayScreen.Start.name) {

            }
            composable(route = LunchTrayScreen.Entree.name) {

            }
            composable(route = LunchTrayScreen.SideDish.name) {

            }
            composable(route = LunchTrayScreen.Accompaniment.name) {

            }
            composable(route = LunchTrayScreen.Checkout.name) {

            }
        }

    }

}


private fun cancelOrderAndNavigateBackToStartScreen(
    viewModel: OrderViewModel,
    navController: NavController
) {
    viewModel.resetOrder()
    navController.popBackStack(LunchTrayScreen.Start.name, inclusive = false)
}


@Preview
@Composable
fun LunchTrayAppBarPreview() {
    LunchTrayAppBar(
        canNavigateBack = false,
        navigateUp = {},
        currentScreen = LunchTrayScreen.Start
    )
}