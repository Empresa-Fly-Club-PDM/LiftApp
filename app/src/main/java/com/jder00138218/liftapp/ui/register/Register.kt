package com.jder00138218.liftapp.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jder00138218.liftapp.R

@Preview(name = "prevRegister", showBackground = true)
@Composable
fun prevRegister() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            Column( // 1
                Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxHeight(0.1f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Crear Cuenta",
                    color = Color.Black,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                )
            }


            Column( // 2
                Modifier
                    .align(Alignment.Center)
                    .fillMaxHeight(0.84f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FieldsRegister()
            }

            Column( // 3
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxHeight(0.06f)
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Menu() TODO -> ADD MENU
            }

        }

    }
}


@Composable
fun FieldsRegister() {
    FieldDetaile("Nombre completo")
    Spacer(modifier = Modifier.padding(2.dp))
    FieldDetaile("Correo")
    Spacer(modifier = Modifier.padding(2.dp))
    Fieldpassword("Contraseña")
    Spacer(modifier = Modifier.padding(2.dp))
    Fieldpassword("Verificar contraseña")
    Spacer(modifier = Modifier.padding(2.dp))
    FieldDetaile("Genero")
    Spacer(modifier = Modifier.padding(2.dp))
    FieldDetaile("Fecha de nacimiento")
    Spacer(modifier = Modifier.padding(2.dp))
    GroupPE()
    Spacer(modifier = Modifier.padding(2.dp))
    ButtonsDetaile()
    Spacer(modifier = Modifier.padding(2.dp))
    Text(text = "Por favor revisar los datos", color = Color.Red)

}

@Composable
fun ButtonsDetaile() {
    Row(modifier = Modifier.padding(8.dp)) {
        Button(
            onClick = { }, modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {

            Text(text = "Registrar")

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldDetaile(name: String) {
    var colorPH = Color(R.color.gray_text)
    var iconId = R.drawable.icon_message

    if (name == "Nombre completo") {
        colorPH = Color.Red
        //iconId = R.drawable.profile
    }

    if (name == "Genero") {
        iconId = R.drawable.user
    }

    if (name == "Fecha de nacimiento") {
        iconId = R.drawable.calendar
    }

    if (name == "Peso") {
        iconId = R.drawable.weight
    }

    if (name == "Estatura") {
        iconId = R.drawable.swap
    }

    OutlinedTextField(
        value = "",
        onValueChange = { },
        modifier = Modifier
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = {

            Text(text = name, color = colorPH)
        },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = iconId),
                contentDescription = "Icon field",
                tint = colorResource(id = R.color.gray_text)
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
fun Fieldpassword(name: String) {

    var colorId = colorResource(id = R.color.gray_text)
    if (name != "Contraseña")
        colorId = Color.Red

    OutlinedTextField(
        value = "",
        onValueChange = { },
        modifier = Modifier
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )// With padding show border color
            .background(colorResource(id = R.color.field)),
        placeholder = {

            Text(text = name, color = colorId)
        },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.icon_password),
                contentDescription = "Icon field",
                tint = colorResource(id = R.color.gray_text)
            )
        },
        trailingIcon = {

            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .clickable { },
                painter = painterResource(id = R.drawable.icon_hide),
                contentDescription = "Hide Icon",
                tint = colorResource(id = R.color.gray_text)
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
    )
}

@Composable
fun GroupPE() {

    FieldDetaileWB(name = "Peso")
    Spacer(modifier = Modifier.padding(2.dp))
    FieldDetaileWB(name = "Estatura")

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldDetaileWB(name: String) {
    var iconId = R.drawable.icon_message
    var textB = ""

    if (name == "Peso") {
        iconId = R.drawable.weight
        textB = "LB"
    }
    if (name == "Estatura") {
        iconId = R.drawable.swap
        textB = "CM"
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = "",
            onValueChange = { },
            modifier = Modifier
                .width(280.dp)
                .clip(RoundedCornerShape(4.dp))
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.field)
                )// With padding show border color
                .background(colorResource(id = R.color.field)),
            placeholder = {

                Text(text = name, color = colorResource(id = R.color.gray_text))
            },
            singleLine = true,
            maxLines = 1,
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = iconId),
                    contentDescription = "Icon field",
                    tint = colorResource(id = R.color.gray_text)
                )
            },
            trailingIcon = {

                Icon(
                    modifier = Modifier
                        .size(16.dp)
                        .clickable { },

                    painter = painterResource(id = R.drawable.icon_hide),
                    contentDescription = "Hide Icon",
                    tint = colorResource(id = R.color.gray_text)
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Button(
            onClick = { /* Acción del botón */ },
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .width(68.dp)
                .height(60.dp)
                .padding(1.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {
            Text(
                text = textB,
                color = Color.White,
                style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}


