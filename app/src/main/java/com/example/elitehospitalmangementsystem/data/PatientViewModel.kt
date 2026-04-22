package com.example.elitehospitalmangementsystem.data



//credintial functions
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.elitehospitalmangementsystem.models.PatientModel
import com.example.elitehospitalmangementsystem.navigation.ROUTE_DASHBOARD
import com.example.elitehospitalmangementsystem.navigation.ROUTE_PATIENT_LIST
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.InputStream

class PatientViewModel:ViewModel() {
    val cloudinaryUrl = "https://api.cloudinary.com/v1_1/dfuv2cguf/image/upload"//"https://api.cloudinary.com/v1_1/this come from cloudinary(Cloud name)/image/upload"
    val uploadPreset = "image_folder"//this is done  in the cloudinary to the ((upload) in the setting)
//    capturing patient details//

    //--------UPLOAD A PATIENT--------//
    fun uploadPatient(imageUri: Uri?,
                      name:String,
                      age:String,
                      phone:String,
                      illness:String,
                      gender: String,
                      date_of_visit: String,
                      context: Context,
                      navController: NavController){

        viewModelScope.launch (Dispatchers.IO){
            try {
                val imageUrl = imageUri?.let { uploadToCloudinary(context,it) }
                val ref = FirebaseDatabase.getInstance().getReference("Patients").push()//to be saved in the database table Patients
                val patientData = mapOf(
                    "id" to ref.key,//if successful this try works if not //CATCH work meaning not saved
                    "name" to name,
                    "age" to age,
                    "phone" to phone,
                    "illness" to illness,
                    "imageUrl" to imageUrl,
                    "gender" to gender,
                    "date_of_visit" to date_of_visit
                )
                ref.setValue(patientData).await()//Save function//
                withContext(Dispatchers.Main){
                    Toast.makeText(context,"Patient saved Successfully",Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_DASHBOARD)
                }

            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(context,"Patient not saved",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
//------UPLOADING AN IMAGE TO CLOUDINARY-------//
    private fun uploadToCloudinary(context:Context,uri: Uri):String{
        val contentResolver = context.contentResolver
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        val fileBytes = inputStream?.readBytes() ?: throw  Exception("Image read failed")
        val requestBody = MultipartBody.Builder().setType(MultipartBody.FORM)
            .addFormDataPart("file","image.jpg",
                RequestBody.create("image/*".toMediaTypeOrNull(),fileBytes))
            .addFormDataPart("upload_preset",uploadPreset).build()
        val request = Request.Builder().url(cloudinaryUrl).post(requestBody).build()
        val response = OkHttpClient().newCall(request).execute()
        if(!response.isSuccessful) throw Exception("Upload failed")
        val responseBody = response.body?.string()
        val secureUrl = Regex("\"secure_url\":\"(.*?)\"")
            .find(responseBody ?: "")?.groupValues?.get(1)
        return secureUrl ?: throw Exception("Failed to get image URL")
    }



    // Correct state for a list
    private val _patients = mutableStateListOf<PatientModel>()
    val patients: List<PatientModel> = _patients


//---------FETCH FUNCTION FOR PATIENTS-------//
    fun fetchPatient(context: Context){

        val ref = FirebaseDatabase.getInstance().getReference("Patients")

        ref.get().addOnSuccessListener { snapshot ->

            _patients.clear()

            for (child in snapshot.children){
                val patient = child.getValue(PatientModel::class.java)

                patient?.let {
                    it.id = child.key ?: ""
                    _patients.add(it)
                }
            }

        }.addOnFailureListener {
            Toast.makeText(context, "Failed to load patients", Toast.LENGTH_LONG).show()
        }
    }

    //-----UPDATE PATIENT FUNCTION-------
    fun updatePatient(patientId: String,
                      imageUri: Uri?,
                      name: String,
                      age: String,
                      phone: String,
                      illness: String,
                      gender: String,
                      date_of_visit: String,
                      context: Context,
                      navController: NavController){
        viewModelScope.launch ( Dispatchers.IO ){
            try {
                val imageUri = imageUri?.let { uploadToCloudinary(context,it) }
                val updatePatient = mapOf(
                    "id" to patientId,
                    "name" to name,
                    "age" to age,
                    "phone" to phone,
                    "illness" to illness,
                    "gender" to gender,
                    "date_of_visit" to date_of_visit,
                    "imageUrl" to imageUri
                 )


                val ref = FirebaseDatabase.getInstance()
                    .getReference("Patients")
                    .child(patientId) // ✅ FIX: correct update path

                ref.setValue( updatePatient).await() // ✅ FIX: actually update data

                withContext(Dispatchers.Main){ // ✅ FIX: UI thread
                    Toast.makeText(context,"Patient updated successfully",Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_PATIENT_LIST)
                }



//
//                val ref = FirebaseDatabase.getInstance()
//                    .getReference("Patients update successfully",
//                        Toast.LENGTH_LONG).show()
//                navController.navigate(ROUTE_PATIENT_LIST)

        }catch (e: Exception){
            withContext(Dispatchers.Main){
                Toast.makeText(context,"Patient update failed",
                    Toast.LENGTH_LONG).show()
            }
        }

    }}}




