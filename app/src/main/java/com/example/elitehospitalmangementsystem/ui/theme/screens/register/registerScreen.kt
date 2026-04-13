package com.example.elitehospitalmangementsystem.ui.theme.screens.register


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.elitehospitalmangementsystem.R
import com.example.elitehospitalmangementsystem.data.AuthViewModel
import com.example.elitehospitalmangementsystem.navigation.ROUTE_LOGIN




@Composable
fun RegisterScreen(navController: NavController){
    var username by remember { mutableStateOf( "") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember {mutableStateOf("")}
    var passwordVisible by remember { mutableStateOf(false) }

    val authViewModel: AuthViewModel=viewModel()  //this bring the login to the screen from the AuthViewModel
    val context = LocalContext.current

    Box(modifier = Modifier
        .fillMaxSize()
    )
    {Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "logo",
        contentScale = ContentScale.Crop,
        modifier = Modifier
//            .size(width = 300.dp, height = 300.dp)
//            .clip(CircleShape)
//            .border(2.dp, Color.White,)
//            .shadow(4.dp, )
                )

    }
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center ) {
          //image
        Image(painter = painterResource(id = R.drawable.audi),
            contentDescription = "Logo",
                )
         //register text
        Text(text="Register Here", fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White)
        OutlinedTextField(
            value = username, // To be in the safe side this value should be the same with the one
            // in both the UserModel and the  authViewModel
            onValueChange = {username = it},
            label={Text(text="Enter Username")},
            placeholder={Text(text="Please enter Username")},
            leadingIcon = { Icon(Icons.Default.Person,contentDescription = null) }
        )
        OutlinedTextField(
            value = email,
            onValueChange = {email = it },
            label = { Text(text = "Enter Email")},
            placeholder={Text(text="Please enter Email")},
            leadingIcon = { Icon(Icons.Default.Email,contentDescription = null) }
        )


        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone Number") }
        )
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label={Text(text ="Enter password")},
            placeholder={Text(text="Please enter Password")},
            leadingIcon = { Icon(Icons.Default.Lock,contentDescription = null) },
            visualTransformation = if (passwordVisible) VisualTransformation.None
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

        OutlinedTextField(
            value= confirmPassword,
            onValueChange = { confirmPassword = it },
            label = {Text(text="ConfirmPassword")},
            placeholder={Text(text="Please Confirm Password ")},
            leadingIcon = { Icon(Icons.Default.Check,contentDescription = null) },
            visualTransformation = if (passwordVisible) VisualTransformation.None// this is the part that makes the password invisible
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


        Button(onClick ={authViewModel.signup(          //calling the authViewModel
            username=username,
            email = email,
            phone = phone,
            password= password,
            confirmpassword = confirmPassword,
            navController= navController,
             context= context )},
            modifier = Modifier
                .fillMaxWidth()
        )
        {Text(text="Submit",
//            modifier = Modifier.clickable{navController.navigate(ROUTE_LOGIN)}
            ) }
        Row() {
            Text(text = "Already Registered?",
                color = Color.Green,)
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Login Here",
                color = Color.Blue,
                modifier = Modifier.clickable{navController.navigate(ROUTE_LOGIN)})
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview(){
    RegisterScreen(rememberNavController())
}