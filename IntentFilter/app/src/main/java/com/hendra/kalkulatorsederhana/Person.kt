package com.hendra.kalkulatorsederhana

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person (
    val name: String?,
    val age: Int?,
    val address: String,
    val city: String
) : Parcelable