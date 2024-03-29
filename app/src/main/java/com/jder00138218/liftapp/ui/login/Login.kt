package com.jder00138218.liftapp.ui.login


import SessionManager
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.login.viewmodel.LoginViewModel
import com.jder00138218.liftapp.ui.navigation.Rutas

import org.json.JSONObject

import java.util.Base64


@Composable
fun LoginScreen(navController: NavHostController) {

    val loginViewModel: LoginViewModel = viewModel(
        factory = LoginViewModel.Factory
    )

    val context = LocalContext.current

    val sessionManager = remember { SessionManager(context) }
    val savedEmail = sessionManager.email
    val savedPassword = sessionManager.password

    if (savedEmail != null && savedPassword != null) {
        loginViewModel.email = savedEmail
        loginViewModel.password = savedPassword
        loginViewModel.onLogin(navController, LocalContext.current)
        sessionManager.clearSession()
    }
    BackHandler(enabled = true) {
        navController.navigate(Rutas.Login.ruta)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Login(Modifier.align(Alignment.Center), loginViewModel, navController)
    }
}

@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel, navController: NavHostController) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(8.dp))
            FieldEmail(viewModel)
            Spacer(modifier = Modifier.padding(8.dp))
            FieldPassword(viewModel)
            Spacer(modifier = Modifier.padding(8.dp))
            ForgotPassword(Modifier.align(Alignment.CenterHorizontally), navController)
            Spacer(modifier = Modifier.padding(8.dp))
            SingIn(viewModel, Modifier.align(Alignment.CenterHorizontally), navController)
            Spacer(modifier = Modifier.padding(36.dp))
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OrSpacer(Modifier.align(Alignment.CenterHorizontally))
            Register(Modifier.align(Alignment.CenterHorizontally), navController)
        }
    }
}


@Composable
fun SingIn(viewModel: LoginViewModel, modifier: Modifier, navController: NavHostController) {
    val context = LocalContext.current

    Button(
        onClick = {
            viewModel._loading.value=true
            viewModel.onLogin(navController, context)
        }, modifier = modifier
            .height(60.dp)
            .width(300.dp)
            .fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Color.Red))
    {
        Row {
            if (viewModel._loading.value) {
                // Show loading animation when isLoading is true
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(36.dp)
                        .padding(end = 8.dp),
                    color = Color.White
                )
            } else {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.icon_login),
                    contentDescription = "Icon login"
                )

                Text(text = stringResource(R.string.ingresar_txg))
            }
        }

    }
}

fun testNav(navController: NavHostController) {
    navController.navigate(route = Rutas.DashboardAdmin.ruta)
}


fun getRoleFromTokenPayload(payloadJson: String): String? {
    val jsonObject = JSONObject(payloadJson)
    return jsonObject.getString("roles")
}

fun getIdFromTokenPayload(payloadJson: String):Int?{
    val jsonObject = JSONObject(payloadJson)
    val userData = jsonObject.getString("sub")
    val userid = userData.split(",")[0].toIntOrNull()
    return userid
}


fun decodeHS512TokenWithoutVerification(token: String): String {

    val base64UrlEncodedPayload = token.split(".")[1] // Obtiene la sección de carga útil del token
    val payloadJson = String(
        Base64.getUrlDecoder().decode(base64UrlEncodedPayload)
    ) // Decodifica la carga útil en formato JSON

    return payloadJson // Devuelve la carga útil del token
}


@Composable
fun Register(modifier: Modifier, navController: NavHostController) {
    Row(modifier = modifier) {
        Text(text = stringResource(R.string.aun_no_tienes_cuenta))
        Text(
            text = stringResource(R.string.registrate),
            color = Color.Red,
            modifier = Modifier.clickable { navController.navigate(route = Rutas.Register.ruta) })
        Spacer(modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun OrSpacer(modifier: Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            color = Color.Black,
            modifier = Modifier.weight(1f),
            thickness = 1.dp
        )
        Text(
            text = stringResource(R.string.o),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
        Divider(
            color = Color.Black,
            modifier = Modifier.weight(1f),
            thickness = 1.dp
        )
    }
}


@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logoliftapp),
        contentDescription = stringResource(R.string.image_of_lift_app),
        modifier = modifier
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldEmail(viewModel: LoginViewModel) {
    var emailUser by remember { mutableStateOf(viewModel.email) }
    OutlinedTextField(
        value = emailUser,
        onValueChange = { newValue ->
            emailUser = newValue
            viewModel.email = newValue
        },
        modifier = Modifier
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(colorResource(id = R.color.field))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            )
            .background(colorResource(id = R.color.field)),
        placeholder = { Text(text = stringResource(R.string.email), color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.icon_message),
                contentDescription = stringResource(R.string.icon_email)
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = androidx.compose.ui.text.input.ImeAction.Next // Acción IME cuando se presiona la tecla Enter
        ),

        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldPassword(viewModel: LoginViewModel) {
    var password by remember { mutableStateOf(viewModel.password) }
    var isVisible by remember { mutableStateOf(viewModel.isVisiblePaswd) }

    val visualTransformation =
        if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

    OutlinedTextField(
        value = password,
        onValueChange = { newValue ->
            password = newValue
            viewModel.password = newValue
        },
        modifier = Modifier
            .width(350.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(colorResource(id = R.color.field))
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.field)
            ),
        placeholder = { Text(text = stringResource(R.string.password), color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .size(16.dp),
                painter = painterResource(id = R.drawable.icon_password),
                contentDescription = stringResource(R.string.icon_password),
            )
        },
        trailingIcon = {

            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .clickable { isVisible = !isVisible },
                painter = painterResource(id = R.drawable.icon_hide),
                contentDescription = stringResource(R.string.hide_icon)
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = androidx.compose.ui.text.input.ImeAction.Next // Acción IME cuando se presiona la tecla Enter
        ),
        visualTransformation = visualTransformation
    )
}

@Composable
fun ForgotPassword(modifier: Modifier, navController: NavHostController) {
    val text = stringResource(R.string.forgot_passw)

    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(textDecoration = TextDecoration.Underline)
            ) {
                append(text)
            }
        },
        modifier = modifier.clickable { navController.navigate(route = Rutas.ForgotPss.ruta) },
        color = Color(R.color.gray_text)
    )
}







