package com.jder00138218.liftapp.ui.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jder00138218.liftapp.ui.login.LoginScreen
import com.jder00138218.liftapp.ui.login.viewmodel.LoginViewModel

@Preview(name = "Preview", showBackground = true)
@Composable
fun previewApp() {
    Welcome()
}

@Composable
fun Welcome() {
    Text(text = "Hello")
}
