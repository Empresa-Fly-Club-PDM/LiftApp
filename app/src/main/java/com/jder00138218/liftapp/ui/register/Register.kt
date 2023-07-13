package com.jder00138218.liftapp.ui.register

import android.app.DatePickerDialog
import android.widget.DatePicker
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.register.viewmodel.RegisterViewModel
import com.jder00138218.liftapp.ui.users.user.HeaderBarBackArrowDumbell
import java.util.Calendar
import java.util.Date


@Composable
fun RegisterScreen(navController: NavHostController) {

    val registerViewModel: RegisterViewModel = viewModel(
        factory = RegisterViewModel.Factory
    )

    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
            Column( // 1
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.crear_cuenta),
                        color = Color.Black,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                    )
                    FieldsRegister(registerViewModel, navController)
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                    Button(onClick = {navController.popBackStack()}, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ), modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth(), elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 10.dp
                    )) {
                        Text(text = "Regresar a inicio", color = Color.Black)
                    }
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                }

            }
    }
}


@Composable
fun FieldsRegister(registerViewModel: RegisterViewModel, navController: NavHostController) {
    Spacer(modifier = Modifier.padding(16.dp))
    FieldDetaile(stringResource(R.string.nombre_completo), registerViewModel)
    Spacer(modifier = Modifier.padding(2.dp))
    FieldDetaile(stringResource(R.string.correo), registerViewModel)
    Spacer(modifier = Modifier.padding(2.dp))
    Fieldpassword(stringResource(R.string.contrase_a), registerViewModel)
    Spacer(modifier = Modifier.padding(2.dp))
    Fieldpassword(stringResource(R.string.verificar_contrase_a), registerViewModel)
    Spacer(modifier = Modifier.padding(2.dp))
    GenreDropDownMenu(registerViewModel)
    Spacer(modifier = Modifier.padding(2.dp))
    DateInputField(registerViewModel)
    Spacer(modifier = Modifier.padding(2.dp))
    GroupPE(registerViewModel)
    Spacer(modifier = Modifier.padding(2.dp))
    ButtonsDetaile(registerViewModel, navController)
    Spacer(modifier = Modifier.padding(2.dp))

}


