package com.jder00138218.liftapp.ui.users.user.createroutine

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.users.user.CustomInputField
import com.jder00138218.liftapp.ui.users.user.CustomSelectField
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowCheck
import com.jder00138218.liftapp.ui.users.user.UserBottomMenu

@Preview
@Composable
fun CreateRoutine(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)) {
                    HeaderBarBackArrowCheck(title = "Crear rutina")
                }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f),
                    verticalArrangement = Arrangement.SpaceBetween
                ){
                    Column(modifier = Modifier
                        .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        CustomInputField(hint = "Nombre de la rutina")
                        Spacer(modifier = Modifier.height(8.dp))
                        CustomSelectField()
                        Spacer(modifier = Modifier.height(8.dp))
                        CustomInputField(hint = "Tiempo objetivo")
                        Spacer(modifier = Modifier.height(8.dp))
                        CustomInputField(hint = "Tag")
                    }

                    Button(modifier = Modifier.fillMaxWidth() , onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                        id = R.color.buttonGren
                    ), contentColor = Color.White)) {
                        Text(text = "Confirmar")
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