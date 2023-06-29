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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.users.user.CustomInputField
import com.jder00138218.liftapp.ui.users.user.CustomSelectField
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowCheck
import com.jder00138218.liftapp.ui.users.user.UserBottomMenu
import java.sql.Time

@Composable
fun CreateRoutine(navController: NavController){
    val context = LocalContext.current
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween) {
                HeaderBarBackArrowCheck(title = "Crear rutina")

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween) {
                    CustomInputField(hint = "Nombre de la rutina")
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomSelectField()
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomInputField(hint = "Tiempo objetivo")
                    Spacer(modifier = Modifier.height(8.dp))
                    CustomInputField(hint = "Tag")
                    Spacer(modifier = Modifier.height(8.dp))
                    TimeSelector()
                }

                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp), onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                        id = R.color.buttonGren
                    ), contentColor = Color.White)) {
                        Text(text = "Confirmar")
                    }

                UserBottomMenu(navController)
            }

            Button(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp), onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = colorResource(
                id = R.color.buttonGren
            ), contentColor = Color.White)) {
                Text(text = "Confirmar")
            }

            UserBottomMenu(navController)
        }
    }
}

@Composable
fun TimeSelector(){
    Row(modifier = Modifier
        .fillMaxWidth()) {
        CustomTimeInputField(hint = "Horas")
        CustomTimeInputField(hint = "Minutos")
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTimeInputField(hint: String){
    OutlinedTextField(
        value = "",
        onValueChange = { },
        modifier = Modifier
            .width(175.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = hint, color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next // Acción IME cuando se presiona la tecla Enter
        )
    )
}

