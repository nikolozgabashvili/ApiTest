package com.example.apitest.classes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardClass(
    val id:Long?,
    val title: String?,
    val userName: String?,
    val numViews:Long?,
    val numComments:Long?,
    val numHearts:Long?,
    val rank:Long?,
    val dateCreated:String?,
    val hex:String?,
    val rgb: RBG?,
    val hsv: HSV?,
    val description:String?,
    val url:String?,
    val imageUrl:String?,
    val badgeUrl:String?,
    val apiUrl:String?
    
) : Parcelable {
    @Parcelize
    data class RBG(
        val red: Int?,
        val green:Int?,
        val blue:Int?
    ) : Parcelable

    @Parcelize
    data class HSV (
        val hue:Int?,
        val saturation:Int?,
        val value :Int?

    ) : Parcelable
}