@Composable
fun ButtonsDetaile(viewModel: RegisterViewModel, navController: NavHostController) {
    val context = LocalContext.current
    Row(modifier = Modifier) {
        Button(
            onClick = {
                viewModel.onRegister(navController, context)

            }, modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {
            if (viewModel._loading.value) {
                // Show loading animation when isLoading is true
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(36.dp)
                        .padding(end = 8.dp),
                    color = Color.White
                )
            } else {
                Text(text = stringResource(R.string.registrar))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldDetaile(name: String, viewModel: RegisterViewModel) {

    var namelUser by remember { mutableStateOf(viewModel.email) }
    var emailUser by remember { mutableStateOf(viewModel.email) }

    var colorPH = Color(R.color.gray_text)
    var iconId = R.drawable.icon_message
    var value = ""
    var type = KeyboardType.Text
    var valueChange: ((String) -> Unit)? = null
    var tint = colorResource(id = R.color.gray_text)


    if (name == stringResource(R.string.nombre_completo)) {
        colorPH = Color.Gray
        iconId = R.drawable.profile
        value = namelUser
        tint = colorResource(id = R.color.buttonGray)
        valueChange = { newValue ->
            namelUser = newValue
            viewModel.name = newValue
        }
    }

    if (name == stringResource(R.string.correo)) {
        value = emailUser
        type = KeyboardType.Text
        valueChange = { newValue ->
            emailUser = newValue
            viewModel.email = newValue
        }
    }


    OutlinedTextField(
        value = value,
        onValueChange = valueChange ?: {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)// Modified
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
                contentDescription = stringResource(R.string.icon_field),
                tint = tint
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
    var passwordUserVde by remember { mutableStateOf(viewModel.passwordVe) }
    var isVisible by remember { mutableStateOf(viewModel.isVisiblePaswd) }
    var value = ""
    var valueChange: ((String) -> Unit)? = null
    var colorId = colorResource(id = R.color.gray_text)
    var tint = colorResource(id = R.color.buttonGray)

    if (name != stringResource(R.string.contrase_a)) {
        colorId = Color.Gray
        value = passwordUser
        valueChange = { newValue ->
            passwordUser = newValue
            viewModel.password = newValue
        }
    }

    if (name != stringResource(R.string.verificar_contrase_a)) {
        colorId = Color.Gray
        value = passwordUserVde
        valueChange = { newValue ->
            passwordUserVde = newValue
            viewModel.passwordVe = newValue
        }
    }


    val visualTransformation =
        if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

    OutlinedTextField(
        value = value,
        onValueChange = valueChange ?: {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
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
                contentDescription = stringResource(R.string.icon_field),
                tint = tint
            )
        },
        trailingIcon = {

            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .clickable { isVisible = !isVisible },
                painter = painterResource(id = R.drawable.icon_hide),
                contentDescription = stringResource(R.string.hide_icon),
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

    FieldDetaileWB(name = stringResource(R.string.peso), viewModel)
    Spacer(modifier = Modifier.padding(2.dp))
    FieldDetaileWB(name = stringResource(R.string.estatura), viewModel)

}

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

    if (name == stringResource(R.string.peso)) {
        iconId = R.drawable.weight
        textB = stringResource(R.string.lb)
        value = weigthUser
        valueChange = { newValue ->
            weigthUser = newValue
            viewModel.weigth = newValue.toIntOrNull() ?: 0
        }
    }

    if (name == stringResource(R.string.estatura)) {
        iconId = R.drawable.swap
        textB = stringResource(R.string.cm)
        type = KeyboardType.Number
        value = heigthUser
        valueChange = { newValue ->
            heigthUser = newValue
            viewModel.heigth = newValue.toIntOrNull() ?: 0
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
                .fillMaxWidth(0.8f)
                .padding(8.dp)
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
                    contentDescription = stringResource(R.string.icon_field),
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
                .fillMaxWidth()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInputField(viewModel: RegisterViewModel) {

    var dateUser by remember { mutableStateOf(viewModel.date) }

    // Fetching the Local Context
    val mContext = LocalContext.current

    // Declaring integer values
    // for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    val mDate = remember { mutableStateOf("") }

    // Declaring DatePickerDialog and setting
    // initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = RoundedCornerShape(15.dp),
            onClick = {
                mDatePickerDialog.show()
            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = stringResource(R.string.fecha_de_nacimiento) +mDate.value,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                Icon(
                    painter = painterResource(id = R.drawable.calendar_days),
                    contentDescription = stringResource(R.string.calendar)
                )
            }
    }
    viewModel.date = mDate.value;
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreDropDownMenu(viewModel: RegisterViewModel) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var type by remember {
        mutableStateOf(viewModel.genre)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = it }) {
            TextField(value = type, onValueChange = { newValue ->
                type = newValue
                viewModel.genre = newValue
            }, readOnly = true, trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            }, modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .border(
                    width = 1.dp,
                    color = colorResource(id = R.color.field)
                )// With padding show border color
                .background(colorResource(id = R.color.field)),
                colors = TextFieldDefaults.textFieldColors(containerColor = colorResource(id = R.color.field)),
                placeholder = { Text(text = stringResource(R.string.genero), color = Color(R.color.gray_text)) },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = stringResource(R.string.icon_field)
                    )
                })

            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                DropdownMenuItem(text = { Text(text = stringResource(R.string.masculino)) },
                    onClick = {
                        isExpanded = false
                        viewModel.genre = "Masculino"
                        type = viewModel.genre
                    })
                DropdownMenuItem(text = { Text(text = stringResource(R.string.femenino)) },
                    onClick = {
                        isExpanded = false
                        viewModel.genre = "Femenino"
                        type = viewModel.genre

                    })

            }
        }
    }
}
