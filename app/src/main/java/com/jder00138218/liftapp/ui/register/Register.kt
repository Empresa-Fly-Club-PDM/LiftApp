package com.jder00138218.liftapp.ui.register

import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.navigation.Rutas
import com.jder00138218.liftapp.ui.register.viewmodel.RegisterViewModel


@Composable
fun RegisterScreen(navController: NavHostController) {

    val registerViewModel: RegisterViewModel = viewModel(
        factory = RegisterViewModel.Factory
    )

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
                    .fillMaxHeight(0.9f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FieldsRegister(registerViewModel, navController)
            }

        }

    }
}


@Composable
fun FieldsRegister(registerViewModel: RegisterViewModel, navController: NavHostController) {
    FieldDetaile("Nombre completo", registerViewModel)
    Spacer(modifier = Modifier.padding(2.dp))
    FieldDetaile("Correo", registerViewModel)
    Spacer(modifier = Modifier.padding(2.dp))
    Fieldpassword("Contraseña", registerViewModel)
    Spacer(modifier = Modifier.padding(2.dp))
    Fieldpassword("Verificar contraseña", registerViewModel)
    Spacer(modifier = Modifier.padding(2.dp))
    FieldDetaile("Genero", registerViewModel)
    Spacer(modifier = Modifier.padding(2.dp))
    FieldDetaile("Fecha de nacimiento", registerViewModel)
    Spacer(modifier = Modifier.padding(2.dp))
    GroupPE(registerViewModel)
    Spacer(modifier = Modifier.padding(2.dp))
    ButtonsDetaile(registerViewModel, navController)
    Spacer(modifier = Modifier.padding(2.dp))
    Text(text = "Por favor revisar los datos", color = Color.Red)

}

@Composable
fun ButtonsDetaile(viewModel: RegisterViewModel, navController: NavHostController) {

    Row(modifier = Modifier.padding(8.dp)) {
        Button(
            onClick = {
                viewModel.onRegister()
                handleUiStatus(viewModel, navController)
            }, modifier = Modifier
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
fun FieldDetaile(name: String, viewModel: RegisterViewModel) {

    var namelUser by remember { mutableStateOf(viewModel.email) }
    var genreUser by remember { mutableStateOf(viewModel.genre) }
    var dateUser by remember { mutableStateOf(viewModel.date) }
    var emailUser by remember { mutableStateOf(viewModel.email) }

    var colorPH = Color(R.color.gray_text)
    var iconId = R.drawable.icon_message
    var value = ""
    var validTextfield = true;
    var type = KeyboardType.Text
    var valueChange: ((String) -> Unit)? = null


    if (name == "Nombre completo") {
        colorPH = Color.Red
        iconId = R.drawable.profile
        value = namelUser
        valueChange = { newValue ->
            namelUser = newValue
            viewModel.name = newValue
        }
    }

    if (name == "Correo") {
        value = emailUser
        type = KeyboardType.Text
        valueChange = { newValue ->
            emailUser = newValue
            viewModel.email = newValue
        }
    }

    if (name == "Genero") {
        iconId = R.drawable.user
        value = genreUser
        valueChange = { newValue ->
            genreUser = newValue
            viewModel.genre = newValue
        }
    }

    if (name == "Fecha de nacimiento") {
        iconId = R.drawable.calendar
        value = dateUser
        validTextfield = false
        valueChange = { newValue ->
            dateUser = newValue
            viewModel.date = newValue
        }
    }


    OutlinedTextField(
        value = value,
        onValueChange = valueChange ?: {},
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
            keyboardType = type,
            imeAction = ImeAction.Next // Acción IME cuando se presiona la tecla Enter
        )
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Fieldpassword(name: String, viewModel: RegisterViewModel) {

    var passwordUser by remember { mutableStateOf(viewModel.password) }
    var isVisible by remember { mutableStateOf(viewModel.isVisiblePaswd) }
    var value = ""
    var valueChange: ((String) -> Unit)? = null
    var colorId = colorResource(id = R.color.gray_text)

    if (name != "Contraseña") {
        colorId = Color.Red
        value = passwordUser
        valueChange = { newValue ->
            passwordUser = newValue
            viewModel.password = newValue
        }
    }

    if (name != "Verificar contraseña") {
        colorId = Color.Red
        value = passwordUser
        valueChange = { newValue ->
            passwordUser = newValue
            viewModel.password = newValue
        }
    }

    val visualTransformation =
        if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

    OutlinedTextField(
        value = value,
        onValueChange = valueChange ?: {},
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
                    .clickable { isVisible = !isVisible },
                painter = painterResource(id = R.drawable.icon_hide),
                contentDescription = "Hide Icon",
                tint = colorResource(id = R.color.gray_text)
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        visualTransformation = visualTransformation
    )
}

@Composable
fun GroupPE(viewModel: RegisterViewModel) {

    FieldDetaileWB(name = "Peso", viewModel)
    Spacer(modifier = Modifier.padding(2.dp))
    FieldDetaileWB(name = "Estatura", viewModel)

}

// TODO -> FIX FIELDS WITH NUMBERS
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldDetaileWB(name: String, viewModel: RegisterViewModel) {
    var iconId = R.drawable.icon_message
    var textB = ""
    var weigthUser by remember { mutableStateOf(viewModel.weigth.toString()) }
    var heigthUser by remember { mutableStateOf(viewModel.heigth.toString()) }
    var value = ""
    var type = KeyboardType.Number
    var valueChange: ((String) -> Unit)? = null

    if (name == "Peso") {
        iconId = R.drawable.swap
        textB = "CM"
        value = weigthUser
        valueChange = { newValue ->
            weigthUser = newValue
            viewModel.weigth = newValue.toDoubleOrNull() ?: 0.0
        }
    }

    if (name == "Estatura") {
        iconId = R.drawable.swap
        textB = "CM"
        type = KeyboardType.Number
        value = heigthUser
        valueChange = { newValue ->
            heigthUser = newValue
            viewModel.heigth = newValue.toDoubleOrNull() ?: 0.0
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = { newValue ->
                valueChange?.invoke(newValue)
                value = newValue
            },
            modifier = Modifier
                .width(280.dp)
                .clip(RoundedCornerShape(4.dp))
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.field)
                )
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
            keyboardOptions = KeyboardOptions(
                keyboardType = type,
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


fun handleUiStatus(
    viewModel: RegisterViewModel,
    navController: NavHostController,
) {
    val status = viewModel.status.value

    when (status) {

        is RegisterUiStatus.Error -> {
            Log.d("tag", "Error")
            // TODO() -> Toast.makeText(requireContext(), "An error has occurred", Toast.LENGTH_SHORT).show()
        }

        is RegisterUiStatus.ErrorWithMessage -> {
            //  TODO() -> Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()
            Log.d("tag", "Error with message")
        }

        is RegisterUiStatus.Success -> {
            viewModel.clearStatus()
            viewModel.clearData()
            navController.navigate(route = Rutas.Login.ruta)
        }

        else -> {}
    }
}

