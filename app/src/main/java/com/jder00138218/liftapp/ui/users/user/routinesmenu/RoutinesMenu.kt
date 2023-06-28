package com.jder00138218.liftapp.ui.users.user.routinesmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.VerifiedExerciseView.CardExerciseVerify
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowAdd
import com.jder00138218.liftapp.ui.users.user.SearchBar
import com.jder00138218.liftapp.ui.users.user.UserBottomMenu

@Composable
fun RoutinesMenu(navController: NavController){

    val handleAddOnClick = {
        navController.navigate(route = Rutas.UserCreateRoutine.ruta)
    }
    val handleBackOnClick = {
        navController.navigate(route = Rutas.DashboardUser.ruta)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            HeaderBarBackArrowAdd(title = "Ejercicios Verificados", navController = navController, addOnClick = {handleAddOnClick()}, backOnClick = {handleBackOnClick()})
            SearchBar()
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
            ) {
                items(20) {
                    RoutineMenuItem(muscleGroup = "BAK")
                }
            }
            UserBottomMenu(navController)
        }
    }

}

@Composable
fun RoutineMenuItem(muscleGroup: String){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .height(72.dp))
    { Box(modifier = Modifier.background(colorResource(id = R.color.card))) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = muscleGroup, style = TextStyle(fontSize = 20.sp, color = Color.Black))
            Button(modifier = Modifier , onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                id = R.color.buttonRed
            ), contentColor = Color.White)) {
                Icon(
                    Icons.Default.ArrowForward,
                    contentDescription = "to $muscleGroup routines"
                )
            }
        }
    }

    }
}