package com.jder00138218.liftapp.ui.users.admin.userManager.AdminManagement

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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.AccountCard
import com.jder00138218.liftapp.ui.users.admin.AdminHeaderBarBackArrowAdd
import com.jder00138218.liftapp.ui.users.admin.AdminHeaderBarBackArrowDumbell
import com.jder00138218.liftapp.ui.users.admin.AdminProfileInfoRow
import com.jder00138218.liftapp.ui.users.admin.AdminSearchBar
import com.jder00138218.liftapp.ui.users.admin.LogoutCard
import com.jder00138218.liftapp.ui.users.admin.Menu
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.VerifiedExerciseView.CardExerciseVerify

@Composable
fun AdminManager(navController: NavController){

    val handleAddOnClick = {
        navController.navigate(route = Rutas.AdminCreateAdmin.ruta)
    }
    val handleBackOnClick = {
        navController.navigate(route = Rutas.AdminProfile.ruta)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
        .background(Color.White)
    ){

        Column(modifier = Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AdminHeaderBarBackArrowAdd(title = "Administradores", navController, addOnClick = {handleAddOnClick()}, backOnClick = {handleBackOnClick})
            AdminSearchBar()
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
            ) {
                items(2) { 
                    AdminInfoRow(name = "johnny", navController = navController)
                }
            }
            Menu(navController)
        }

    }
}

@Composable
fun AdminInfoRow(name: String, navController: NavController){

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
    horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = name)

        Button(
            onClick = {  },
            modifier = Modifier,
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                id = R.color.buttonGray)
            )
        ) {
            Text(text = "Detalle", color = Color.Black)
        }
    }
}

