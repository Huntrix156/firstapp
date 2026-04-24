package com.example.elitehospitalmangementsystem.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.elitehospitalmangementsystem.ui.theme.SplashScreen
import com.example.elitehospitalmangementsystem.ui.theme.screens.dashboard.DashboardScreen
import com.example.elitehospitalmangementsystem.ui.theme.screens.forgetpassword.screens.ForgotPasswordScreen
import com.example.elitehospitalmangementsystem.ui.theme.screens.login.LoginScreen
import com.example.elitehospitalmangementsystem.ui.theme.screens.patient.AddPatientScreen
import com.example.elitehospitalmangementsystem.ui.theme.screens.patient.PatientListScreen
import com.example.elitehospitalmangementsystem.ui.theme.screens.patient.UpdatePatientScreen
import com.example.elitehospitalmangementsystem.ui.theme.screens.register.RegisterScreen

@Composable
fun AppNavHost(navController: NavHostController= rememberNavController(),
                startDestination:String = ROUTE_ADD_PATIENT){
    NavHost(navController = navController,
        startDestination = startDestination){
        composable(ROUTE_REGISTER) {RegisterScreen(navController)}
        composable(ROUTE_LOGIN ) {LoginScreen(navController) }
        composable  (ROUTE_DASHBOARD) { DashboardScreen(navController) }
        composable  (ROUTE_ADD_PATIENT) { AddPatientScreen(navController) }
        composable (ROUTE_PATIENT_LIST){ PatientListScreen(navController) }
        composable(ROUTE_FORGOT_PASSWORD) { ForgotPasswordScreen(navController)}
        composable (ROUTE_SPLASH_SCREEN){ SplashScreen(navController) }
        composable(ROUTE_UPDATE_PATIENT,
            arguments = listOf(
                navArgument("patientId"){
                    type = NavType.StringType }
            )){
                backStackEntry ->
            val patientId = backStackEntry.arguments?.getString("patientId")!!
            UpdatePatientScreen(
                navController = navController,
                patientId = patientId
            )
        }
    }

    }


