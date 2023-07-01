package com.jder00138218.liftapp.ui.users.user.routinesmenu

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jder00138218.liftapp.LiftAppApplication
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.network.dto.routine.routine
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.VerifiedExerciseView.CardExerciseVerify
import com.jder00138218.liftapp.ui.users.admin.userManager.AdminManagement.viewmodel.AdminManagementViewModel
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowAdd
import com.jder00138218.liftapp.ui.users.user.SearchBar
import com.jder00138218.liftapp.ui.users.user.UserBottomMenu
import com.jder00138218.liftapp.ui.users.user.routinesmenu.viewmodel.RoutineMenuViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun RoutinesMenu(navController: NavController){
    val vm: RoutineMenuViewModel = viewModel(
        factory = RoutineMenuViewModel.Factory
    )
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current
    val app = context.applicationContext as LiftAppApplication

    LaunchedEffect(text) {
        val currentText = text
        delay(500) // Add a short delay before executing the search
        if (currentText == text) { // Ensure the text hasn't changed during the delay
            vm.getMyRoutines(text,app.getUserId())
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
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            HeaderBarBackArrowAdd(title = "Mis Rutinas", navController = navController, addOnClick = {handleAddOnClick()}, backOnClick = {handleBackOnClick()})
            OutlinedTextField(value = text, onValueChange = { newText: String ->
                text = newText
                vm.getMyRoutines(text,app.getUserId()) }, modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(
                    colorResource(id = R.color.field)
                )
                .border(width = 0.dp, color = Color.White),
                placeholder = { Text(text = "Buscar..", color = Color(R.color.gray_text)) },

                )
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
            ) {
                items(vm.routines) {
                    RoutineMenuItem(routine = it,navController)
                }
            }
            UserBottomMenu(navController)
        }
    }

}

@Composable
fun RoutineMenuItem(routine:routine,navController: NavController){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .height(72.dp)
        .clickable(onClick = {navController.navigate("rutas_start_routine/${routine.id}")})
    )

    { Box(modifier = Modifier.background(colorResource(id = R.color.card))) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = routine.name, style = TextStyle(fontSize = 20.sp, color = Color.Black))
            Button(modifier = Modifier , onClick = {
                Log.d("SentData",routine.id.toString())
                navController.navigate(route = "ruta_user_routine_detail/${routine.id}")},
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                id = R.color.buttonRed
            ), contentColor = Color.White)) {
                Icon(
                    Icons.Default.Edit,
                    contentDescription = "to $routine.na routines"
                )
            }
        }
    }

    }
}