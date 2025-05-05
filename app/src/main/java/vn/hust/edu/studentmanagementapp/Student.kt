package vn.hust.edu.studentmanagementapp

import android.os.Parcel
import android.os.Parcelable

import java.io.Serializable

data class Student(
    var name: String,
    var id: String,
    var email: String,
    var phone: String
) : Serializable
