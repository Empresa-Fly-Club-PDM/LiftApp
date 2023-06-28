package com.jder00138218.liftapp.ui.users.user.routineflow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.CardExercise
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowAdd
import com.jder00138218.liftapp.ui.users.user.UserBottomMenu

@Composable
fun StartRoutine(navController: NavController){

    val handleAddOnClick = {
        navController.navigate(route = Rutas.UserCreateRoutine.ruta)
    }
    val handleBackOnClick = {
        navController.navigate(route = Rutas.DashboardUser.ruta)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ){

        Column( modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
        )
        {
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f)) {
                HeaderBarBackArrowAdd("Ranking", navController, addOnClick = handleAddOnClick, backOnClick = handleBackOnClick)
            }

            Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.9f)) {
                /*LazyColumn(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)){
                    items(20) {
                        CardExercise()
                    }
                }*/

                Button(modifier = Modifier.fillMaxWidth() , onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                    id = R.color.buttonGren
                ), contentColor = Color.White)) {
                    Text(text = "Iniciar rutina")
                }
            }

            Column(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom) {
                UserBottomMenu(navController)
            }
        }
    }
}