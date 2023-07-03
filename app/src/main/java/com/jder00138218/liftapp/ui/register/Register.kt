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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jder00138218.liftapp.R
import com.jder00138218.liftapp.ui.register.viewmodel.RegisterViewModel
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
    GenreDropDownMenu(registerViewModel)
    Spacer(modifier = Modifier.padding(2.dp))
    DateInputField(registerViewModel)
    Spacer(modifier = Modifier.padding(2.dp))
    GroupPE(registerViewModel)
    Spacer(modifier = Modifier.padding(2.dp))
    ButtonsDetaile(registerViewModel, navController)
    Spacer(modifier = Modifier.padding(2.dp))

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreDropDownMenu(viewModel: RegisterViewModel) {

    val genreList = arrayOf("Masculino", "Femenino")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(genreList[0]) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },

            ) {
            TextField(
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                leadingIcon = {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(R.drawable.user),
                        contentDescription = "Icon field",
                        tint = colorResource(id = R.color.gray_text)
                    )
                },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()

            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                genreList.forEach { item ->
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        text = { Text(text = item) },
                        onClick = {
                            selectedText = item
                            expanded = false
                            viewModel.genre = selectedText
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun ButtonsDetaile(viewModel: RegisterViewModel, navController: NavHostController) {
    val context = LocalContext.current
    Row(modifier = Modifier.padding(8.dp)) {
        Button(
            onClick = {
                viewModel.onRegister(navController, context)

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
    var emailUser by remember { mutableStateOf(viewModel.email) }

    var colorPH = Color(R.color.gray_text)
    var iconId = R.drawable.icon_message
    var value = ""
    var type = KeyboardType.Text
    var valueChange: ((String) -> Unit)? = null
    var tint = colorResource(id = R.color.gray_text)


    if (name == "Nombre completo") {
        colorPH = Color.Red
        iconId = R.drawable.profile
        value = namelUser
        tint = colorResource(id = R.color.buttonRed)
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
                contentDescription = "Icon field",
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
    var tint = colorResource(id = R.color.buttonRed)

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
                contentDescription = "Icon field",
                tint = tint
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
        iconId = R.drawable.weight
        textB = "LB"
        value = weigthUser
        valueChange = { newValue ->
            weigthUser = newValue
            viewModel.weigth = newValue.toIntOrNull() ?: 0
        }
    }

    if (name == "Estatura") {
        iconId = R.drawable.swap
        textB = "CM"
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

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {


        // Displaying the mDate value in the Text
        Text(
            text = "Fecha de nacimiento: ${mDate.value}",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        // Adding a space of 100dp height
        Spacer(modifier = Modifier.padding(2.dp))
        // Creating a button that on
        // click displays/shows the DatePickerDialog

        Button(
            modifier = Modifier
                .width(70.dp)
                .height(30.dp),
            shape = RoundedCornerShape(15.dp),
            onClick = {
                mDatePickerDialog.show()
            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.calendar_days),
                contentDescription = "Calendar"
            )
        }
    }
    viewModel.date = mDate.value;
}

