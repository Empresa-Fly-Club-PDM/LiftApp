package com.jder00138218.liftapp.ui.users.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jder00138218.liftapp.R
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.ui.users.admin.viewmodel.DashboardAdminViewmodel

@Composable
fun DashboardAdminScreen(navController: NavController) {
    val vm: DashboardAdminViewmodel = viewModel(
        factory = DashboardAdminViewmodel.Factory
    )

    LaunchedEffect(Unit, block = {
        vm.getSolicitudes("")
    })
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = stringResource(R.string.solicitudes_de_verificacion),
                color = Color.Black,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            )
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
                LazyColumn(
                    Modifier
                        .fillMaxHeight(0.9f)
                        .fillMaxWidth()
                ) {
                    items(vm.exercises) { index ->
                        var url = "ruta_admin_exercise_details/" + index.id
                        ExerciseCardAdmin(index, url, navController)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

            Menu(navController)
        }

    }

}