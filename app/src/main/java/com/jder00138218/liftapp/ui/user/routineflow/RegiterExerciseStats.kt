package com.jder00138218.liftapp.ui.user.routineflow

import androidx.compose.foundation.background
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
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.user.CustomInputField
import com.jder00138218.liftapp.ui.user.CustomSelectField
import com.jder00138218.liftapp.ui.user.CustomTypeSelectField
import com.jder00138218.liftapp.ui.user.HeaderBarBackArrowDumbell
import com.jder00138218.liftapp.ui.user.UserBottomMenu

@Preview
@Composable
fun RegisterExerciseStats(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f)) {
                HeaderBarBackArrowDumbell(title = "Detalles del ejercicio")
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f),
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Column(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    CustomInputField(hint = "Nombre del ejercicio")
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomInputField(hint = "Peso")
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomInputField(hint = "Repeticiones")
                }

                Button(modifier = Modifier.fillMaxWidth() , onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                    id = R.color.buttonGren
                ), contentColor = Color.White)) {
                    Text(text = "Registrar peso")
                }
            }

            Column(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom) {
                UserBottomMenu()
            }
        }
    }
}