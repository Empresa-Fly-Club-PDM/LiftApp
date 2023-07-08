package com.jder00138218.liftapp.ui.users.admin.exerciseManager.VerifiedExerciseView

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.AdminHeaderBarBackArrowAdd
import com.jder00138218.liftapp.ui.users.admin.Menu
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.VerifiedExerciseView.viewmodel.VerifiedExercisesViewModel
import com.jder00138218.liftapp.ui.users.admin.viewmodel.DashboardAdminViewmodel

@Composable
fun VerifyExercises(navController: NavController) {
    val vm: VerifiedExercisesViewModel = viewModel(
        factory = VerifiedExercisesViewModel.Factory
    )
    LaunchedEffect(Unit, block = {
        vm.getVeriedEercises("")
    })
    val handleAddOnClick = {
        navController.navigate(route = Rutas.AdminCreateExercise.ruta)
    }
    val handleBackOnClick = {
        navController.navigate(route = Rutas.DashboardAdmin.ruta)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        Column(modifier = Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AdminHeaderBarBackArrowAdd(
                title = "Ejercicios Verificados",
                navController = navController,
                addOnClick = { handleAddOnClick() },
                backOnClick = { handleBackOnClick() })

            if (vm._loading.value) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
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
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                ) {
                    items(vm.exercises) { index ->
                        CardExerciseVerify(index, navController)
                    }
                }
            }

            Menu(navController)
        }

    }

}

// This is for Verify exercise
@Composable
fun CardExerciseVerify(exercise: exercise, navController: NavController) {
    Card( // this
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable{navController.navigate(route = "ruta_admin_update_exercise/" + exercise.id)
            },
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.card)
        )
    ) {

        Box(
            modifier = Modifier
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.fillMaxWidth(1f)) {

                Row() {
                    Text(
                        text = exercise.name,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.shield_done),
                        contentDescription = "Verify Icon",
                        modifier = Modifier.size(20.dp)
                    )
                }


                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.Start
                ) {
                    ItemExVerify(exercise)
                    ItemExVerifyRight(exercise)
                }

            }
        }


    }
}


@Composable
fun ItemExVerify(exercise: exercise) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ), modifier = Modifier
            .padding(4.dp)
            .size(width = 160.dp, height = 60.dp)
    ) {
        Column(Modifier.padding(8.dp)) {
            Text(text = "Musculo", color = Color.Red)
            Text(text = exercise.muscle, color = Color(R.color.gray_text))
        }
    }

}

@Composable
fun ItemExVerifyRight(exercise: exercise) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ), modifier = Modifier
            .padding(4.dp)
            .size(width = 160.dp, height = 60.dp)
    ) {
        Column(Modifier.padding(8.dp)) {
            Text(text = exercise.type, color = Color.Red)
            Text(text = "${exercise.reps}x${exercise.sets}", color = Color(R.color.gray_text))
        }
    }

}
