package com.mintic.travelTicApp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sites(
    val name: String,
    val description: String,
    var points: String,
    var image: String
) :Parcelable
