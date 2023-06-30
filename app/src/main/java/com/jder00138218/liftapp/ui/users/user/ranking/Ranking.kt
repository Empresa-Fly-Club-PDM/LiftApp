package com.jder00138218.liftapp.ui.users.user.ranking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.Menu
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowAdd
import com.jder00138218.liftapp.ui.users.user.SearchBar
import com.jder00138218.liftapp.ui.users.user.UserBottomMenu

@Composable
fun RankingUsers(navController: NavController ) {

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

        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween) {
            HeaderBarBackArrowAdd("Ranking", navController, addOnClick = handleAddOnClick, backOnClick = handleBackOnClick)
            SearchBar()
            LazyColumn(modifier = Modifier.fillMaxHeight(0.7f)){
                items(5) {
                    RankingInfoRow(name = "friend name", id = 1, navController = navController)
                }
            }
            UserBottomMenu(navController = navController)
        }
    }
}

@Composable
fun RankingInfoRow(name: String, id:Int?, navController: NavController){

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Column(modifier = Modifier
            .fillMaxHeight(), 
        horizontalAlignment = Alignment.CenterHorizontally, 
        verticalArrangement = Arrangement.Center) {
            Text(text = "name")
            Text(text = "puntos")
        }
        
    }
}