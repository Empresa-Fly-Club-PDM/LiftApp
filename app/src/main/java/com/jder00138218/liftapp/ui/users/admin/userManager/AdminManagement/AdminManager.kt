package com.jder00138218.liftapp.ui.users.admin.userManager.AdminManagement

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.users.admin.AdminHeaderBarBackArrowAdd
import com.jder00138218.liftapp.ui.users.admin.Menu
import com.jder00138218.liftapp.ui.users.admin.userManager.AdminManagement.viewmodel.AdminManagementViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminManager(navController: NavController){

    val vm: AdminManagementViewModel = viewModel(
        factory = AdminManagementViewModel.Factory
    )
    var text by remember { mutableStateOf("") }

    LaunchedEffect(text) {
        val currentText = text
        delay(500) // Add a short delay before executing the search
        if (currentText == text) { // Ensure the text hasn't changed during the delay
            vm.getAllAdmins(text)
        }
    }

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
            AdminHeaderBarBackArrowAdd(title = stringResource(R.string.administradores), navController, addOnClick = {handleAddOnClick()}, backOnClick = {handleBackOnClick})
            OutlinedTextField(value = text, onValueChange = { newText: String ->
                text = newText
                vm.getAllAdmins(text)
            }, modifier = Modifier
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
                        .fillMaxHeight(0.8f)
                ) {
                    items(vm.users) {
                        AdminInfoRow(name = it.nombrecompleto, it.id, navController = navController)
                    }
                }
            }
            Menu(navController)
        }

    }
}

@Composable
fun AdminInfoRow(name: String, id:Int?, navController: NavController){

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
    horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = name)

        Button(
            onClick = {navController.navigate(route = "ruta_admin_update_admin/" + id) },
            modifier = Modifier,
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                id = R.color.buttonGray)
            )
        ) {
            Text(text = stringResource(R.string.detalle), color = Color.Black)
        }
    }
}

