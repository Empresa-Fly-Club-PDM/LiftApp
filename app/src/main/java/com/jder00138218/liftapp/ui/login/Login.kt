package com.jder00138218.liftapp.ui.login


import android.util.Log
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.RetrofitApplication
import com.jder00138218.liftapp.ui.login.viewmodel.LoginViewModel
import com.jder00138218.liftapp.ui.navigation.Rutas


@Composable
fun LoginScreen(navController: NavHostController) {

    val loginViewModel: LoginViewModel = viewModel(
        factory = LoginViewModel.Factory
    )

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
    // TODO -> FIX VALIDATION STATUS
    Button(
        onClick = {
            viewModel.onLogin()
            // testNav(navController)
            handleUiStatus(viewModel, navController)
        }, modifier = modifier
            .height(60.dp)
            .width(300.dp)
            .fillMaxWidth(), colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red
        )
    ) {
        Row() {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.icon_login),
                contentDescription = "Icon login"
            )
            Text(text = " Ingresar")
        }

    }
}

fun testNav(navController: NavHostController) {
    navController.navigate(route = Rutas.DashboardAdmin.ruta)
}


fun handleUiStatus(viewModel: LoginViewModel, navController: NavHostController) {
    val status = viewModel.status.value

    Log.d("tag", "HandleUIState...")
    val app = RetrofitApplication()
    Log.d("Tag status on function", status.toString())


    when (status) {

        is LoginUiStatus.Error -> {
            Log.d("tag", "Error")
            // TODO() -> Toast.makeText(requireContext(), "An error has occurred", Toast.LENGTH_SHORT).show()
        }

        is LoginUiStatus.ErrorWithMessage -> {
            //  TODO() -> Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()
            Log.d("tag", "Error with message")
        }

        is LoginUiStatus.Success -> {
            Log.d("tag", "Done 2")
            viewModel.clearStatus()
            viewModel.clearData()
            app.saveAuthToken(status.token)
            Log.d("tag TOKEN", status.token) // TODO -> VALIDATE USER
            navController.navigate(route = Rutas.DashboardAdmin.ruta)
        }

        else -> {}
    }
}


@Composable
fun Register(modifier: Modifier, navController: NavHostController) {
    Row(modifier = modifier) {
        Text(text = "¿Aun no tienes cuenta?")
        Text(
            text = " Registrate",
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
            text = "Or",
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
        contentDescription = "Image of lift app",
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
        placeholder = { Text(text = "Email", color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.icon_message),
                contentDescription = "Icon Email"
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
        placeholder = { Text(text = "Password", color = Color(R.color.gray_text)) },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .size(16.dp),
                painter = painterResource(id = R.drawable.icon_password),
                contentDescription = "Icon Password",
            )
        },
        trailingIcon = {

            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .clickable { isVisible = !isVisible },
                painter = painterResource(id = R.drawable.icon_hide),
                contentDescription = "Hide Icon"
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







