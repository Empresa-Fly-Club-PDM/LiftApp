package com.jder00138218.liftapp.ui.users.admin.exerciseManager.CreateExercise

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.jder00138218.liftapp.ui.users.admin.Menu


@Preview(name = "Create Exercise", showBackground = true)
@Composable
fun CreateExercise() {

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
                    text = "Crear Ejercicio",
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
                    .fillMaxHeight(0.76f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FieldsDetaileCreate()
            }

            Column( // 3
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxHeight(0.06f)
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Menu()
            }

        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldsDetaileCreate() {

    FieldDetaileCreate("Nombre del ejercicio")
    Spacer(modifier = Modifier.padding(2.dp))
    FieldDetaileCreate("Musculo")
    Spacer(modifier = Modifier.padding(2.dp))
    FieldDetaileCreate("Tipo")
    Spacer(modifier = Modifier.padding(2.dp))
    FieldDetaileCreate("Dificultad")
    Spacer(modifier = Modifier.padding(2.dp))
    FieldDetaileCreate("Descripcion")
    Spacer(modifier = Modifier.padding(2.dp))
    FieldDetaileCreate("Sets")
    Spacer(modifier = Modifier.padding(2.dp))
    FieldDetaileCreate("Repeticiones")
    Spacer(modifier = Modifier.padding(8.dp))
    ButtonsCreate()
}

@Composable
fun ButtonsCreate() {

        Button(
            onClick = { }, modifier = Modifier
                .height(60.dp)
                .width(175.dp)
                .fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.buttonGren)
            )
        ) {
            Text(text = " Confirmar")

        }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldDetaileCreate(name: String) {
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
        placeholder = { Text(text = name, color = Color(R.color.gray_text)) },
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