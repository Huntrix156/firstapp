package com.example.elitehospitalmangementsystem.ui.theme.screens.forgetpassword.screens

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
//import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Forgot Password") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Reset Your Password", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(10.dp))

            Text("Enter your email and we will send you a reset link")

            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email Address") },
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = "")
                },
//                keyboardOptions = androidx.compose.ui.text.input.KeyboardOptions(
//                    keyboardType = KeyboardType.Email
//                ),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {

                    if (email.isEmpty()) {
                        Toast.makeText(context, "Email is required", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        Toast.makeText(context, "Enter a valid email", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    isLoading = true

                    auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener { task ->
                            isLoading = false

                            if (task.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Reset link sent to $email",
                                    Toast.LENGTH_LONG
                                ).show()

                                // Optional: go back to log in
                                navController.popBackStack()
                            } else {
                                Toast.makeText(
                                    context,
                                    task.exception?.message ?: "Error occurred",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                enabled = !isLoading
            ) {

                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Send Reset Link")
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            TextButton(onClick = {
                navController.popBackStack()
            }) {
                Text("Back to Login")
            }
        }
    }
}