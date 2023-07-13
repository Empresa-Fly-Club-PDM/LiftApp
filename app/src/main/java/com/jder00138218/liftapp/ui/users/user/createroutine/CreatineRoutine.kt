package com.jder00138218.liftapp.ui.users.user.createroutine

import android.app.Dialog
import android.app.TimePickerDialog
import android.widget.TimePicker
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.LiftAppApplication
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.CreateExercise.viewmodel.CreateExerciseViewmodel
import com.jder00138218.liftapp.ui.users.user.CustomInputField
import com.jder00138218.liftapp.ui.users.user.CustomSelectField
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowCheck
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowDumbell
import com.jder00138218.liftapp.ui.users.user.UserBottomMenu
import com.jder00138218.liftapp.ui.users.user.createroutine.viewmodel.CreateRoutineViewModel
import java.sql.Time

@Composable
fun CreateRoutine(navController: NavHostController){
    val createRoutineViewModel: CreateRoutineViewModel = viewModel(
        factory = CreateRoutineViewModel.Factory
    )
    val context = LocalContext.current
    val app = context.applicationContext as LiftAppApplication

    val handleAddOnClick = {
        navController.navigate(route = Rutas.AdminCreateExercise.ruta)
    }
    val handleBackOnClick = {
        navController.popBackStack()
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween) {

            HeaderBarBackArrowDumbell(title = stringResource(R.string.crear_rutina), navController, backOnClick = {handleBackOnClick()})

            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
                .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween) {
                NameInputField(createRoutineViewModel)
                Spacer(modifier = Modifier.height(2.dp))
                DifficultyInputField(createRoutineViewModel)
                Spacer(modifier = Modifier.height(2.dp))
                TagInputField(createRoutineViewModel)
                Spacer(modifier = Modifier.height(2.dp))
                CustomHourInputField(createRoutineViewModel)
                Spacer(modifier = Modifier.height(2.dp))
                CustomMinuteInputField(createRoutineViewModel)
                Spacer(modifier = Modifier.height(8.dp))

                Button(modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp), onClick = {createRoutineViewModel.onCreate(navController, context,app.getUserId()) }, colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                    id = R.color.buttonGren
                ), contentColor = Color.White)) {

                    if (createRoutineViewModel._loading.value) {
                        // Show loading animation when isLoading is true
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(36.dp)
                                .padding(end = 8.dp),
                            color = Color.White
                        )
                    } else {

                        Text(text = stringResource(id = R.string.confirmar))
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }



            UserBottomMenu(navController)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomHourInputField(viewmodel: CreateRoutineViewModel){
    var hour by remember { mutableStateOf(viewmodel.hour) }
    OutlinedTextField(
        value = hour,
        onValueChange = {newValue ->
            hour = newValue
            viewmodel.hour= newValue },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = stringResource(R.string.horas), color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next // Acción IME cuando se presiona la tecla Enter
        )
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomMinuteInputField(viewmodel: CreateRoutineViewModel){
    var minutes by remember { mutableStateOf(viewmodel.minute) }
    OutlinedTextField(
        value = minutes,
        onValueChange = {newValue ->
            minutes = newValue
            viewmodel.minute= newValue },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = stringResource(R.string.minutos), color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next // Acción IME cuando se presiona la tecla Enter
        )
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameInputField(viewmodel:CreateRoutineViewModel){
    var name by remember { mutableStateOf(viewmodel.name) }

    OutlinedTextField(
        value = name,
        onValueChange = { newValue ->
            name = newValue
            viewmodel.name= newValue
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = stringResource(R.string.nombre_de_la_rutina), color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.pesa),
                contentDescription = stringResource(id = R.string.icon_field)
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next // Acción IME cuando se presiona la tecla Enter
        )
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DifficultyInputField(viewmodel: CreateRoutineViewModel){

    var isExpanded by remember {
        mutableStateOf(false)
    }
    var difficulty by remember {
        mutableStateOf(viewmodel.difficulty)
    }

    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded = it}) {
            TextField(value = difficulty, onValueChange = {
                    newValue ->
                difficulty = newValue
                viewmodel.difficulty= newValue
            }, readOnly = true, trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            }, modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.field)
                )// With padding show border color
                .background(colorResource(id = R.color.field)),
                colors = TextFieldDefaults.textFieldColors(containerColor = colorResource(id = R.color.field)) ,
                placeholder = { Text(text = stringResource(id = R.string.dificultad), color = Color(R.color.gray_text)) },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.pesa),
                        contentDescription = stringResource(id = R.string.icon_field)
                    )
                })

            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                DropdownMenuItem(text = { Text(text = stringResource(id = R.string.bajo)) },
                    onClick = {
                        isExpanded = false
                        viewmodel.difficulty = "Bajo"
                        difficulty = viewmodel.difficulty
                    })
                DropdownMenuItem(text = { Text(text = stringResource(id = R.string.medio)) },
                    onClick = {
                        isExpanded = false
                        viewmodel.difficulty = "Medio"
                        difficulty = viewmodel.difficulty
                    })
                DropdownMenuItem(text = { Text(text = stringResource(id = R.string.alto)) },
                    onClick = {
                        isExpanded = false
                        viewmodel.difficulty = "Alto"
                        difficulty = viewmodel.difficulty

                    })
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagInputField(viewmodel:CreateRoutineViewModel){
    var tag by remember { mutableStateOf(viewmodel.tag) }

    OutlinedTextField(
        value = tag,
        onValueChange = { newValue ->
            tag = newValue
            viewmodel.tag= newValue
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = stringResource(R.string.tag), color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.pesa),
                contentDescription = stringResource(id = R.string.icon_field)
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next // Acción IME cuando se presiona la tecla Enter
        )
    )
}