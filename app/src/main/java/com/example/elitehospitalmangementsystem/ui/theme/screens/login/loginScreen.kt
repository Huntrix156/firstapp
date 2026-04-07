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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.elitehospitalmangementsystem.R
import com.example.elitehospitalmangementsystem.navigation.ROUTE_LOGIN
import com.example.elitehospitalmangementsystem.navigation.ROUTE_REGISTER

@Composable
fun LoginScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Image(painter = painterResource(id= R.drawable.login),
    contentDescription = "Background",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )

    Box(modifier =  Modifier
        .fillMaxSize()
    ) {

        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center ) {
            Image(painter = painterResource(id = R.drawable.audi),
                contentDescription="logo")
            Text(
                text = "Login",
                modifier = Modifier
                    .fillMaxWidth(),




            )

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text(text = "userName") }
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "password") }
            )

            Row() {

                Text(text = "Already Registered?",
                    color = Color.Green,)
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Login Here",
                    color = Color.Blue)
                 Text(text="Login Here",
                    modifier = Modifier
                        .clickable{navController.navigate(ROUTE_REGISTER)}) }

            } }
    }






@Preview(showBackground = true, showSystemUi = true)
@Composable
fun loginScreenpreview(){
    LoginScreen(navController= rememberNavController())
}






