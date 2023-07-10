package com.jder00138218.liftapp.ui.users.admin.exerciseManager.updateexercise

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.network.dto.exercise.exercise
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.AdminHeaderBarBackArrowDumbell
import com.jder00138218.liftapp.ui.users.admin.Menu
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.CreateExercise.viewmodel.CreateExerciseViewmodel
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.ManageExerciseRequests.viewModel.DetailExerciseViewmodel
import com.jder00138218.liftapp.ui.users.admin.exerciseManager.updateexercise.viewmodel.AdminUpdateExerciseViewModel

@Composable
fun AdminUpdateExercise(navController: NavHostController) {
    val navBackStackEntry = navController.currentBackStackEntry
    val exerciseid = navBackStackEntry?.arguments?.getInt("id")
    val adminUpdateExerciseViewModel: AdminUpdateExerciseViewModel = viewModel(
        factory = AdminUpdateExerciseViewModel.Factory
    )
    if(exerciseid!=0){
        adminUpdateExerciseViewModel.getDetailExercise(exerciseid)
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
            AdminHeaderBarBackArrowDumbell(title = "Detalle de ejercicio", navController = navController, backOnClick = {navController.popBackStack()})
            FieldsDetaileCreate(navController,adminUpdateExerciseViewModel)
            ButtonsUpdate(exerciseid,adminUpdateExerciseViewModel,navController)
            Menu(navController)
        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldsDetaileCreate(navController: NavHostController,adminUpdateExerciseViewModel: AdminUpdateExerciseViewModel) {
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {
        FieldName(adminUpdateExerciseViewModel)
        Spacer(modifier = Modifier.padding(2.dp))
        FieldMuscle(adminUpdateExerciseViewModel)
        Spacer(modifier = Modifier.padding(2.dp))
        FieldType(adminUpdateExerciseViewModel)
        Spacer(modifier = Modifier.padding(2.dp))
        FieldDifficulty(adminUpdateExerciseViewModel)
        Spacer(modifier = Modifier.padding(2.dp))
        FieldDescription(adminUpdateExerciseViewModel)
        Spacer(modifier = Modifier.padding(2.dp))
        FieldSets(adminUpdateExerciseViewModel)
        Spacer(modifier = Modifier.padding(2.dp))
        FieldReps(adminUpdateExerciseViewModel)
        Spacer(modifier = Modifier.padding(8.dp))
    }

}

@Composable
fun ButtonsUpdate(id: Int?, adminUpdateExerciseViewModel: AdminUpdateExerciseViewModel, navController: NavHostController) {
    val context = LocalContext.current
    Row() {
        Button(
            onClick = {
                adminUpdateExerciseViewModel.isLoadingUpdate.value = true
                      adminUpdateExerciseViewModel.onUpdate(navController,context,id)
            }, modifier = Modifier
                .height(60.dp)
                .width(175.dp)
                .fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.buttonGray)
            )
        ) {
            if (adminUpdateExerciseViewModel.isLoadingUpdate.value) {
                // Show loading animation when isLoading is true
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp)
                ,color = Color.White

                )
            } else {
                Text(text = " Editar")
            }
        }

        Button(
            onClick = {
                adminUpdateExerciseViewModel.isLoadingDelete.value = true
                adminUpdateExerciseViewModel.deleteExercise(id, navController,context)
            }, modifier = Modifier
                .height(60.dp)
                .width(175.dp)
                .fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {
            if (adminUpdateExerciseViewModel.isLoadingDelete.value) {
                // Show loading animation when isLoading is true
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp),
                    color = Color.White
                )
            } else {
                Text(text = "Eliminar")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldName(adminUpdateExerciseViewModel: AdminUpdateExerciseViewModel) {
    OutlinedTextField(
        value = adminUpdateExerciseViewModel._name,
        onValueChange = {newValue ->
            adminUpdateExerciseViewModel._name= newValue
        },
        modifier = Modifier
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = "Nombre del Ejercicio", color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.pesa),
                contentDescription = "Icon field"
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
fun FieldMuscle(adminUpdateExerciseViewModel: AdminUpdateExerciseViewModel) {
    OutlinedTextField(
        value = adminUpdateExerciseViewModel._muscle,
        onValueChange = { newValue ->
            adminUpdateExerciseViewModel._muscle= newValue
        },
        modifier = Modifier
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = "Músculo trabajado", color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.pesa),
                contentDescription = "Icon field"
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
fun FieldType(adminUpdateExerciseViewModel: AdminUpdateExerciseViewModel){
    Log.d("recievedData",adminUpdateExerciseViewModel._type)

    var isExpanded by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center) {
        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded = it}) {
            TextField(value = adminUpdateExerciseViewModel._type, onValueChange = {

            }, readOnly = true, trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            }, modifier = Modifier
                .menuAnchor()
                .width(350.dp)
                .clip(RoundedCornerShape(4.dp))
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.field)
                )// With padding show border color
                .background(colorResource(id = R.color.field)),
                colors = TextFieldDefaults.textFieldColors(containerColor = colorResource(id = R.color.field)) ,
                placeholder = { Text(text ="Tipo de estimulo", color = Color(R.color.gray_text)) },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.pesa),
                        contentDescription = "Icon field"
                    )
                })

            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                DropdownMenuItem(text = { Text(text = "Hipertrofia") },
                    onClick = {
                        isExpanded = false
                        adminUpdateExerciseViewModel._type= "Hipertrofia"
                    })
                DropdownMenuItem(text = { Text(text = "Estres") },
                    onClick = {
                        isExpanded = false
                        adminUpdateExerciseViewModel._type= "Estres"
                    })
                DropdownMenuItem(text = { Text(text = "Fuerza") },
                    onClick = {
                        isExpanded = false
                        adminUpdateExerciseViewModel._type= "Fuerza"
                    })
            }
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldDifficulty(adminUpdateExerciseViewModel: AdminUpdateExerciseViewModel){

    var isExpanded by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center) {
        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded = it}) {
            TextField(value = adminUpdateExerciseViewModel._difficulty, onValueChange = {
            }, readOnly = true, trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            }, modifier = Modifier
                .menuAnchor()
                .width(350.dp)
                .clip(RoundedCornerShape(4.dp))
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.field)
                )// With padding show border color
                .background(colorResource(id = R.color.field)),
                colors = TextFieldDefaults.textFieldColors(containerColor = colorResource(id = R.color.field)) ,
                placeholder = { Text(text = "Dificultad", color = Color(R.color.gray_text)) },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.pesa),
                        contentDescription = "Icon field"
                    )
                })

            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                DropdownMenuItem(text = { Text(text = "Bajo") },
                    onClick = {
                        isExpanded = false
                        adminUpdateExerciseViewModel._difficulty = "Bajo"
                    })
                DropdownMenuItem(text = { Text(text = "Medio") },
                    onClick = {
                        isExpanded = false
                        adminUpdateExerciseViewModel._difficulty = "Medio"
                    })
                DropdownMenuItem(text = { Text(text = "Alto") },
                    onClick = {
                        isExpanded = false
                        adminUpdateExerciseViewModel._difficulty = "Alto"
                    })
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldDescription(adminUpdateExerciseViewModel: AdminUpdateExerciseViewModel) {
    OutlinedTextField(
        value = adminUpdateExerciseViewModel._description,
        onValueChange = {newValue->
            adminUpdateExerciseViewModel._description=newValue
        },
        modifier = Modifier
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = "Descripción", color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.pesa),
                contentDescription = "Icon field"
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
fun FieldSets(adminUpdateExerciseViewModel: AdminUpdateExerciseViewModel) {
    OutlinedTextField(
        value = adminUpdateExerciseViewModel._sets,
        onValueChange = {newValue->
            adminUpdateExerciseViewModel._sets=newValue
        },
        modifier = Modifier
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = "Sets", color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.pesa),
                contentDescription = "Icon field"
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next // Acción IME cuando se presiona la tecla Enter
        )
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldReps(adminUpdateExerciseViewModel: AdminUpdateExerciseViewModel) {
    OutlinedTextField(
        value = adminUpdateExerciseViewModel._reps,
        onValueChange = {newValue->
            adminUpdateExerciseViewModel._reps=newValue
        },
        modifier = Modifier
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = "Repeticiones", color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.pesa),
                contentDescription = "Icon field"
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next // Acción IME cuando se presiona la tecla Enter
        )
    )
}
