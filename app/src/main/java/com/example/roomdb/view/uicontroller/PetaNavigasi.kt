package com.example.roomdb.view.uicontroller

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.roomdb.view.DetailSiswaScreen
import com.example.roomdb.view.EditSiswaScreen
import com.example.roomdb.view.EntrySiswaScreen
import com.example.roomdb.view.HomeScreen
import com.example.roomdb.view.route.DestinasiDetailSiswa
import com.example.roomdb.view.route.DestinasiEditSiswa
import com.example.roomdb.view.route.DestinasiEntry
import com.example.roomdb.view.route.DestinasiHome

@Composable
fun SiswaApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    HostNavigasi(navController = navController, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {

        // ======================
        // HOME
        // ======================
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiEntry.route)
                },
                navigateToItemDetail = { siswaId ->
                    navController.navigate(
                        DestinasiDetailSiswa.createRoute(siswaId)
                    )
                }
            )
        }

        // ======================
        // ENTRY
        // ======================
        composable(DestinasiEntry.route) {
            EntrySiswaScreen(
                navigateBack = { navController.popBackStack() }
            )
        }

        // ======================
        // DETAIL
        // ======================
        composable(
            route = DestinasiDetailSiswa.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetailSiswa.itemIdArg) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            DetailSiswaScreen(
                navigateToEditItem = { siswaId ->
                    navController.navigate(
                        DestinasiEditSiswa.createRoute(siswaId)
                    )
                },
                navigateBack = { navController.popBackStack() }
            )
        }

        // ======================
        // EDIT
        // ======================
        composable(
            route = DestinasiEditSiswa.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiEditSiswa.itemIdArg) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val id = backStackEntry.arguments?.getInt(DestinasiEditSiswa.itemIdArg)
                ?: 0 // fallback agar tidak crash

            EditSiswaScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.popBackStack() }
            )
        }
    }
}
