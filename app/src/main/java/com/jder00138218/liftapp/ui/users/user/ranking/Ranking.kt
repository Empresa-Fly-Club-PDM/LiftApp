package com.jder00138218.liftapp.ui.users.user.ranking

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.network.dto.user.user
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.Menu
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowAdd
import com.jder00138218.liftapp.ui.users.user.SearchBar
import com.jder00138218.liftapp.ui.users.user.UserBottomMenu
import com.jder00138218.liftapp.ui.users.user.ranking.viewmodel.RankinViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RankingUsers(navController: NavController ) {
    val rankinViewModel:RankinViewModel = viewModel(
        factory = RankinViewModel.Factory
    )
    var text by remember { mutableStateOf("") }

    LaunchedEffect(text) {
        val currentText = text
        delay(500) // Add a short delay before executing the search
        if (currentText == text) { // Ensure the text hasn't changed during the delay
            rankinViewModel.getRanking(text)
        }
    }

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

            OutlinedTextField(value = text, onValueChange = { newText: String ->
                text = newText
                rankinViewModel.getRanking(text)
            }, modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(
                    colorResource(id = R.color.field)
                )
                .border(width = 0.dp, color = Color.White),
                placeholder = { Text(text = "Buscar..", color = Color(R.color.gray_text)) },
            )
              LazyColumn(modifier = Modifier.fillMaxHeight(0.7f)){
                items(rankinViewModel.users) {
                    RankingInfoRow(it, navController = navController)
                }
            }
            UserBottomMenu(navController = navController)
        }
    }
}

@Composable
fun RankingInfoRow(user: user, navController: NavController){

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Column(modifier = Modifier
            .fillMaxHeight(), 
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center) {
            Text(text = user.nombrecompleto)
            Text(text = getRank(user.points))
        }
        IconButton(onClick = {navController.navigate("rutas_friend_profile/${user.id}")}) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "AddFriend"
            )
        }
    }

}

fun getRank(user_points:Int):String{
    var rank:String =""
    if(user_points<4000){
        rank = "Principiante"
    }else if(user_points>=4000 && user_points<10000){
        rank = "Novato 1"
    }else if(user_points>=10000 && user_points<18000){
        rank = "Novato 2"
    }else if(user_points>=18000 && user_points<26000){
        rank = "Novato 3"
    }else if(user_points>=26000 && user_points<36000){
        rank = "Intermedio 1"
    }else if(user_points>=36000 && user_points<48000){
        rank = "Intermedio 2"
    }else if(user_points>=48000 && user_points<64000){
        rank = "Intermedio 3"
    }else if(user_points>=64000 && user_points<88000){
        rank = "Élite 1"
    }else if(user_points>=88000 && user_points<120000){
        rank = "Élite 2"
    }else if(user_points>=120000){
        rank = "Élite 3"
    }
    return rank
}