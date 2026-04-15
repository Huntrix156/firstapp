package com.example.elitehospitalmangementsystem.models

data class PatientModel(
    var id: String?=null,//from the database//
    var name: String?=null,
    var age: String?=null,
    var phone: String?=null,
    var illness: String?=null,
    var imageUrl: String?=null,
    var gender: String?=null,
    var date_of_visit: String?=null,
)//>This are records from the patient//


//create the model
// then
//view model
//then attach it to the frontend
//t