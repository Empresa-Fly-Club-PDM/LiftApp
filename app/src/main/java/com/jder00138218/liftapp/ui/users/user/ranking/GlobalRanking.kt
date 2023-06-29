package com.jder00138218.liftapp.ui.users.user.ranking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.Menu
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowAdd
import com.jder00138218.liftapp.ui.users.user.SearchBar


@Composable
fun GlobalRankingUsers(navController: NavController) {

    val handleAddOnClick = {
        navController.navigate(route = Rutas.UserCreateRoutine.ruta)
    }
    val handleBackOnClick = {
        navController.navigate(route = Rutas.DashboardUser.ruta)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(color = Color.White)
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            Column(
                // 1
                Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxHeight(0.1f)
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                HeaderBarBackArrowAdd("Ranking", navController, addOnClick = handleAddOnClick, backOnClick = handleBackOnClick)
            }


            Column(
                Modifier
                    .align(Alignment.Center)
                    .fillMaxHeight(0.84f)
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBar()
                RankingInfo()
            }

            Column( // 3
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxHeight(0.06f)
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }

        }

    }

}


