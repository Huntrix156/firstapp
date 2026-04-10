package com.example.elitehospitalmangementsystem.ui.theme.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lint.kotlin.metadata.Visibility
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.elitehospitalmangementsystem.R
import com.example.elitehospitalmangementsystem.data.AuthViewModel
import com.example.elitehospitalmangementsystem.navigation.ROUTE_LOGIN
import com.example.elitehospitalmangementsystem.navigation.ROUTE_REGISTER

@Composable
fun LoginScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var Email by remember { mutableStateOf("") }
    val context = LocalContext.current
    val authViewModel: AuthViewModel = viewModel()



    Image(
        painter = painterResource(id = R.drawable.login),
        contentDescription = "Background",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.audi),
                contentDescription = "logo"
            )
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "userName") }
            )
            Text(
                text = "Login",
                modifier = Modifier
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "userName") })

            OutlinedTextField(
                value = Email,
                onValueChange = { Email = it },
                label = { Text(text = "Email") }
            )





            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "password") },
                visualTransformation = if (passwordVisible)
                    VisualTransformation.None
                else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.Visibility
                            else Icons.Default.VisibilityOff,
                            contentDescription = "Toggle Password"
                        )
                    }
                }
            )

            Row() {

                Text(
                    text = "Already Registered?",
                    color = Color.Green,
                )
                Spacer(modifier = Modifier.width(5.dp))
                Button(onClick = {
                    authViewModel.login(
                        email = Email,
                        password = password,
                        navController = navController,
                        context = context
                    )
                },
                modifier = Modifier.fillMaxWidth()
                ) {Text(text = "Login", color = Color.White) }
                Row() {
                    Text(
                        text = "Dont Have an Account?", color = Color.White)
                    Text(
                        text = "Register Here",
                        modifier = Modifier
                            .clickable { navController.navigate(ROUTE_REGISTER) }
                    )
                }

            }
        }
    }
}







@Preview(showBackground = true, showSystemUi = true)
@Composable
fun loginScreenpreview(){
    LoginScreen(navController= rememberNavController())
}






