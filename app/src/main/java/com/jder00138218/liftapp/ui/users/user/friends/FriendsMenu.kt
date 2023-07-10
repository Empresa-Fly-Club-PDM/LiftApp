package com.jder00138218.liftapp.ui.users.user.friends

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jder00138218.liftapp.LiftAppApplication
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.userManager.AdminManagement.viewmodel.AdminManagementViewModel
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowAdd
import com.jder00138218.liftapp.ui.users.user.SearchBar
import com.jder00138218.liftapp.ui.users.user.UserBottomMenu
import com.jder00138218.liftapp.ui.users.user.friends.viewmodel.FriendMenuViewModel
import com.jder00138218.liftapp.ui.users.user.ranking.RankingInfoRow

@Composable
fun FriendsMenu(navController: NavController){
    val vm: FriendMenuViewModel = viewModel(
        factory = FriendMenuViewModel.Factory
    )

    val context = LocalContext.current
    val app = context.applicationContext as LiftAppApplication

    LaunchedEffect(Unit, block = {
        vm.getMyFriends(app.getUserId())
    })
    val handleAddOnClick = {
        navController.navigate(route = "rutas_find_friends/${app.getUserId()}")
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
            HeaderBarBackArrowAdd("Amigos", navController, addOnClick = handleAddOnClick, backOnClick = { navController.popBackStack() })

            if (vm._loading.value) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(36.dp)
                            .padding(end = 8.dp),
                        color = Color.Red
                    )
                }

            } else {
                LazyColumn(modifier = Modifier.fillMaxHeight(0.7f)) {
                    items(vm.users) {
                        FriendInfoRow(it.nombrecompleto, getRank(it.points), it.id, navController)
                    }
                }
            }
            UserBottomMenu(navController = navController)
            
        }

    }
}

@Composable
fun FriendInfoRow(name: String, rank:String, id:Int?, navController: NavController){

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Column(modifier = Modifier
            ,
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center) {
            Text(text = name)
            Text(text = rank)
        }
        IconButton(onClick = {
            navController.navigate("rutas_friend_profile/${id}")
        }) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "ViewUser"
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