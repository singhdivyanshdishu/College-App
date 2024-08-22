package com.example.collegeapp.viewmodel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.collegeapp.models.BannerModel
import com.example.collegeapp.models.CollegeInfoModel
import com.example.collegeapp.utils.Constant.BANNER
import com.example.collegeapp.utils.Constant.COLLEGE_INFO
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.UUID

class CollegeInfoViewModel : ViewModel() {

    private val collegeInfoRef = Firebase.firestore.collection(COLLEGE_INFO)

    private val storageRef = Firebase.storage.reference

    private val _isPosted = MutableLiveData<Boolean>()
    val isPosted: LiveData<Boolean>  = _isPosted


    private val _collegeInfo = MutableLiveData<CollegeInfoModel>()
    val collegeInfo: LiveData<CollegeInfoModel> = _collegeInfo


    fun saveImage(uri: Uri , name : String , address : String ,desc:String, websiteLink:String) {
        _isPosted.postValue(false)
        val randomUid = UUID.randomUUID().toString()

        val imageRef = storageRef.child("$COLLEGE_INFO/${randomUid}.jpg")

        val uploadTask = imageRef.putFile(uri)

        uploadTask.addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener {
                uploadImage(it.toString(),  name , address , desc , websiteLink )
            }
        }

    }

    //chat gpt
//    private fun uploadImage(imageUrl: String, name: String, address: String, desc: String, websiteLink: String) {
//        val collegeInfo = mapOf(
//            "imageUrl" to imageUrl,
//            "name" to name,
//            "address" to address,
//            "desc" to desc,
//            "websiteLink" to websiteLink
//        )
//
//        collegeInfoRef.add(collegeInfo)
//            .addOnSuccessListener {
//                _isPosted.postValue(true)
//            }
//            .addOnFailureListener {
//                _isPosted.postValue(false)
//            }
//    }


    fun uploadImage(imageUrl: String, name : String , address : String ,desc:String, websiteLink:String) {

        val map = mutableMapOf<String, Any>()
        map["imageUrl"] = imageUrl
        map["name"] = name
        map["address"] = address
        map["desc"] = desc
        map["websiteLink"] = websiteLink

        collegeInfoRef.document("UIET").set(map)
            .addOnSuccessListener {
                _isPosted.postValue(true)
            }
            .addOnFailureListener{
                _isPosted.postValue(false)
            }
    }


//    fun getCollegeInfo(){
//        collegeInfoRef.document("UIET").get().addOnSuccessListener {
//           _collegeInfo.postValue(
//               CollegeInfoModel(
//                   it.data!!["name"].toString(),
//                   it.data!!["address"].toString(),
//                   it.data!!["desc"].toString(),
//                   it.data!!["websiteLink"].toString(),
//                   it.data!!["imageUrl"].toString()
//
//               )
//           )
//
//
//        }
//    }

    //Gemini

    fun getCollegeInfo(){
        collegeInfoRef.document("UIET").get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists() && documentSnapshot.data != null) { // Check if document exists and has data
                val data = documentSnapshot.data!! // Now it's safe to use !!
                _collegeInfo.postValue(
                    CollegeInfoModel(
                        data["name"] as String? ?: "", // Use safe cast and provide default values
                        data["address"] as String? ?: "",
                        data["desc"] as String? ?: "",
                        data["websiteLink"] as String? ?: "",
                        data["imageUrl"] as String? ?: ""
                    )
                )
            } else {
                // Handle the case where the document doesn't exist or has no data
                // For example, you could post an empty CollegeInfoModel or show an error message
                _collegeInfo.postValue(CollegeInfoModel("", "", "", "", ""))
            }
        }.addOnFailureListener { exception ->
            // Handle any errors that occur while fetching the document
            Log.e("CollegeInfoViewModel", "Error getting college info: ${exception.message}")
        }
    }


}