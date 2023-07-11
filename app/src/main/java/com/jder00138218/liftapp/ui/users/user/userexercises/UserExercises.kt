package com.jder00138218.liftapp.ui.users.user.userexercises

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jder00138218.liftapp.LiftAppApplication
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowAdd
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowCheck
import com.jder00138218.liftapp.ui.users.user.UserBottomMenu
import com.jder00138218.liftapp.ui.users.user.addexercisetoroutine.CardExercise
import com.jder00138218.liftapp.ui.users.user.addexercisetoroutine.viewmodel.AddExerciseToRoutineViewModel
import com.jder00138218.liftapp.ui.users.user.userexercises.viewmodel.UserExercisesViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserExercises(navController: NavController){
    val vm: UserExercisesViewModel = viewModel(
        factory = UserExercisesViewModel.Factory
    )
    var text by remember { mutableStateOf("") }
    val context = LocalContext.current
    val app = context.applicationContext as LiftAppApplication

    LaunchedEffect(text) {
        val currentText = text
        delay(500) // Add a short delay before executing the search
        if (currentText == text) { // Ensure the text hasn't changed during the delay
            vm.getPersonalExercises(text,app.getUserId())
        }
    }

    val handleAddOnClick = {
        navController.popBackStack()
    }
    val handleBackOnClick = {
        navController.popBackStack()
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(8.dp)) {

        Column(modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween) {
            HeaderBarBackArrowAdd(title = stringResource(R.string.mis_ejercicios), navController = navController, addOnClick = {navController.navigate(route = Rutas.UserAddExercises.ruta)}, backOnClick = {navController.popBackStack()})
            OutlinedTextField(value = text, onValueChange = { newText: String ->
                text = newText
                vm.getPersonalExercises(text,app.getUserId())}, modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(
                    colorResource(id = R.color.field)
                )
                .border(width = 0.dp, color = Color.White),
                placeholder = { Text(text = stringResource(R.string.buscar), color = Color(R.color.gray_text)) },
            )
            if (vm._loading.value) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f),
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
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f)
                ) {
                    items(vm.exercises) {
                        CardUserExercise(it, navController, vm)
                    }
                }
            }
            UserBottomMenu(navController)
        }
    }
}

@Composable
fun CardUserExercise(exercise: exercise, navController: NavController, addExerciseToRoutineViewModel: UserExercisesViewModel) {
    val context = LocalContext.current
    Card( // this
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {navController.navigate(route = "rutas_user_update_exercise/${exercise.id}")
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
                        painter = painterResource(id = R.drawable.pesa),
                        contentDescription = stringResource(R.string.verify_icon),
                        modifier = Modifier.size(20.dp)
                    )
                }


                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.Start
                ) {
                    ItemUserEx(exercise)
                    ItemUserExRight(exercise)
                }

            }
        }


    }
}


@Composable
fun ItemUserEx(exercise: exercise) {
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
fun ItemUserExRight(exercise: exercise) {
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
